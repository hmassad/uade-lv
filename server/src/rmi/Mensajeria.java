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
				if (id != null) {
					// mensaje guardado, eliminarlo
					em.remove((Mensaje) em.find(Mensaje.class, id));
				}

				Mensaje mensaje = new Mensaje();
				mensaje.setFecha(new Date());
				mensaje.setAsunto(asunto);
				mensaje.setCuerpo(cuerpo);
				mensaje.setTipo(tipo);
				mensaje.setOrigen(casillaOrigen);

				casillaOrigen.agregarMensaje(mensaje, MensajeEstado.Enviado);

				for (String s : destinos) {
					Casilla casillaDestino = buscarCasillaPorDireccion(s, em);

					if (casillaDestino.getUsuario().getCasillasBloqueadas().contains(casillaOrigen)) {
						Mensaje mensajeBloqueo = new Mensaje();
						mensajeBloqueo.setFecha(new Date());
						mensajeBloqueo.setAsunto("Aviso de Bloqueo");
						mensajeBloqueo.setCuerpo(String.format("Su mensaje con asunto \"%s\", enviado a \"%s\", no se puede enviar porque el usuario bloqueó la recepción desde esta casilla.", asunto,
								s));
						mensajeBloqueo.setTipo(tipo);
						mensajeBloqueo.setOrigen(null);
						em.persist(mensajeBloqueo);
						casillaOrigen.agregarMensaje(mensajeBloqueo, MensajeEstado.NoLeido);
					} else {
						casillaDestino.agregarMensaje(mensaje, MensajeEstado.NoLeido);
					}
				}

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw new RemoteException("Ocurrió un error al enviar el mensaje.", e);
			}
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<String> listarDireccionesPosibles(String direccion) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			ArrayList<String> direcciones = new ArrayList<String>();

			Casilla casilla = buscarCasillaPorDireccion(direccion, em);
			for (Oficina o : casilla.getOficinas()) {
				for (Casilla c : o.getCasillas()) {
					if (!c.equals(casilla)) {
						direcciones.add(c.getDireccion());
					}
				}
				for (RelacionConfianza rc : o.getRelacionesConfianza()) {
					for (Casilla c : rc.getDestino().getCasillas()) {
						if (!direcciones.contains(c.getDireccion()) && !c.equals(casilla)) {
							direcciones.add(c.getDireccion());
						}
					}
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
		Collection<MensajeVO> mensajesVO = listarMensajesPorCasilla(direccion);
		for (MensajeVO mensajeVO : mensajesVO) {
			if (!mensajeVO.getEstado().equals(estado)) {
				mensajesVO.remove(mensajeVO);
			}
		}
		return mensajesVO;
	}

	@Override
	public void guardarMensaje(Integer id, String direccion, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccion, em);
			if (casilla == null) {
				throw new RemoteException("Casilla no encontrada.");
			}

			EntityTransaction et = em.getTransaction();
			try {
				if (id != null) {
					// mensaje guardado, eliminarlo
					em.remove((Mensaje) em.find(Mensaje.class, id));
				}

				Mensaje mensaje = new Mensaje();
				mensaje.setFecha(new Date());
				mensaje.setOrigen(casilla);
				mensaje.setAsunto(asunto);
				mensaje.setCuerpo(cuerpo);
				mensaje.setTipo(tipo);

				casilla.agregarMensaje(mensaje, MensajeEstado.SinEnviar);

				et.commit();
			} catch (Exception e) {
				et.rollback();
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
}
