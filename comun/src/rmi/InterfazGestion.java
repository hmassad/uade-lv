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

	void agregarCasillaAUsuario(UsuarioVO u, CasillaVO c) throws RemoteException;

	void agregarCasillaAOficina(OficinaVO o, CasillaVO c) throws RemoteException;

	Collection<CasillaVO> obtenerCasillas() throws RemoteException;

	Collection<CasillaVO> obtenerCasillasPorOficina(OficinaVO o) throws RemoteException;

	Collection<CasillaVO> obtenerCasillasPorUsuario(UsuarioVO u) throws RemoteException;

	CasillaVO obtenerCasilla(CasillaVO c) throws RemoteException;

	void modificarCasilla(CasillaVO cOriginal, CasillaVO cNueva) throws RemoteException;

	void borrarCasilla(CasillaVO c) throws RemoteException;

	// Mensajes

	void agregarMensaje(CasillaVO casillaVO, MensajeVO mensajeVO) throws RemoteException;

	Collection<MensajeVO> obtenerMensajes() throws RemoteException;

	MensajeVO obtenerMensaje(MensajeVO mensajeVO) throws RemoteException;

	void modificarMensaje(MensajeVO mensajeVoOriginal, MensajeVO mensajeVoNuevo) throws RemoteException;

	void borrarMensaje(MensajeVO mensajeVO) throws RemoteException;

	// Oficinas

	void agregarOficina(OficinaVO o) throws RemoteException;

	Collection<OficinaVO> obtenerOficinas() throws RemoteException;

	OficinaVO obtenerOficina(OficinaVO o) throws RemoteException;

	void modificarOficina(OficinaVO oOriginal, OficinaVO oNueva) throws RemoteException;

	void borrarOficina(OficinaVO o) throws RemoteException;

	// Relaciones de Confianza

	void agregarRelacionConfianza(RelacionConfianzaVO rc) throws RemoteException;

	Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws RemoteException;

	RelacionConfianzaVO obtenerRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestino) throws RemoteException;

	void modificarRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestinoOriginal, OficinaVO oDestinoNueva) throws RemoteException;

	void borrarRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestino) throws RemoteException;

	// Usuarios

	void agregarUsuario(UsuarioVO usuario) throws RemoteException;

	void modificarUsuario(UsuarioVO uOriginal, UsuarioVO uNuevo) throws RemoteException;

	void borrarUsuario(UsuarioVO usuario) throws RemoteException;

	Collection<UsuarioVO> obtenerUsuarios() throws RemoteException;

}
