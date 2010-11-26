package entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

/**
 * @author  hmassad
 */
@Entity
@AssociationOverrides( {
	@AssociationOverride(name = "pk.origen", joinColumns = { @JoinColumn(name = "casilla_origen_id", nullable = false) }),
	@AssociationOverride(name = "pk.destino", joinColumns = { @JoinColumn(name = "casilla_destino_id", nullable = false) }) 
})
public class RelacionConfianza implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="pk"
	 * @uml.associationEnd  
	 */
	@EmbeddedId
	private RelacionConfianzaPk pk = new RelacionConfianzaPk();

	/**
	 * @return
	 * @uml.property  name="pk"
	 */
	private RelacionConfianzaPk getPk() {
		return pk;
	}

	/**
	 * @param pk
	 * @uml.property  name="pk"
	 */
	@SuppressWarnings("unused")
	private void setPk(RelacionConfianzaPk pk) {
		this.pk = pk;
	}

	@Transient
	public Oficina getOrigen() {
		return getPk().getOrigen();
	}

	public void setOrigen(Oficina origen) {
		getPk().setOrigen(origen);
	}

	@Transient
	public Oficina getDestino() {
		return getPk().getDestino();
	}

	public void setDestino(Oficina destino) {
		getPk().setDestino(destino);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		RelacionConfianza that = (RelacionConfianza) obj;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "[origen: " + getOrigen() + "; destino: " + getDestino() + "]";
	}
}
