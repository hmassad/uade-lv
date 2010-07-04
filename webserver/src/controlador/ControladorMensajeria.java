package controlador;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;

import enums.MensajeEstado;

import rmi.InterfazMensajeria;
import beans.CasillaVO;
import beans.MensajeVO;

public class ControladorMensajeria {

	private InterfazMensajeria mensajeria;

	public ControladorMensajeria() throws MalformedURLException, RemoteException, NotBoundException {
		mensajeria = (InterfazMensajeria) Naming.lookup("//localhost/mensajeria");
	}

	public boolean validarLogin(String direccion, String password) throws RemoteException {
		try {
			return mensajeria.validarLogin(direccion, password);
		} catch (RemoteException e) {
			return false;
		}
	}

	public void cambiarPassword(String usuario, String password) throws RemoteException {
		mensajeria.cambiarPassword(usuario, password);
	}

	public Collection<CasillaVO> listarCasillasPorUsuario(String usuario) throws RemoteException {
		return mensajeria.listarCasillasPorUsuario(usuario);
	}

	public Collection<MensajeVO> listarMensajesPorCasilla(String direccion) throws RemoteException {
		return mensajeria.listarMensajesPorCasilla(direccion);
	}

	public Collection<MensajeVO> listarMensajesPorCasillaPorEstado(String direccion, MensajeEstado estado) throws RemoteException {
		return mensajeria.listarMensajesPorCasillaPorEstado(direccion, estado);
	}
}
