package beans;

import java.util.Vector;

public class UsuarioVO {

	private String nombre;
	private Vector<CasillaVO> casillas;

	public UsuarioVO(String nombre) {
		this.nombre = nombre;
		this.casillas = new Vector<CasillaVO>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Vector<CasillaVO> getCasillas() {
		return casillas;
	}
}
