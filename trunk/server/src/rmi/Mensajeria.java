package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import beans.CasillaVO;
import beans.MensajeVO;
import entities.Casilla;
import entities.Mensaje;
import enums.MensajeEstado;

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

	@Override
	public Collection<MensajeVO> listarMensajesPorCasilla(CasillaVO c) throws RemoteException {
		throw new RemoteException("No Implementado.");
	}

	@Override
	public void enviarMensaje(MensajeVO m) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
//			Mensaje mensaje = new Mensaje();
//			mensaje.setFecha(m.getFecha());
//			mensaje.setTipo(m.getTipo());
//			mensaje.setAsunto(m.getAsunto());
//			mensaje.setCuerpo(m.getCuerpo());
//
//			Casilla casillaRemitente = em.find(Casilla.class, m.getCasillaRemitente().getId());
//			mensaje.agregarCasilla(casillaRemitente, MensajeEstado.Enviado);
//
//			for (CasillaVO c : m.getCasillasDestinatarios()) {
//				Casilla casillaDestinatario = em.find(Casilla.class, c.getId());
//				mensaje.agregarCasilla(casillaDestinatario, MensajeEstado.NoLeido);
//
//				if (casillaRemitente.puedeEnviar(casillaDestinatario)) {
//					casillaDestinatario.agregarMensaje(mensaje);
//				}
//			}
//
//			EntityTransaction tx = em.getTransaction();
//			try {
//				tx.begin();
//				em.persist(mensaje);
//
////				for (Casilla c : mensaje.getCasillasDestinatarios()) {
////					em.persist(c);
////				}
//
//				tx.commit();
//			} catch (Exception e) {
//				tx.rollback();
//			}
		} finally {
			em.close();
		}
	}

}
