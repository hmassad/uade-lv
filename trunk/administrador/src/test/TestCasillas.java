package test;

import java.rmi.RemoteException;
import java.util.Collection;

import beans.CasillaVO;
import beans.UsuarioVO;
import controlador.ControladorGestion;

public class TestCasillas {

	public static void main(String[] args) {
		TestCasillas c = new TestCasillas();
		c.testAgregarCasillaAUsuario();
	}

	private ControladorGestion controladorGestion;

	public TestCasillas() {
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
			u.setId(1);
			Collection<CasillaVO> casillas = controladorGestion.obtenerCasillasPorUsuario(u);
			for (CasillaVO casilla : casillas) {
				System.out.println(casilla);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testAgregarCasillaAUsuario() {
		try {
			UsuarioVO u = new UsuarioVO();
			u.setId(1);

			System.out.println("Antes");
			
			for (CasillaVO casilla : controladorGestion.obtenerCasillasPorUsuario(u)) {
				System.out.println(casilla);
			}
			
			System.out.println("---");

			CasillaVO c = new CasillaVO();
			c.setDireccion("juan");
			c.setPassword("password");
			controladorGestion.agregarCasillaAUsuario(u, c);
			
			System.out.println("---");
			
			System.out.println("Después");
			for (CasillaVO casilla : controladorGestion.obtenerCasillasPorUsuario(u)) {
				System.out.println(casilla);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
