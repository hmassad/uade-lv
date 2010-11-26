package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author  hmassad
 */
@Embeddable
public class RelacionConfianzaPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="origen"
	 * @uml.associationEnd  
	 */
	@ManyToOne
	private Oficina origen;

	/**
	 * @uml.property  name="destino"
	 * @uml.associationEnd  
	 */
	@ManyToOne
	private Oficina destino;

	/**
	 * @return
	 * @uml.property  name="origen"
	 */
	public Oficina getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 * @uml.property  name="origen"
	 */
	public void setOrigen(Oficina origen) {
		this.origen = origen;
	}

	/**
	 * @return
	 * @uml.property  name="destino"
	 */
	public Oficina getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 * @uml.property  name="destino"
	 */
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
