package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import enums.MensajeEstado;
import enums.MensajeTipo;

public class MensajeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
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
	private Collection<String> destinos;
	/**
	 * @uml.property  name="tipo"
	 * @uml.associationEnd  
	 */
	private MensajeTipo tipo;
	/**
	 * @uml.property  name="estado"
	 * @uml.associationEnd  
	 */
	private MensajeEstado estado;
	/**
	 * @uml.property  name="asunto"
	 */
	private String asunto;
	/**
	 * @uml.property  name="cuerpo"
	 */
	private String cuerpo;

	public MensajeVO() {
		destinos = new ArrayList<String>();
	}

	public MensajeVO(int id, Date fecha, String origen, Collection<String> destinos, MensajeTipo tipo, MensajeEstado estado, String asunto, String cuerpo) {
		this();
		this.id = id;
		this.fecha = fecha;
		this.origen = origen;
		this.destinos = destinos;
		this.tipo = tipo;
		this.estado = estado;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

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
	 * @uml.property  name="estado"
	 */
	public MensajeEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 * @uml.property  name="estado"
	 */
	public void setEstado(MensajeEstado estado) {
		this.estado = estado;
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

	public void agregarDestino(String destino) {
		getDestinos().add(destino);
	}

	public void removerDestino(String destino) {
		getDestinos().remove(destino);
	}

	@Override
	public boolean equals(Object arg) {
		return this.getId() == ((CasillaVO) arg).getId();
	}

	@Override
	public String toString() {
		return String.format("Mensaje(id: %d; fecha: %s; origen: %s; destinos: %s; tipo: %s; asunto: %s)", getId(), new SimpleDateFormat("dd-MM-yyyy HH:mm").format(getFecha()), getOrigen(), getDestinos(), getTipo(), getAsunto());
	}
}
