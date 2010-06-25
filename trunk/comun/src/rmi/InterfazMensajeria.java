package rmi;

import java.rmi.Remote;

import beans.MensajeVO;

public interface InterfazMensajeria extends Remote {

	public void enviarMensaje(MensajeVO mensaje);

}
