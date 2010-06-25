package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import beans.CasillaVO;
import beans.MensajeVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;

public interface InterfazGestion extends Remote {

	// Casillas

	public Collection<CasillaVO> obtenerCasillas() throws RemoteException;

	public CasillaVO obtenerCasilla(CasillaVO casilla) throws RemoteException;

	public void modificarCasilla(CasillaVO casillaOriginal, CasillaVO casillaNueva) throws RemoteException;
	
	public void borrarCasilla(CasillaVO casilla) throws RemoteException;

	// Mensajes

	public Collection<MensajeVO> obtenerMensajes() throws RemoteException;

	public MensajeVO obtenerMensaje(String id) throws RemoteException;

	public void modificarMensaje(String id, MensajeVO nuevoMensaje) throws RemoteException;

	public void borrarMensaje(String id) throws RemoteException;

	// Oficinas

	public Collection<OficinaVO> obtenerOficinas() throws RemoteException;

	public OficinaVO obtenerOficina(String id) throws RemoteException;

	public void modificarOficina(OficinaVO oficinaOriginal, OficinaVO oficinaNueva) throws RemoteException;

	public void borrarOficina(OficinaVO oficina) throws RemoteException;

	// Relaciones de Confianza

	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws RemoteException;

	public RelacionConfianzaVO obtenerRelacionConfianza(OficinaVO oficinaOrigen, OficinaVO oficinaDestino) throws RemoteException;

	public void modificarRelacionConfianza(OficinaVO oficinaOrigen, OficinaVO oficinaDestinoOriginal, OficinaVO oficinaDestinoNueva) throws RemoteException;

	public void borrarRelacionConfianza(OficinaVO oficinaOrigen, OficinaVO oficinaDestino) throws RemoteException;

	// Usuarios

	public void agregarUsuario(UsuarioVO usuario);

	public void borrarUsuario(UsuarioVO usuario);

	public void modificarUsuario(UsuarioVO usuario);
}
