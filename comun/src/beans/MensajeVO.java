package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import enums.MensajeEstado;
import enums.MensajeTipo;

public class MensajeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date fecha;
	private CasillaVO casillaRemitente;
	private Collection<CasillaVO> casillasDestinatarios;
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

	public CasillaVO getCasillaRemitente() {
		return casillaRemitente;
	}

	public void setCasillaRemitente(CasillaVO casillaRemitente) {
		this.casillaRemitente = casillaRemitente;
	}

	public Collection<CasillaVO> getCasillasDestinatarios() {
		return casillasDestinatarios;
	}

	public void setCasillasDestinatarios(Collection<CasillaVO> casillasDestinatarios) {
		this.casillasDestinatarios = casillasDestinatarios;
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

}
