package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelacionConfianza {

	@Id
	private Oficina origen;
	@Id
	private Oficina destino;

	public RelacionConfianza() {

	}

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

	public String toString() {
		return "RelacionConfianza(Origen: " + getOrigen() + "; Destino: " + getDestino() + ")";
	}
}
