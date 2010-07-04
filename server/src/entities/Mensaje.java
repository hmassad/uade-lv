package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

@Entity
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "mensaje_id", nullable = false)
	private int id;

	@Column(name = "fecha")
	private Date fecha;

	@ManyToOne
	private Casilla origen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.mensaje")
	private Collection<MensajeEnCasilla> destinos = new ArrayList<MensajeEnCasilla>();

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private MensajeTipo tipo;
	
	@Column(name = "asunto")
	private String asunto;

	@Column(name = "cuerpo")
	private String cuerpo;

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

	public Casilla getOrigen() {
		return origen;
	}

	public void setOrigen(Casilla origen) {
		this.origen = origen;
	}

	public Collection<MensajeEnCasilla> getDestinos() {
		return destinos;
	}

	public void setDestinos(Collection<MensajeEnCasilla> destinos) {
		this.destinos = destinos;
	}

	public MensajeTipo getTipo() {
		return tipo;
	}

	public void setTipo(MensajeTipo tipo) {
		this.tipo = tipo;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String toString() {
		return String.format("Mensaje(ID: %d; Fecha: %t; Asunto: %s; Cuerpo: %s", getId(), getFecha(), getAsunto(), getCuerpo());
	}
}
