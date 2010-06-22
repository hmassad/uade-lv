package beans;

public class RelacionConfianzaVO {

	private OficinaCorreoVO origen;
	private OficinaCorreoVO destino;

	public RelacionConfianzaVO(OficinaCorreoVO origen, OficinaCorreoVO destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public OficinaCorreoVO getOrigen() {
		return origen;
	}

	public void setOrigen(OficinaCorreoVO origen) {
		this.origen = origen;
	}

	public OficinaCorreoVO getDestino() {
		return destino;
	}

	public void setDestino(OficinaCorreoVO destino) {
		this.destino = destino;
	}

}
