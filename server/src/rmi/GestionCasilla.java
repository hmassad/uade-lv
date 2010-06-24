package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import beans.CasillaVO;

public class GestionCasilla extends UnicastRemoteObject implements TDAManejoCasilla {

	/**
	 * 
	 */
	private Set<CasillaVO> casillas;
	
	private static final long serialVersionUID = 1L;

	protected GestionCasilla() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void borrarCasilla(String id) throws RemoteException {
		// TODO Auto-generated method stub
		CasillaVO aux;
		for (Iterator<CasillaVO> i = casillas.iterator(); i.hasNext();) {
			aux = i.next();
			if (aux.getId() == id) {
				i.remove();
				return;
			}
		}
		throw new RemoteException("No encontre la casilla");
	}

	@Override
	public CasillaVO obtenerCasilla(String id) throws RemoteException {
		// TODO Auto-generated method stub
		CasillaVO aux;
		for (Iterator<CasillaVO> i = casillas.iterator(); i.hasNext();) {
			aux = i.next();
			if (aux.getId() == id) {
				return aux;
				
			}
		}
		throw new RemoteException("No encontre la casilla");
	}

	@Override
	public Collection<CasillaVO> obtenerCasillas() throws RemoteException {
		// TODO Auto-generated method stub
		return casillas;
	}
	

}
