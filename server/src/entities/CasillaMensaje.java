package entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import enums.MensajeEstado;

@Entity
@Table(name = "Casilla_Mensaje")
@AssociationOverrides( {
	@AssociationOverride(name = "pk.casilla", joinColumns = {@JoinColumn(name = "casilla_id", nullable = false)}),
	@AssociationOverride(name = "pk.mensaje", joinColumns = {@JoinColumn(name = "mensaje_id", nullable = false)})
})
public class CasillaMensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CasillaMensajePk pk = new CasillaMensajePk();

	@Enumerated(EnumType.STRING)
	private MensajeEstado estado;

	@SuppressWarnings("unused")
	private void setPk(CasillaMensajePk pk) {
		this.pk = pk;
	}

	private CasillaMensajePk getPk() {
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

		CasillaMensaje that = (CasillaMensaje) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
