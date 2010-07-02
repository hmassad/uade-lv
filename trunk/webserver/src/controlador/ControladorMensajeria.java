package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import rmi.InterfazMensajeria;
import beans.CasillaVO;
import beans.MensajeVO;

public class ControladorMensajeria {

	private InterfazMensajeria mensajeria;

	public ControladorMensajeria() {
		try {
			mensajeria = (InterfazMensajeria) Naming.lookup("//localhost/mensajeria");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validarLogin(String direccion, String password) {
		try {
			return mensajeria.validarLogin(direccion, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Collection<MensajeVO> obtenerMensajesPorCasilla(String direccion) {
		CasillaVO c = new CasillaVO();
		c.setDireccion(direccion);
		try {
			return mensajeria.listarMensajesPorCasilla(direccion);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
