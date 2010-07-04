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
import javax.persistence.OneToMany;

@Entity
public class Oficina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "oficina_id", unique = true)
	private int id;

	private String nombre;

	@ManyToMany
	@JoinTable(name = "CasillasPorOficina", joinColumns = { @JoinColumn(name = "oficina_id") }, inverseJoinColumns = { @JoinColumn(name = "casilla_id") })
	private Collection<Casilla> casillas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.origen")
	private Collection<RelacionConfianza> relacionesConfianza = new ArrayList<RelacionConfianza>();

	public int getId() {
		return id;
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

	public Collection<RelacionConfianza> getRelacionesConfianza() {
		return relacionesConfianza;
	}

	public void setRelacionesConfianza(Collection<RelacionConfianza> relacionesConfianza) {
		this.relacionesConfianza = relacionesConfianza;
	}

	public void agregarCasilla(Casilla casilla) {
		if (casillas == null) {
			casillas = new ArrayList<Casilla>();
		}
		casillas.add(casilla);
	}

	public void borrarCasilla(Casilla casilla) {
		casillas.remove(casilla);
	}
}
