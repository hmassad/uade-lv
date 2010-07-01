package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class LogTraficoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fecha;
	private String origen;
	private Collection<String> destinos;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String direccionOrigen) {
		this.origen = direccionOrigen;
	}

	public Collection<String> getDestinos() {
		return destinos;
	}

	public void setDestinos(Collection<String> destinos) {
		this.destinos = destinos;
	}

	@Override
	public String toString() {
		return String.format("LogTrafico(fecha: %s, origen: %s, destinos: %s)", getFecha(), getOrigen(), getDestinos());
	}
}
