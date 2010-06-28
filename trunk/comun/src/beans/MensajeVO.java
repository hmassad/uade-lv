package beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import enums.MensajeEstado;
import enums.MensajeTipo;

public class MensajeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private UsuarioVO remitente;
	private Collection<UsuarioVO> destinatarios;
	private Date fechaEnvio;
	private String asunto;
	private String cuerpo;
	private MensajeTipo tipo;
	private MensajeEstado estado;
}
