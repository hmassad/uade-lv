package enums;

/**
 * @author   hmassad
 */
public enum MensajeEstado {
	/**
	 * @uml.property  name="enviado"
	 * @uml.associationEnd  
	 */
	Enviado,
	/**
	 * @uml.property  name="sinEnviar"
	 * @uml.associationEnd  
	 */
	SinEnviar,
	/**
	 * @uml.property  name="leido"
	 * @uml.associationEnd  
	 */
	Leido,
	/**
	 * @uml.property  name="noLeido"
	 * @uml.associationEnd  
	 */
	NoLeido,
	/**
	 * @uml.property  name="paraBorrar"
	 * @uml.associationEnd  
	 */
	ParaBorrar,
}
