package enities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Casilla")
public class Casilla {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "UsuarioID")
	private Usuario usuario;
	private String direccion;
	private String password;
	private Oficina oficina;
	private Collection<Mensaje> mensajes;

	public Casilla() {
	}

	public int getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(Collection<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public void agregarMensaje(Mensaje mensaje) {
		if (mensajes == null) {
			mensajes = new ArrayList<Mensaje>();
		}
		mensajes.add(mensaje);
		mensaje.setCasilla(this);
	}

	public void borrarMensaje(Mensaje mensaje) {
		mensajes.remove(mensaje);
	}

	public String toString() {
		return "Casilla(ID: " + getId() + "; Usuario: " + getUsuario() + "; Direccion: " + getDireccion() + "; Password: " + getPassword() + ")";
	}
}
