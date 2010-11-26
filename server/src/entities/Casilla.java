package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
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

/**
 * @author  hmassad
 */
@Entity
public class Casilla implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * @uml.property  name="usuario"
	 * @uml.associationEnd  
	 */
	@ManyToOne
	private Usuario usuario;

	/**
	 * @uml.property  name="direccion"
	 */
	@Column(unique = true)
	private String direccion;

	/**
	 * @uml.property  name="oficinas"
	 */
	@ManyToMany
	@JoinTable(name = "CasillasPorOficina", joinColumns = { @JoinColumn(name = "casilla_id") }, inverseJoinColumns = { @JoinColumn(name = "oficina_id") })
	private Collection<Oficina> oficinas = new ArrayList<Oficina>();

	/**
	 * @uml.property  name="mensajes"
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.casilla", cascade = { CascadeType.ALL })
	private Collection<MensajeEnCasilla> mensajes = new ArrayList<MensajeEnCasilla>();

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
	 * @uml.property  name="usuario"
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 * @uml.property  name="usuario"
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return
	 * @uml.property  name="direccion"
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 * @uml.property  name="direccion"
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return
	 * @uml.property  name="oficinas"
	 */
	public Collection<Oficina> getOficinas() {
		return oficinas;
	}

	/**
	 * @param oficinas
	 * @uml.property  name="oficinas"
	 */
	public void setOficinas(Collection<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	/**
	 * @return
	 * @uml.property  name="mensajes"
	 */
	public Collection<MensajeEnCasilla> getMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes
	 * @uml.property  name="mensajes"
	 */
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
	public boolean equals(Object obj) {
		if (obj instanceof Casilla) {
			return this.getId() == ((Casilla) obj).getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return "[id: " + getId() + "; direccion: " + getDireccion() + "]";
	}
}
