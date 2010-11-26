package beans;

import java.io.Serializable;

/**
 * @author  hmassad
 */
public class RelacionConfianzaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="origen"
	 * @uml.associationEnd  
	 */
	private OficinaVO origen;
	/**
	 * @uml.property  name="destino"
	 * @uml.associationEnd  
	 */
	private OficinaVO destino;

	public RelacionConfianzaVO() {
	}

	public RelacionConfianzaVO(OficinaVO origen, OficinaVO destino) {
		this.origen = origen;
		this.destino = destino;
	}

	/**
	 * @return
	 * @uml.property  name="origen"
	 */
	public OficinaVO getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 * @uml.property  name="origen"
	 */
	public void setOrigen(OficinaVO origen) {
		this.origen = origen;
	}

	/**
	 * @return
	 * @uml.property  name="destino"
	 */
	public OficinaVO getDestino() {
		return destino;
	}

	/**
	 * @param destino
	 * @uml.property  name="destino"
	 */
	public void setDestino(OficinaVO destino) {
		this.destino = destino;
	}

	@Override
	public boolean equals(Object arg) {
		RelacionConfianzaVO otra = (RelacionConfianzaVO) arg;
		return this.getOrigen().equals(otra.getOrigen()) && this.getDestino().equals(otra.getDestino());
	}

	@Override
	public String toString() {
		return String.format("'%s' a '%s'", origen.getNombre(), destino.getNombre());
	}
}
