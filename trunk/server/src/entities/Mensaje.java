package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.MensajeTipo;

/**
 * @author  hmassad
 */
@Entity
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	@Column(name = "mensaje_id", nullable = false)
	private int id;

	/**
	 * @uml.property  name="fecha"
	 */
	private Date fecha;

	/**
	 * @uml.property  name="origen"
	 * @uml.associationEnd  
	 */
	@ManyToOne
	private Casilla origen;

	/**
	 * @uml.property  name="destinos"
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.mensaje", cascade = { CascadeType.ALL })
	private Collection<MensajeEnCasilla> destinos = new ArrayList<MensajeEnCasilla>();

	/**
	 * @uml.property  name="tipo"
	 * @uml.associationEnd  
	 */
	@Enumerated(EnumType.STRING)
	private MensajeTipo tipo;

	/**
	 * @uml.property  name="asunto"
	 */
	private String asunto;

	/**
	 * @uml.property  name="cuerpo"
	 */
	private String cuerpo;

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
	 * @uml.property  name="origen"
	 */
	public Casilla getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 * @uml.property  name="origen"
	 */
	public void setOrigen(Casilla origen) {
		this.origen = origen;
	}

	/**
	 * @return
	 * @uml.property  name="destinos"
	 */
	public Collection<MensajeEnCasilla> getDestinos() {
		return destinos;
	}

	/**
	 * @param destinos
	 * @uml.property  name="destinos"
	 */
	public void setDestinos(Collection<MensajeEnCasilla> destinos) {
		this.destinos = destinos;
	}

	/**
	 * @return
	 * @uml.property  name="tipo"
	 */
	public MensajeTipo getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 * @uml.property  name="tipo"
	 */
	public void setTipo(MensajeTipo tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return
	 * @uml.property  name="asunto"
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto
	 * @uml.property  name="asunto"
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return
	 * @uml.property  name="cuerpo"
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * @param cuerpo
	 * @uml.property  name="cuerpo"
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Mensaje) {
			return this.getId() == ((Mensaje) obj).getId();
		}
		return false;
	}

	public String toString() {
		return String.format("[id: %d; Fecha: %t; Asunto: %s; Cuerpo: %s]", getId(), getFecha(), getAsunto(), getCuerpo());
	}
}
