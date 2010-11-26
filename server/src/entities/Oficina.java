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
import javax.persistence.OneToMany;

/**
 * @author  hmassad
 */
@Entity
public class Oficina implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="id"
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * @uml.property  name="nombre"
	 */
	@Column(unique = true)
	private String nombre;

	/**
	 * @uml.property  name="casillas"
	 */
	@ManyToMany
	@JoinTable(name = "CasillasPorOficina", joinColumns = { @JoinColumn(name = "oficina_id") }, inverseJoinColumns = { @JoinColumn(name = "casilla_id") })
	private Collection<Casilla> casillas;

	/**
	 * @uml.property  name="relacionesConfianza"
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.origen", cascade = { CascadeType.ALL })
	private Collection<RelacionConfianza> relacionesConfianza = new ArrayList<RelacionConfianza>();

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
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return
	 * @uml.property  name="casillas"
	 */
	public Collection<Casilla> getCasillas() {
		return casillas;
	}

	/**
	 * @param casillas
	 * @uml.property  name="casillas"
	 */
	public void setCasillas(Collection<Casilla> casillas) {
		this.casillas = casillas;
	}

	/**
	 * @return
	 * @uml.property  name="relacionesConfianza"
	 */
	public Collection<RelacionConfianza> getRelacionesConfianza() {
		return relacionesConfianza;
	}

	/**
	 * @param relacionesConfianza
	 * @uml.property  name="relacionesConfianza"
	 */
	public void setRelacionesConfianza(Collection<RelacionConfianza> relacionesConfianza) {
		this.relacionesConfianza = relacionesConfianza;
	}

	public void agregarCasilla(Casilla casilla) {
		if (casillas == null) {
			casillas = new ArrayList<Casilla>();
		}
		casillas.add(casilla);
		casilla.agregarOficina(this);
	}

	public void borrarCasilla(Casilla casilla) {
		casillas.remove(casilla);
		casilla.removerOficina(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Oficina) {
			return this.getId() == ((Oficina) obj).getId();
		}
		return false;
	}

	@Override
	public String toString() {
		return "[id: " + getId() + "; nombre: " + getNombre() + "]";
	}
}
