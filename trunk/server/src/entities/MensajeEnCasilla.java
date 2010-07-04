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

@Entity
@AssociationOverrides( {
	@AssociationOverride(name = "pk.casilla", joinColumns = {@JoinColumn(name = "casilla_id", nullable = false)}),
	@AssociationOverride(name = "pk.mensaje", joinColumns = {@JoinColumn(name = "mensaje_id", nullable = false)})
})
public class MensajeEnCasilla implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MensajeEnCasillaPk pk = new MensajeEnCasillaPk();

	@Enumerated(EnumType.STRING)
	private MensajeEstado estado;

	@SuppressWarnings("unused")
	private void setPk(MensajeEnCasillaPk pk) {
		this.pk = pk;
	}

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

	public MensajeEstado getEstado() {
		return estado;
	}

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
