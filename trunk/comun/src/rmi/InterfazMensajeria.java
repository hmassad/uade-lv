package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import beans.MensajeVO;

public interface InterfazMensajeria extends Remote {

	public void enviarMensaje(MensajeVO mensaje) throws RemoteException;

}
