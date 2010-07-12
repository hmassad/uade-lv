package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MensajeEnCasillaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Casilla casilla;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Mensaje mensaje;

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MensajeEnCasillaPk that = (MensajeEnCasillaPk) o;

		if (mensaje != null ? !mensaje.equals(that.mensaje) : that.mensaje != null)
			return false;
		if (casilla != null ? !casilla.equals(that.casilla) : that.casilla != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (mensaje != null ? mensaje.hashCode() : 0);
		result = 31 * result + (casilla != null ? casilla.hashCode() : 0);
		return result;
	}
}
