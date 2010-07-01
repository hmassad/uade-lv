package entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@AssociationOverrides( {
	@AssociationOverride(name = "pk.origen", joinColumns = { @JoinColumn(name = "casilla_origen_id", nullable = false) }),
	@AssociationOverride(name = "pk.destino", joinColumns = { @JoinColumn(name = "casilla_destino_id", nullable = false) }) 
})
public class RelacionConfianza implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RelacionConfianzaPk pk = new RelacionConfianzaPk();

	private RelacionConfianzaPk getPk() {
		return pk;
	}

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

	public String toString() {
		return "RelacionConfianza(Origen: " + getOrigen() + "; Destino: " + getDestino() + ")";
	}
}
