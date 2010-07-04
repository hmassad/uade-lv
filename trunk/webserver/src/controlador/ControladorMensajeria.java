package controlador;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;

import rmi.InterfazMensajeria;
import beans.CasillaVO;
import beans.MensajeVO;
import enums.MensajeEstado;
import enums.MensajeTipo;

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

	public void enviarMensaje(Integer id, String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) {
		try {
			mensajeria.enviarMensaje(id, origen, destinos, asunto, cuerpo, tipo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void guardarMensaje(Integer id, String direccion, String asunto, String cuerpo, MensajeTipo tipo) {
		try {
			mensajeria.guardarMensaje(id, direccion, asunto, cuerpo, tipo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public Collection<String> listarDireccionesPosibles(String direccion) {
		try {
			return mensajeria.listarDireccionesPosibles(direccion);
		} catch (RemoteException e) {
			System.err.println("Ocurrió un error al listar las direcciones posibles.");
			e.printStackTrace();
			return null;
		}
	}
}
