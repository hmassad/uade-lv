package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public enum MensajeTipo {
	@Enumerated(EnumType.STRING)
	Urgente,
	@Enumerated(EnumType.STRING)
	Normal,
	@Enumerated(EnumType.STRING)
	Informativo,
}
