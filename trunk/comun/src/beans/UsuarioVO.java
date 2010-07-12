package beans;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String password;

	public UsuarioVO() {
	}

	public UsuarioVO(int id, String nombre, String password) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UsuarioVO) {
			UsuarioVO otro = (UsuarioVO) obj;
			return this.getId() == otro.getId() || this.getNombre().equalsIgnoreCase(otro.getNombre());
		}
		return false;
	}

	@Override
	public String toString() {
		return getNombre();
	}
}