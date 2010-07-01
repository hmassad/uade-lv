package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.MensajeEstado;

@Entity
public class Casilla implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "casilla_id", nullable = false)
	private int id;

	@ManyToOne
	private Usuario usuario;

	@Column(name = "direccion", unique = true)
	private String direccion;

	@Column(name = "password")
	private String password;

	@ManyToMany
	@JoinTable(name = "CasillasPorOficina", joinColumns = { @JoinColumn(name = "oficina_id") }, inverseJoinColumns = { @JoinColumn(name = "casilla_id") })
	private Collection<Oficina> oficinas = new ArrayList<Oficina>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.casilla")
	private Collection<CasillaMensaje> mensajes = new ArrayList<CasillaMensaje>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Collection<Oficina> getOficinas() {
		return oficinas;
	}

	public void setOficinas(Collection<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	public Collection<CasillaMensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Collection<CasillaMensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public void agregarAOficina(Oficina o) {
		getOficinas().add(o);
	}

	public void removerDeOficina(Oficina o) {
		getOficinas().remove(o);
	}

	public void agregarMensaje(Mensaje m, MensajeEstado me) {
		CasillaMensaje cm = new CasillaMensaje();
		cm.setCasilla(this);
		cm.setMensaje(m);
		cm.setEstado(me);
		getMensajes().add(cm);
	}

	public void removerMensaje(CasillaMensaje cm) {
		getMensajes().remove(cm);
	}

	@Override
	public String toString() {
		return "Casilla(ID: " + getId() + "; Usuario: " + getUsuario()
				+ "; Direccion: " + getDireccion() + "; Password: "
				+ getPassword() + ")";
	}
}
