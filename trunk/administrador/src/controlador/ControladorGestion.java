package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import observer.Observable;
import rmi.InterfazGestion;
import beans.CasillaVO;
import beans.OficinaVO;
import beans.UsuarioVO;

public class ControladorGestion extends Observable {

	private InterfazGestion gestion;

	public ControladorGestion() {
		try {
			gestion = (InterfazGestion) Naming.lookup("//localhost/gestion");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<CasillaVO> obtenerCasillas() throws RemoteException {
		return gestion.obtenerCasillas();
	}

	public void agregarCasilla(UsuarioVO usuario, Collection<OficinaVO> oficinas, CasillaVO casilla) {
		try {
			gestion.agregarCasilla(usuario, oficinas, casilla);
			// this.notifyObservers(this.listarAlumno());

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public Collection<UsuarioVO> obtenerUsuarios() throws RemoteException {
		return gestion.obtenerUsuarios();
	}

	public Collection<CasillaVO> obtenerCasillasPorUsuario(UsuarioVO u) throws RemoteException {
		return gestion.obtenerCasillasPorUsuario(u);
	}
}
