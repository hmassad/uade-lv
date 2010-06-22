package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import beans.CasillaVO;

public interface TDAManejoCasilla extends Remote {

	public Set<CasillaVO> obtenerCasillas() throws RemoteException;

	public CasillaVO obtenerCasilla(String id) throws RemoteException;

	public void borrarCasilla(String id) throws RemoteException;
}
