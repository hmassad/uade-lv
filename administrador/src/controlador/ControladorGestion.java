package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import observer.Observable;
import rmi.InterfazGestion;
import beans.CasillaVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
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

	public Collection<CasillaVO> obtenerCasillas() {
		try {
			return gestion.obtenerCasillas();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<UsuarioVO> obtenerUsuarios() {
		try {
			return gestion.obtenerUsuarios();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<CasillaVO> obtenerCasillasPorUsuario(UsuarioVO u) {
		try {
			return gestion.obtenerCasillasPorUsuario(u);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void agregarUsuario(UsuarioVO u) {
		try {
			gestion.agregarUsuario(u);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void agregarCasillaAUsuario(UsuarioVO u, CasillaVO c) {
		try {
			gestion.agregarCasillaAUsuario(u, c);
			// this.notifyObservers(this.listarAlumno());

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void eliminarUsuario(int id) {
		try {
			gestion.borrarUsuario(new UsuarioVO());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() {
		try {
			return gestion.obtenerRelacionesConfianza();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<OficinaVO> obtenerOficinas() {
		try {
			return gestion.obtenerOficinas();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void eliminarOficina(int id) {
		// TODO Auto-generated method stub

	}

	public void eliminarCasilla(int id) {
		// TODO Auto-generated method stub

	}

	public void eliminarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) {
		// TODO Auto-generated method stub

	}
}
