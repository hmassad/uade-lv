package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author  hmassad
 */
public class LogTraficoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="fecha"
	 */
	private Date fecha;
	/**
	 * @uml.property  name="origen"
	 */
	private String origen;
	/**
	 * @uml.property  name="destinos"
	 */
	private Collection<String> destinos = new ArrayList<String>();

	/**
	 * @return
	 * @uml.property  name="fecha"
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 * @uml.property  name="fecha"
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return
	 * @uml.property  name="origen"
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param direccionOrigen
	 * @uml.property  name="origen"
	 */
	public void setOrigen(String direccionOrigen) {
		this.origen = direccionOrigen;
	}

	/**
	 * @return
	 * @uml.property  name="destinos"
	 */
	public Collection<String> getDestinos() {
		return destinos;
	}

	/**
	 * @param destinos
	 * @uml.property  name="destinos"
	 */
	public void setDestinos(Collection<String> destinos) {
		this.destinos = destinos;
	}

	@Override
	public boolean equals(Object arg) {
		return this.getFecha().equals(((LogTraficoVO) arg).getFecha()) && this.getOrigen().equals(((LogTraficoVO) arg).getOrigen()) && this.getDestinos().equals(((LogTraficoVO) arg).getDestinos());
	}

	@Override
	public String toString() {
		return String.format("[fecha: %s, origen: %s, destinos: %s]", new SimpleDateFormat("dd-MM-yy HH:mm").format(getFecha()), getOrigen(), getDestinos());
	}

	public void agregarDestino(String destino) {
		destinos.add(destino);
	}

	public void borrarDestino(String destino) {
		destinos.remove(destino);
	}
}
