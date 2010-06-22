package beans;

public class RelacionConfianzaVO {

	private OficinaVO origen;
	private OficinaVO destino;

	public RelacionConfianzaVO(OficinaVO origen, OficinaVO destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public OficinaVO getOrigen() {
		return origen;
	}

	public void setOrigen(OficinaVO origen) {
		this.origen = origen;
	}

	public OficinaVO getDestino() {
		return destino;
	}

	public void setDestino(OficinaVO destino) {
		this.destino = destino;
	}

}
