package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import rmi.InterfazMensajeria;
import beans.CasillaVO;
import beans.MensajeVO;
import beans.UsuarioVO;

public class ControladorMensajeria {

	private InterfazMensajeria mensajeria;

	public ControladorMensajeria() {
		try {
			mensajeria = (InterfazMensajeria) Naming.lookup("//localhost/mensajeria");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validarUsuarioCasilla(String nombreUsuario, String direccionCasilla, String contraseñaCasilla) {
		UsuarioVO u = new UsuarioVO();
		u.setNombre(nombreUsuario);

		CasillaVO c = new CasillaVO();
		c.setDireccion(direccionCasilla);
		c.setPassword(contraseñaCasilla);

		// return mensajeria.validarUsuarioCasilla(u, c);

		// TODO Auto-generated method stub
		return true;
	}

	public Collection<MensajeVO> obtenerMensajesPorCasilla(String direccion) throws RemoteException {
		CasillaVO c = new CasillaVO();
		c.setDireccion(direccion);
		return mensajeria.listarMensajesPorCasilla(c);
	}
}
