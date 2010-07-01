package beans;

import java.io.Serializable;

public class CasillaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String password;
	private String direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString(){
		return String.format("Casilla(id: %s, password: %s, direccion: %s)", getId(), getPassword(), getDireccion());
	}
}
