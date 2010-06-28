package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import observer.Observable;
import rmi.InterfazGestion;
import beans.CasillaVO;
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

	public Collection<UsuarioVO> obtenerUsuarios() throws RemoteException {
		return gestion.obtenerUsuarios();
	}

	public Collection<CasillaVO> obtenerCasillasPorUsuario(UsuarioVO u) throws RemoteException {
		return gestion.obtenerCasillasPorUsuario(u);
	}

	public void agregarUsuario(UsuarioVO u) throws RemoteException {
		gestion.agregarUsuario(u);
	}

	public void agregarCasillaAUsuario(UsuarioVO u, CasillaVO c) {
		try {
			gestion.agregarCasillaAUsuario(u, c);
			// this.notifyObservers(this.listarAlumno());

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
