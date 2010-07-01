package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "usuario_id")
	private int id;

	@Column(name = "nombre", unique = true)
	private String nombre;

	@OneToMany(mappedBy = "usuario")
	private Collection<Casilla> casillas = new ArrayList<Casilla>();

	@OneToMany
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
