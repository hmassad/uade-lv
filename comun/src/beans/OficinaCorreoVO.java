package beans;

import java.util.Vector;

public class OficinaCorreoVO {

	private String nombre;
	private Vector<CasillaVO> casillas;
	private Vector<RelacionConfianzaVO> relacionesConfianza;

	public OficinaCorreoVO(String nombre) {
		this.nombre = nombre;
		this.casillas = new Vector<CasillaVO>();
		this.relacionesConfianza = new Vector<RelacionConfianzaVO>();
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

	public Vector<RelacionConfianzaVO> getRelacionesConfianza() {
		return relacionesConfianza;
	}
}
