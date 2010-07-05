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

	public boolean validarLogin(String direccion, String password) {
		try {
			return mensajeria.validarLogin(direccion, password);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void cambiarPassword(String usuario, String password) {
		try {
			mensajeria.cambiarPassword(usuario, password);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Collection<CasillaVO> listarCasillasPorUsuario(String usuario) {
		try {
			return mensajeria.listarCasillasPorUsuario(usuario);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Collection<MensajeVO> listarMensajesPorCasilla(String direccion) {
		try {
			return mensajeria.listarMensajesPorCasilla(direccion);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Collection<MensajeVO> listarMensajesPorCasillaPorEstado(String direccion, MensajeEstado estado) {
		try {
			return mensajeria.listarMensajesPorCasillaPorEstado(direccion, estado);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Collection<MensajeVO> listarMensajesPorUsuario(String usuario) {
		try {
			return mensajeria.listarMensajesPorUsuario(usuario);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Collection<MensajeVO> listarMensajesPorUsuarioPorEstado(String usuario, MensajeEstado estado) {
		try {
			return mensajeria.listarMensajesPorUsuarioPorEstado(usuario, estado);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean enviarMensaje(Integer id, String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) {
		try {
			mensajeria.enviarMensaje(id, origen, destinos, asunto, cuerpo, tipo);
			return true;
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void guardarMensaje(Integer id, String direccion, String asunto, String cuerpo, MensajeTipo tipo) {
		try {
			mensajeria.guardarMensaje(id, direccion, asunto, cuerpo, tipo);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Collection<String> listarDireccionesPosibles(String direccion) {
		try {
			return mensajeria.listarDireccionesPosibles(direccion);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public Collection<String> listarDireccionesPosiblesQueComiencenCon(String direccion, String comienzo) {
		try {
			return mensajeria.listarDireccionesPosiblesQueComiencenCon(direccion, comienzo);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public MensajeVO obtenerMensaje(String direccionCasilla, int idMensaje) {
		try {
			return mensajeria.obtenerMensaje(direccionCasilla, idMensaje);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void cambiarMensajeEstado(String direccionCasilla, int idMensaje, MensajeEstado estado) {
		try {
			mensajeria.cambiarMensajeEstado(direccionCasilla, idMensaje, estado);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
