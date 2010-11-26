package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author  hmassad
 */
@Embeddable
public class MensajeEnCasillaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="casilla"
	 * @uml.associationEnd  
	 */
	@ManyToOne
	private Casilla casilla;

	/**
	 * @uml.property  name="mensaje"
	 * @uml.associationEnd  
	 */
	@ManyToOne(cascade = { CascadeType.ALL })
	private Mensaje mensaje;

	/**
	 * @return
	 * @uml.property  name="casilla"
	 */
	public Casilla getCasilla() {
		return casilla;
	}

	/**
	 * @param casilla
	 * @uml.property  name="casilla"
	 */
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	/**
	 * @return
	 * @uml.property  name="mensaje"
	 */
	public Mensaje getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 * @uml.property  name="mensaje"
	 */
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
