package test;

import java.rmi.RemoteException;

import beans.UsuarioVO;
import controlador.ControladorGestion;

public class TestUsuario {

	public static void main(String[] args) {
		ControladorGestion cg = new ControladorGestion();
		try {
			for (UsuarioVO usuario : cg.obtenerUsuarios()) {
				System.out.println(usuario.toString());
			}

			UsuarioVO u = new UsuarioVO();
			u.setNombre("El 5");
			cg.agregarUsuario(u);

			for (UsuarioVO usuario : cg.obtenerUsuarios()) {
				System.out.println(usuario.toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
