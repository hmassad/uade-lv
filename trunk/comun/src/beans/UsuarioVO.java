package beans;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;

	public UsuarioVO() {
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
	public String toString() {
		return "Usuario(ID: " + getId() + "; Nombre: " + getNombre() + ")";
	}
}
