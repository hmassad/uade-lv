package enities;

import javax.persistence.Entity;

@Entity
public class RelacionConfianza {
	private Oficina origen;
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
