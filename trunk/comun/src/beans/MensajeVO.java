package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import enums.MensajeEstado;
import enums.MensajeTipo;

public class MensajeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date fecha;
	private String origen;
	private Collection<String> destinos = new ArrayList<String>();
	private MensajeTipo tipo;
	private MensajeEstado estado;
	private String asunto;
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

	public void agregarDestino(String destino) {
		getDestinos().add(destino);
	}

	public void removerDestino(String destino) {
		getDestinos().remove(destino);
	}
}
