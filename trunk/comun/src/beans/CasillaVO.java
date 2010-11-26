package beans;

import java.io.Serializable;

/**
 * @author  hmassad
 */
public class CasillaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	private int id;
	/**
	 * @uml.property  name="direccion"
	 */
	private String direccion;

	public CasillaVO() {
	}

	public CasillaVO(int id, String direccion) {
		this.id = id;
		this.direccion = direccion;
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
	 * @uml.property  name="direccion"
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 * @uml.property  name="direccion"
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public boolean equals(Object arg) {
		return this.getId() == ((CasillaVO) arg).getId();
	}

	@Override
	public String toString() {
		return getDireccion();
	}
}
