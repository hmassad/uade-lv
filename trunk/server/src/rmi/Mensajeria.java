package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.CasillaVO;
import beans.MensajeVO;
import entities.Casilla;
import entities.LogTrafico;
import entities.Mensaje;
import entities.MensajeEnCasilla;
import entities.Oficina;
import entities.RelacionConfianza;
import entities.Usuario;
import enums.MensajeEstado;
import enums.MensajeTipo;

public class Mensajeria extends UnicastRemoteObject implements InterfazMensajeria {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf;

	public Mensajeria() throws RemoteException {
		super();
		emf = Persistence.createEntityManagerFactory("default");
	}

	@Override
	protected void finalize() throws Throwable {
		if (emf != null) {
			emf.close();
		}
		super.finalize();
	}

	private Usuario buscarUsuarioPorNombre(String nombreUsuario, EntityManager em) {
		try {
			return (Usuario) em.createQuery("select u from Usuario u where u.nombre = :nombre").setParameter("nombre", nombreUsuario).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	private Casilla buscarCasillaPorDireccion(String direccion, EntityManager em) {
		Query q = em.createQuery("select c from Casilla c where c.direccion = :direccion");
		q.setParameter("direccion", direccion);
		try {
			Casilla c = (Casilla) q.getSingleResult();
			return c;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Collection<MensajeVO> listarMensajesPorCasilla(String direccion) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccion, em);
			ArrayList<MensajeVO> mensajes = new ArrayList<MensajeVO>();
			for (MensajeEnCasilla cm : casilla.getMensajes()) {
				MensajeVO m = new MensajeVO();
				m.setId(cm.getMensaje().getId());
				m.setFecha(cm.getMensaje().getFecha());
				m.setAsunto(cm.getMensaje().getAsunto());
				m.setCuerpo(cm.getMensaje().getCuerpo());
				m.setTipo(cm.getMensaje().getTipo());

				m.setOrigen(cm.getMensaje().getOrigen().getDireccion());
				for (MensajeEnCasilla destino : cm.getMensaje().getDestinos()) {
					m.agregarDestino(destino.getCasilla().getDireccion());
				}
				m.setEstado(cm.getEstado());

				mensajes.add(m);
			}
			return mensajes;
		} finally {
			em.close();
		}
	}

	@Override
	public void enviarMensaje(Integer id, String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casillaOrigen = buscarCasillaPorDireccion(origen, em);
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();

				LogTrafico log = new LogTrafico();

				Mensaje mensaje;
				if (id != null) {
					// mensaje guardado
					mensaje = (Mensaje) em.find(Mensaje.class, id);
				} else {
					// mensaje nuevo
					mensaje = new Mensaje();

					MensajeEnCasilla mc = new MensajeEnCasilla();
					mc.setCasilla(casillaOrigen);
					mc.setMensaje(mensaje);
					mc.setEstado(MensajeEstado.Enviado);

					em.persist(mensaje);
					em.persist(mc);
				}

				mensaje.setFecha(new Date());
				mensaje.setAsunto(asunto);
				mensaje.setCuerpo(cuerpo);
				mensaje.setTipo(tipo);
				mensaje.setOrigen(casillaOrigen);

				log.setFecha(mensaje.getFecha());
				log.setMensaje(mensaje);
				log.setOrigen(casillaOrigen);

				for (String s : destinos) {
					Casilla casillaDestino = buscarCasillaPorDireccion(s, em);

					if (casillaDestino == null || !puedeEnviar(casillaOrigen, casillaDestino)) {
						throw new Exception("Casilla destino no válida.");
					}

					if (casillaDestino.getUsuario().getCasillasBloqueadas().contains(casillaOrigen)) {
						Mensaje mensajeBloqueo = new Mensaje();
						mensajeBloqueo.setFecha(new Date());
						mensajeBloqueo.setAsunto("Aviso de Bloqueo");
						mensajeBloqueo.setCuerpo(String.format("Su mensaje con asunto \"%s\", enviado a \"%s\", no se puede enviar porque el usuario bloqueó la recepción desde esta casilla.", asunto,
								s));
						mensajeBloqueo.setTipo(tipo);
						mensajeBloqueo.setOrigen(null);

						MensajeEnCasilla mc = new MensajeEnCasilla();
						mc.setCasilla(casillaDestino);
						mc.setMensaje(mensajeBloqueo);
						mc.setEstado(MensajeEstado.NoLeido);

						em.persist(mensajeBloqueo);
						em.persist(mc);
					} else {
						MensajeEnCasilla mc = new MensajeEnCasilla();
						mc.setCasilla(casillaDestino);
						mc.setMensaje(mensaje);
						mc.setEstado(MensajeEstado.NoLeido);

						em.persist(mc);
					}
					log.agregarDestino(casillaDestino);
				}

				em.persist(log);
				em.flush();
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw new RemoteException("Ocurrió un error al enviar el mensaje.", e);
			}
		} finally {
			em.close();
		}
	}

	private Collection<Casilla> listarCasillasPosibles(Casilla remitente) {
		Collection<Casilla> casillas = new ArrayList<Casilla>();
		for (Oficina o : remitente.getOficinas()) {
			for (Casilla c : o.getCasillas()) {
				if (!c.equals(remitente) && !casillas.contains(c)) {
					casillas.add(c);
				}
			}
			for (RelacionConfianza rc : o.getRelacionesConfianza()) {
				for (Casilla c : rc.getDestino().getCasillas()) {
					if (!c.equals(remitente) && !casillas.contains(c)) {
						casillas.add(c);
					}
				}
			}
		}
		return casillas;
	}

	@Override
	public Collection<String> listarDireccionesPosibles(String direccion) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			ArrayList<String> direcciones = new ArrayList<String>();
			for (Casilla casilla : listarCasillasPosibles(buscarCasillaPorDireccion(direccion, em))) {
				direcciones.add(casilla.getDireccion());
			}
			return direcciones;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<String> listarDireccionesPosiblesQueComiencenCon(String direccion, String comienzo) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			ArrayList<String> direcciones = new ArrayList<String>();
			for (Casilla casilla : listarCasillasPosibles(buscarCasillaPorDireccion(direccion, em))) {
				if (casilla.getDireccion().startsWith(comienzo)) {
					direcciones.add(casilla.getDireccion());
				}
			}
			return direcciones;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean validarLogin(String nombreUsuario, String password) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = buscarUsuarioPorNombre(nombreUsuario, em);
			if (usuario == null) {
				throw new RemoteException("Usuario no encontrado.");
			}
			return usuario.getPassword().equals(password);
		} finally {
			em.close();
		}
	}

	@Override
	public void cambiarPassword(String nombreUsuario, String password) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = buscarUsuarioPorNombre(nombreUsuario, em);
			if (usuario == null) {
				throw new RemoteException("Usuario no encontrado.");
			}
			usuario.setPassword(password);
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				em.flush();
				if (et.isActive()) {
					et.commit();
				}
			} catch (Exception e) {
				et.rollback();
				throw new RemoteException("Ocurrió un error al cambiar la contraseña.", e);
			}
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<CasillaVO> listarCasillasPorUsuario(String nombreUsuario) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = buscarUsuarioPorNombre(nombreUsuario, em);
			Collection<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			for (Casilla casilla : usuario.getCasillas()) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillasVO.add(casillaVO);
			}
			return casillasVO;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<MensajeVO> listarMensajesPorCasillaPorEstado(String direccion, MensajeEstado estado) throws RemoteException {
		Collection<MensajeVO> mensajesVO = new ArrayList<MensajeVO>();
		for (MensajeVO mensajeVO : listarMensajesPorCasilla(direccion)) {
			if (mensajeVO.getEstado().equals(estado)) {
				mensajesVO.add(mensajeVO);
			}
		}
		return mensajesVO;
	}

	@Override
	public void guardarMensaje(Integer id, String direccion, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();

				Casilla casilla = buscarCasillaPorDireccion(direccion, em);
				if (casilla == null) {
					throw new RemoteException("Casilla no encontrada.");
				}

				Mensaje mensaje;
				if (id != null) {
					// mensaje guardado
					mensaje = (Mensaje) em.find(Mensaje.class, id);
				} else {
					// mensaje nuevo
					mensaje = new Mensaje();
					MensajeEnCasilla cm = new MensajeEnCasilla();
					cm.setCasilla(casilla);
					cm.setMensaje(mensaje);
					cm.setEstado(MensajeEstado.SinEnviar);
					casilla.getMensajes().add(cm);

					em.persist(mensaje);
					em.persist(cm);
				}

				mensaje.setFecha(new Date());
				mensaje.setOrigen(casilla);
				mensaje.setAsunto(asunto);
				mensaje.setCuerpo(cuerpo);
				mensaje.setTipo(tipo);

				em.flush();
				et.commit();
			} catch (RemoteException e) {
				et.rollback();
				throw e;
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void eliminarMensaje(Integer id) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			if (id != null) {
				em.remove((Mensaje) em.find(Mensaje.class, id));
			}
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<MensajeVO> listarMensajesPorUsuario(String nombreUsuario) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = buscarUsuarioPorNombre(nombreUsuario, em);
			Collection<MensajeVO> mensajesVO = new ArrayList<MensajeVO>();
			for (Casilla c : usuario.getCasillas()) {
				for (MensajeEnCasilla mc : c.getMensajes()) {
					MensajeVO mensajeVO = new MensajeVO();
					mensajeVO.setId(mc.getMensaje().getId());
					mensajeVO.setFecha(mc.getMensaje().getFecha());
					mensajeVO.setAsunto(mc.getMensaje().getAsunto());
					mensajeVO.setCuerpo(mc.getMensaje().getCuerpo());
					mensajeVO.setTipo(mc.getMensaje().getTipo());
					mensajeVO.setEstado(mc.getEstado());
					mensajeVO.setOrigen(mc.getMensaje().getOrigen().getDireccion());
					for (MensajeEnCasilla destino : mc.getMensaje().getDestinos()) {
						mensajeVO.agregarDestino(destino.getCasilla().getDireccion());
					}
				}
			}
			return mensajesVO;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<MensajeVO> listarMensajesPorUsuarioPorEstado(String nombreUsuario, MensajeEstado estado) throws RemoteException {
		Collection<MensajeVO> mensajesVO = new ArrayList<MensajeVO>();
		for (MensajeVO m : listarMensajesPorUsuario(nombreUsuario)) {
			if (m.getEstado().equals(estado)) {
				mensajesVO.add(m);
			}
		}
		return mensajesVO;
	}

	@Override
	public MensajeVO obtenerMensaje(String direccionCasilla, Integer idMensaje) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccionCasilla, em);
			if (casilla == null) {
				throw new RemoteException("No se encontró la Casilla.");
			}
			for (MensajeEnCasilla mc : casilla.getMensajes()) {
				if (mc.getMensaje().getId() == idMensaje) {
					MensajeVO mensajeVO = new MensajeVO();
					mensajeVO.setId(mc.getMensaje().getId());
					mensajeVO.setFecha(mc.getMensaje().getFecha());
					mensajeVO.setAsunto(mc.getMensaje().getAsunto());
					mensajeVO.setCuerpo(mc.getMensaje().getCuerpo());
					mensajeVO.setTipo(mc.getMensaje().getTipo());

					mensajeVO.setEstado(mc.getEstado());
					mensajeVO.setOrigen(mc.getMensaje().getOrigen().getDireccion());
					for (MensajeEnCasilla destino : mc.getMensaje().getDestinos()) {
						if (!destino.getCasilla().equals(mc.getMensaje().getOrigen())) {
							mensajeVO.agregarDestino(destino.getCasilla().getDireccion());
						}
					}
					return mensajeVO;
				}
			}
			throw new RemoteException("No se encontró el Mensaje.");
		} finally {
			em.close();
		}
	}

	@Override
	public void cambiarMensajeEstado(String direccionCasilla, Integer idMensaje, MensajeEstado estado) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccionCasilla, em);
			if (casilla == null) {
				throw new RemoteException("No se encontró la Casilla.");
			}
			for (MensajeEnCasilla mc : casilla.getMensajes()) {
				if (mc.getMensaje().getId() == idMensaje) {
					EntityTransaction et = em.getTransaction();
					try {
						et.begin();

						mc.setEstado(estado);

						em.flush();
						et.commit();
					} catch (Exception e) {
						et.rollback();
						throw new RemoteException("Ocurrió un error al cambiar el estado del mensaje.", e);
					}
					return;
				}
			}
			throw new RemoteException("No se encontró el Mensaje.");
		} finally {
			em.close();
		}
	}

	private boolean puedeEnviar(Casilla remitente, Casilla destinatario) {
		for (Oficina o : remitente.getOficinas()) {
			if (o.getCasillas().contains(destinatario)) {
				return true;
			}
			for (RelacionConfianza rc : o.getRelacionesConfianza()) {
				if (rc.getDestino().getCasillas().contains(destinatario)) {
					return true;
				}
			}
		}
		return false;
	}
}
