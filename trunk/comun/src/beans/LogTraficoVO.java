package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class LogTraficoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fecha;
	private String origen;
	private Collection<String> destinos = new ArrayList<String>();

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
