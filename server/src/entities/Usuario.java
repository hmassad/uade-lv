package entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
@UniqueConstraint(columnNames = { "nombre" })
public class Usuario {

	@Id
	@GeneratedValue
	private int id;

	private String nombre;

	@OneToMany(mappedBy = "usuario", targetEntity = Casilla.class)
	private Collection<Casilla> casillas;

	public Usuario() {
	}

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

	public void agregarCasilla(Casilla casilla) {
		if (casillas == null) {
			casillas = new ArrayList<Casilla>();
		}
		casillas.add(casilla);
		casilla.setUsuario(this);
	}

	public String toString() {
		return "Usuario(ID: " + getId() + "; Nombre: " + getNombre() + ")";
	}
}
