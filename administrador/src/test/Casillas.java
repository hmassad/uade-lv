package test;

import java.rmi.RemoteException;
import java.util.Collection;

import beans.CasillaVO;
import beans.UsuarioVO;
import controlador.ControladorGestion;

public class Casillas {

	public static void main(String[] args) {
		Casillas c = new Casillas();
		c.testObtenerCasillasPorUsuario();
	}

	private ControladorGestion controladorGestion;

	public Casillas() {
		controladorGestion = new ControladorGestion();
	}

	public void testObtenerCasillas() {
		try {
			Collection<CasillaVO> casillas = controladorGestion.obtenerCasillas();
			for (CasillaVO casilla : casillas) {
				System.out.println(casilla);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testObtenerCasillasPorUsuario() {
		try {
			UsuarioVO u = new UsuarioVO();
			u.setId(2);
			Collection<CasillaVO> casillas = controladorGestion.obtenerCasillasPorUsuario(u);
			for (CasillaVO casilla : casillas) {
				System.out.println(casilla);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
