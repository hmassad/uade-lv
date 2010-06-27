package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue
	private int id;
	private Date fecha;
	private Casilla casillaRemitente;
	private Casilla casillaDestinatario;
	private String asunto;
	private String cuerpo;

	@Enumerated(EnumType.STRING)
	private MensajeTipo tipo;

	@Enumerated(EnumType.STRING)
	private MensajeEstado estado;

	public Mensaje() {
	}

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

	public Casilla getCasillaRemitente() {
		return casillaRemitente;
	}

	public void setCasillaRemitente(Casilla casillaRemitente) {
		this.casillaRemitente = casillaRemitente;
	}

	public Casilla getCasillaDestinatario() {
		return casillaDestinatario;
	}

	public void setCasillaDestinatario(Casilla casillaDestinatario) {
		this.casillaDestinatario = casillaDestinatario;
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

	public MensajeTipo getTipo() {
		return tipo;
	}

	public void setTipo(MensajeTipo tipo) {
		this.tipo = tipo;
	}

	public MensajeEstado getEstado() {
		return estado;
	}

	public void setEstado(MensajeEstado estado) {
		this.estado = estado;
	}

	public String toString() {
		return String.format("Mensaje(ID: %d; Fecha: %t; Asunto: %s", getId(), getFecha(), getAsunto());
	}
}
