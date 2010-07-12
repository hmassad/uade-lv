package beans;

import java.io.Serializable;

public class OficinaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;

	public OficinaVO() {

	}

	public OficinaVO(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object arg) {
		OficinaVO other = (OficinaVO) arg;
		return this.getId() == other.getId();
	}

	@Override
	public String toString() {
		return getNombre();
	}
}
