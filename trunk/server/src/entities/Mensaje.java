package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.MensajeEstado;
import enums.MensajeTipo;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue
	private int id;

	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "casillaremitenteid")
	private Casilla casillaRemitente;

	@OneToMany(targetEntity = Casilla.class, mappedBy = "casilladestinatarioid")
	@JoinTable(name = "CasillasDestinatarioPorMensaje")
	private Collection<Casilla> casillasDestinatarios;

	@Enumerated(EnumType.STRING)
	private MensajeTipo tipo;

	@Enumerated(EnumType.STRING)
	private MensajeEstado estado;

	private String asunto;
	private String cuerpo;

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

	public Collection<Casilla> getCasillasDestinatarios() {
		return casillasDestinatarios;
	}

	public void setCasillaDestinatario(Collection<Casilla> casillasDestinatarios) {
		this.casillasDestinatarios = casillasDestinatarios;
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

	public void agregarCasillaDestinatario(Casilla casilla) {
		if (casillasDestinatarios == null) {
			casillasDestinatarios = new ArrayList<Casilla>();
		}
		casillasDestinatarios.add(casilla);
	}
}
