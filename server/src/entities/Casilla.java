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

@Entity
public class Casilla implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Usuario usuario;

	@Column(unique = true)
	private String direccion;

	@ManyToMany
	@JoinTable(name = "CasillasPorOficina", joinColumns = { @JoinColumn(name = "casilla_id") }, inverseJoinColumns = { @JoinColumn(name = "oficina_id") })
	private Collection<Oficina> oficinas = new ArrayList<Oficina>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.casilla")
	private Collection<MensajeEnCasilla> mensajes = new ArrayList<MensajeEnCasilla>();

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

	public Collection<Oficina> getOficinas() {
		return oficinas;
	}

	public void setOficinas(Collection<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	public Collection<MensajeEnCasilla> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Collection<MensajeEnCasilla> mensajes) {
		this.mensajes = mensajes;
	}

	public void agregarOficina(Oficina o) {
		getOficinas().add(o);
	}

	public void removerOficina(Oficina o) {
		getOficinas().remove(o);
	}

	@Override
	public String toString() {
		return "Casilla(ID: " + getId() + "; Direccion: " + getDireccion() + ")";
	}
}
