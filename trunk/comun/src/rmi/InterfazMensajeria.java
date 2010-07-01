package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.MensajeVO;
import enums.MensajeTipo;

public interface InterfazMensajeria extends Remote {

	boolean validarLogin(String direccion, String password) throws RemoteException; 

	void cambiarPassword(String direccion, String password) throws RemoteException;
	
	Collection<MensajeVO> listarMensajesPorCasilla(String direccion) throws RemoteException;

	void enviarMensaje(String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException;

	Collection<String> listarDireccionesPosibles(String direccion) throws RemoteException;
}
