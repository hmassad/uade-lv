package beans;

import java.util.Vector;

public class CasillaVO {

	private int id;
	private String password;
	private String direccion;
	private Vector<MensajeVO> mensajes;

	public CasillaVO() {
		this.mensajes = new Vector<MensajeVO>();
	}

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

	public Vector<MensajeVO> getMensajes(){
		return mensajes;
	}
}
