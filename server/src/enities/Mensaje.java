package enities;

import java.sql.Date;

public class Mensaje {
	private Casilla casilla;

	private Date Id_Fecha;
	private String Id_Casilla_Emisor;
	private String Id_Casilla_Receptor;
	private String Asunto;
	private String Texto;
	private String Tipo;
	private String Estado;

	public Mensaje(Date id_Fecha, String id_Casilla_Emisor, String id_Casilla_Receptor, String asunto, String texto, String tipo, String estado) {
		Id_Fecha = id_Fecha;
		Id_Casilla_Emisor = id_Casilla_Emisor;
		Id_Casilla_Receptor = id_Casilla_Receptor;
		Asunto = asunto;
		Texto = texto;
		Tipo = tipo;
		Estado = estado;
	}

	public Date getId_Fecha() {
		return Id_Fecha;
	}

	public void setId_Fecha(Date id_Fecha) {
		Id_Fecha = id_Fecha;
	}

	public String getId_Casilla_Emisor() {
		return Id_Casilla_Emisor;
	}

	public void setId_Casilla_Emisor(String id_Casilla_Emisor) {
		Id_Casilla_Emisor = id_Casilla_Emisor;
	}

	public String getId_Casilla_Receptor() {
		return Id_Casilla_Receptor;
	}

	public void setId_Casilla_Receptor(String id_Casilla_Receptor) {
		Id_Casilla_Receptor = id_Casilla_Receptor;
	}

	public String getAsunto() {
		return Asunto;
	}

	public void setAsunto(String asunto) {
		Asunto = asunto;
	}

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Casilla getCasilla() {
		return casilla;
	}
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}
}
