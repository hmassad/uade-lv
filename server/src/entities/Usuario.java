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

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(unique = true)
	private String nombre;

	private String password;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = { CascadeType.ALL })
	private Collection<Casilla> casillas = new ArrayList<Casilla>();

	@ManyToMany
	private Collection<Casilla> casillasBloqueadas = new ArrayList<Casilla>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(Collection<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Collection<Casilla> getCasillasBloqueadas() {
		return casillasBloqueadas;
	}

	public void setCasillasBloqueadas(Collection<Casilla> casillasBloqueadas) {
		this.casillasBloqueadas = casillasBloqueadas;
	}

	public void agregarCasilla(Casilla casilla) {
		casillas.add(casilla);
		casilla.setUsuario(this);
	}

	public String toString() {
		return "Usuario(ID: " + getId() + "; Nombre: " + getNombre() + ")";
	}
}
