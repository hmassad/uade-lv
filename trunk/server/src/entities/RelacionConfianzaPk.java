package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RelacionConfianzaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Oficina origen;

	@ManyToOne
	private Oficina destino;

	public Oficina getOrigen() {
		return origen;
	}

	public void setOrigen(Oficina origen) {
		this.origen = origen;
	}

	public Oficina getDestino() {
		return destino;
	}

	public void setDestino(Oficina destino) {
		this.destino = destino;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RelacionConfianzaPk that = (RelacionConfianzaPk) o;

		if (origen != null ? !origen.equals(that.origen) : that.origen != null)
			return false;
		if (destino != null ? !destino.equals(that.destino) : that.destino != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (origen != null ? origen.hashCode() : 0);
		result = 31 * result + (destino != null ? destino.hashCode() : 0);
		return result;
	}
}
