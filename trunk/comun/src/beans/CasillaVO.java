package beans;

import java.util.Vector;

public class CasillaVO {

	private String id;
	private String password;
	private Vector<MensajeVO> mensajes;

	public CasillaVO(String id, String password) {
		this.id = id;
		this.password = password;
		this.mensajes = new Vector<MensajeVO>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Vector<MensajeVO> getMensajes(){
		return mensajes;
	}
}
