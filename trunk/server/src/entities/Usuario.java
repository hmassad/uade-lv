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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author  hmassad
 */
@Entity
public class Usuario implements Serializable {

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
	 * @uml.property  name="password"
	 */
	private String password;

	/**
	 * @uml.property  name="casillas"
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = { CascadeType.ALL })
	private Collection<Casilla> casillas = new ArrayList<Casilla>();

	/**
	 * @uml.property  name="casillasBloqueadas"
	 */
	@ManyToMany
	private Collection<Casilla> casillasBloqueadas = new ArrayList<Casilla>();

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return this.id;
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
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @uml.property  name="casillasBloqueadas"
	 */
	public Collection<Casilla> getCasillasBloqueadas() {
		return casillasBloqueadas;
	}

	/**
	 * @param casillasBloqueadas
	 * @uml.property  name="casillasBloqueadas"
	 */
	public void setCasillasBloqueadas(Collection<Casilla> casillasBloqueadas) {
		this.casillasBloqueadas = casillasBloqueadas;
	}

	public void agregarCasilla(Casilla casilla) {
		casillas.add(casilla);
		casilla.setUsuario(this);
	}

	public void agregarCasillaBloqueada(Casilla casilla) {
		casillasBloqueadas.add(casilla);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			return this.getId() == ((Usuario) obj).getId();
		}
		return false;
	}

	public String toString() {
		return "[id: " + getId() + "; nombre: " + getNombre() + "]";
	}
}
