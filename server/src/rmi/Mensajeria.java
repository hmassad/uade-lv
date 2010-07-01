package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import beans.MensajeVO;
import entities.Casilla;
import entities.CasillaMensaje;
import entities.Mensaje;
import entities.Oficina;
import entities.RelacionConfianza;
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

	private Casilla buscarCasillaPorDireccion(String direccion, EntityManager em) {
		Query q = em.createQuery("select c from Casilla c where c.direccion = :direccion");
		q.setParameter("direccion", direccion);
		return (Casilla) q.getSingleResult();
	}

	@Override
	public Collection<MensajeVO> listarMensajesPorCasilla(String direccion) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccion, em);
			ArrayList<MensajeVO> mensajes = new ArrayList<MensajeVO>();
			for (CasillaMensaje cm : casilla.getMensajes()) {
				MensajeVO m = new MensajeVO();
				m.setFecha(cm.getMensaje().getFecha());
				m.setAsunto(cm.getMensaje().getAsunto());
				m.setCuerpo(cm.getMensaje().getCuerpo());
				m.setTipo(cm.getMensaje().getTipo());

				m.setOrigen(cm.getMensaje().getOrigen().getDireccion());
				for (CasillaMensaje destino : cm.getMensaje().getDestinos()) {
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
	public void enviarMensaje(String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casillaOrigen = buscarCasillaPorDireccion(origen, em);

			EntityTransaction transaction = em.getTransaction();
			try {

				Mensaje mensaje = new Mensaje();
				mensaje.setFecha(new Date());
				mensaje.setAsunto(asunto);
				mensaje.setCuerpo(cuerpo);
				mensaje.setTipo(tipo);
				mensaje.setOrigen(casillaOrigen);
				
				em.persist(mensaje);

				CasillaMensaje casillaMensaje;

				casillaMensaje = new CasillaMensaje();
				casillaMensaje.setCasilla(casillaOrigen);
				casillaMensaje.setMensaje(mensaje);
				casillaMensaje.setEstado(MensajeEstado.Enviado);

				em.persist(casillaMensaje);

				for (String s : destinos) {
					Casilla casillaDestino = buscarCasillaPorDireccion(s, em);

					if (casillaDestino.getUsuario().getCasillasBloqueadas().contains(casillaOrigen)) {
						Mensaje mensajeBloqueo = new Mensaje();
						mensajeBloqueo.setFecha(new Date());
						mensajeBloqueo.setAsunto("Aviso de Bloqueo");
						mensajeBloqueo.setCuerpo(String.format("Su mensaje con asunto \"%s\", enviado a \"%s\", no se puede enviar porque el usuario bloqueó la recepción desde esta casilla.", asunto, s));
						mensajeBloqueo.setTipo(tipo);
						mensajeBloqueo.setOrigen(null);
						em.persist(mensajeBloqueo);
					} else {
						casillaMensaje = new CasillaMensaje();
						casillaMensaje.setCasilla(casillaDestino);
						casillaMensaje.setMensaje(mensaje);
						casillaMensaje.setEstado(MensajeEstado.NoLeido);
						em.persist(casillaMensaje);
					}
				}
				
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
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
	public boolean validarLogin(String direccion, String password) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			return buscarCasillaPorDireccion(direccion, em).getPassword().equals(password);
		} finally {
			em.close();
		}
	}

	@Override
	public void cambiarPassword(String direccion, String password) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = buscarCasillaPorDireccion(direccion, em);
			casilla.setPassword(password);
			em.persist(casilla);
		} finally {
			em.close();
		}
	}

}
