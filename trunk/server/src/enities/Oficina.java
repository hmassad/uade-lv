package enities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Oficina {

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private Collection<Casilla> casillas;

	public Oficina() {
	}

	public int getId() {
		return id;
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
		casilla.setOficina(this);
	}

	public void borrarCasilla(Casilla casilla) {
		casillas.remove(casilla);
	}
}
