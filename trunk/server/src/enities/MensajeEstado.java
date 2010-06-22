package enities;

import javax.persistence.Entity;

@Entity
public enum MensajeEstado {
	Enviado, SinEnviar, Leido, NoLeido, ParaBorrar,
}
