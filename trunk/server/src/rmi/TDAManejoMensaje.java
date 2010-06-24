package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.MensajeVO;

public interface TDAManejoMensaje extends Remote {
		
	public Collection<MensajeVO> obtenerMensajes() throws RemoteException;

	public MensajeVO obtenerCasilla(String id) throws RemoteException;

	public void borrarMensaje(String id) throws RemoteException;



}
