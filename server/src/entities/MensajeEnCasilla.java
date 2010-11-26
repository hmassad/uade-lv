package entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import enums.MensajeEstado;

/**
 * @author  hmassad
 */
@Entity
@AssociationOverrides( {
	@AssociationOverride(name = "pk.casilla", joinColumns = {@JoinColumn(name = "casilla_id", nullable = false)}),
	@AssociationOverride(name = "pk.mensaje", joinColumns = {@JoinColumn(name = "mensaje_id", nullable = false)})
})
public class MensajeEnCasilla implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="pk"
	 * @uml.associationEnd  
	 */
	@EmbeddedId
	private MensajeEnCasillaPk pk = new MensajeEnCasillaPk();

	/**
	 * @uml.property  name="estado"
	 * @uml.associationEnd  
	 */
	@Enumerated(EnumType.STRING)
	private MensajeEstado estado;

	/**
	 * @param pk
	 * @uml.property  name="pk"
	 */
	@SuppressWarnings("unused")
	private void setPk(MensajeEnCasillaPk pk) {
		this.pk = pk;
	}

	/**
	 * @return
	 * @uml.property  name="pk"
	 */
	private MensajeEnCasillaPk getPk() {
		return pk;
	}

	@Transient
	public Casilla getCasilla() {
		return getPk().getCasilla();
	}

	public void setCasilla(Casilla casilla) {
		getPk().setCasilla(casilla);
	}

	@Transient
	public Mensaje getMensaje() {
		return getPk().getMensaje();
	}

	public void setMensaje(Mensaje mensaje) {
		getPk().setMensaje(mensaje);
	}

	/**
	 * @return
	 * @uml.property  name="estado"
	 */
	public MensajeEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 * @uml.property  name="estado"
	 */
	public void setEstado(MensajeEstado estado) {
		this.estado = estado;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MensajeEnCasilla that = (MensajeEnCasilla) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
