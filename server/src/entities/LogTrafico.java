package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionOfElements;

@Entity
public class LogTrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private Date fecha;
	private String origen;

	@CollectionOfElements(targetElement = String.class, fetch = FetchType.LAZY)
	private Collection<String> destinos = new ArrayList<String>();

	private String mensaje;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Collection<String> getDestinos() {
		return destinos;
	}

	public void setDestinos(Collection<String> destinos) {
		this.destinos = destinos;
	}

	public void agregarDestino(String casillaDestino) {
		getDestinos().add(casillaDestino);
	}

	public void removerDestino(String casillaDestino) {
		getDestinos().remove(casillaDestino);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LogTrafico) {
			return this.getFecha() == ((LogTrafico) obj).getFecha() && this.origen.equals(((LogTrafico) obj).getOrigen()) && this.destinos.equals(((LogTrafico) obj).getDestinos());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("[fecha: %s; origen: %s; destinos: %s]", getFecha(), getOrigen(), getDestinos());
	}
}
