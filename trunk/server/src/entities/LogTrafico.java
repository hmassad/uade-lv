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

/**
 * @author  hmassad
 */
@Entity
public class LogTrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	private int id;

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
	@CollectionOfElements(targetElement = String.class, fetch = FetchType.LAZY)
	private Collection<String> destinos = new ArrayList<String>();

	/**
	 * @uml.property  name="mensaje"
	 */
	private String mensaje;

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @uml.property  name="mensaje"
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje
	 * @uml.property  name="mensaje"
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return
	 * @uml.property  name="origen"
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 * @uml.property  name="origen"
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
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
