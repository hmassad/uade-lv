package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.CasillaVO;
import beans.MensajeVO;
import enums.MensajeEstado;
import enums.MensajeTipo;

public interface InterfazMensajeria extends Remote {

	boolean validarLogin(String nombreUsuario, String password) throws RemoteException;

	void cambiarPassword(String nombreUsuario, String password) throws RemoteException;

	Collection<MensajeVO> listarMensajesPorCasilla(String direccion) throws RemoteException;

	void enviarMensaje(Integer id, String origen, Collection<String> destinos, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException;

	void guardarMensaje(Integer id, String direccion, String asunto, String cuerpo, MensajeTipo tipo) throws RemoteException;
	
	void eliminarMensaje(Integer id) throws RemoteException;

	Collection<String> listarDireccionesPosibles(String direccion) throws RemoteException;

	Collection<String> listarDireccionesPosiblesQueComiencenCon(String direccion, String comienzo) throws RemoteException;

	Collection<CasillaVO> listarCasillasPorUsuario(String usuario) throws RemoteException;

	Collection<MensajeVO> listarMensajesPorCasillaPorEstado(String direccion, MensajeEstado estado) throws RemoteException;

	Collection<MensajeVO> listarMensajesPorUsuario(String usuario) throws RemoteException;

	Collection<MensajeVO> listarMensajesPorUsuarioPorEstado(String usuario, MensajeEstado estado) throws RemoteException;

	MensajeVO obtenerMensaje(String direccionCasilla, Integer idMensaje) throws RemoteException;

	void cambiarMensajeEstado(String direccionCasilla, Integer idMensaje, MensajeEstado estado) throws RemoteException;

	void bloquearCasilla(String nombreUsuario, String direccionCasilla) throws RemoteException;

	int obtenerCantidadMensajesNoLeidosDeUsuario(String nombreUsuario) throws RemoteException;
}
