package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.CasillaVO;
import beans.MensajeVO;

public interface InterfazMensajeria extends Remote {

	void enviarMensaje(MensajeVO mensaje) throws RemoteException;

	Collection<MensajeVO> listarMensajesPorCasilla(CasillaVO c) throws RemoteException;

}
