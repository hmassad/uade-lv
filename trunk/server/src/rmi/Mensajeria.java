package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import beans.MensajeVO;

public class Mensajeria extends UnicastRemoteObject implements
		InterfazMensajeria {

	private static final long serialVersionUID = 1L;

	public Mensajeria() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enviarMensaje(MensajeVO mensaje) {
		// TODO Auto-generated method stub
		
	}

}
