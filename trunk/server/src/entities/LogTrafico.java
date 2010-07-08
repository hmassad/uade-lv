package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class LogTrafico {

	@Id
	@GeneratedValue
	@Column(name = "log_trafico_id", unique = true)
	private int id;

	private Date fecha;
	private Mensaje mensaje;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Casilla.class)
	private Casilla origen;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Casilla.class)
	private Collection<Casilla> destinos = new ArrayList<Casilla>();

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Casilla getOrigen() {
		return origen;
	}

	public void setOrigen(Casilla origen) {
		this.origen = origen;
	}

	public Collection<Casilla> getDestinos() {
		return destinos;
	}

	public void setDestinos(Collection<Casilla> destinos) {
		this.destinos = destinos;
	}

	public void agregarDestino(Casilla casillaDestino) {
		getDestinos().add(casillaDestino);
	}

	public void removerDestino(Casilla casillaDestino) {
		getDestinos().remove(casillaDestino);
	}
}
