package test;

import java.rmi.RemoteException;
import java.util.Collection;

import beans.UsuarioVO;
import controlador.ControladorGestion;

public class Usuario {

	public static void main(String[] args) {
		ControladorGestion cg = new ControladorGestion();
		try {
			Collection<UsuarioVO> usuarios = cg.obtenerUsuarios();
			for (UsuarioVO usuario : usuarios) {
				System.out.println(usuario.toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
