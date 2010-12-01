package beans;

import java.io.Serializable;


public class OficinaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="nombre"
	 */
	private String nombre;

	public OficinaVO() {

	}

	public OficinaVO(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 * @uml.property  name="nombre"
	 */
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
