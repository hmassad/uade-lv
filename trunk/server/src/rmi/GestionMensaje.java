package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import beans.MensajeVO;

public class GestionMensaje extends UnicastRemoteObject implements TDAManejoMensaje {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestionMensaje() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void borrarMensaje(String id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MensajeVO obtenerCasilla(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<MensajeVO> obtenerMensajes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
