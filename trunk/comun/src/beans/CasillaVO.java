package beans;

import java.io.Serializable;

public class CasillaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String direccion;

	public CasillaVO() {
	}

	public CasillaVO(int id, String direccion) {
		this.id = id;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

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
