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

	public Collection<CasillaVO> obtenerCasillasPorUsuario(int idUsuario) {
		try {
			return gestion.obtenerCasillasPorUsuario(idUsuario);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void agregarUsuario(String nombreUsuario, String password) {
		try {
			gestion.agregarUsuario(nombreUsuario, password);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void agregarOficina(String nombre) {
		try {
			gestion.agregarOficina(nombre);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void agregarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) {
		try {
			gestion.agregarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void agregarCasilla(int idUsuario, String direccion) {
		try {
			gestion.agregarCasilla(idUsuario, direccion);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void eliminarUsuario(int id) {
		try {
			gestion.borrarUsuario(id);
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
		try {
			gestion.borrarOficina(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void eliminarCasilla(int id) {
		try {
			gestion.borrarCasilla(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void eliminarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) {
		try {
			gestion.borrarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
