package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Casilla;
import entities.Mensaje;

import beans.CasillaVO;
import beans.MensajeVO;

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
			Mensaje mensaje = new Mensaje();
			mensaje.setFecha(m.getFecha());
			mensaje.setEstado(m.getEstado());
			mensaje.setTipo(m.getTipo());
			mensaje.setAsunto(m.getAsunto());
			mensaje.setCuerpo(m.getCuerpo());
			Casilla remitente = em.find(Casilla.class, m.getCasillaRemitente().getId());
			for(CasillaVO c : m.getCasillasDestinatarios()){
				Casilla casilla = em.find(Casilla.class, c.getId());
				mensaje.agregarCasillaDestinatario(casilla);
			}
		} finally {
			em.close();
		}
	}

}
