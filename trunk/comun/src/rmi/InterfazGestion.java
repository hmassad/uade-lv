package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import rmi.observer.RemoteObservable;

import beans.CasillaVO;
import beans.LogTraficoVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;

public interface InterfazGestion extends Remote, RemoteObservable {

	// Casillas

	Collection<CasillaVO> obtenerCasillas() throws RemoteException;

	Collection<CasillaVO> obtenerCasillasPorOficina(int idOficina) throws RemoteException;

	Collection<CasillaVO> obtenerCasillasPorUsuario(int idUsuario) throws RemoteException;

	CasillaVO obtenerCasilla(int id) throws RemoteException;

	void agregarCasilla(int idUsuario, String direccion) throws RemoteException;

	void agregarCasillaAOficina(int idOficina, int idCasilla) throws RemoteException;

	void borrarCasillaDeOficina(int idOficina, int idCasilla) throws RemoteException;

	void modificarCasilla(int idCasilla, String direccion) throws RemoteException;

	void borrarCasilla(int idCasilla) throws RemoteException;

	// Oficinas

	Collection<OficinaVO> obtenerOficinas() throws RemoteException;

	OficinaVO obtenerOficina(int id) throws RemoteException;

	Collection<OficinaVO> obtenerOficinasPorCasilla(int idUsuario) throws RemoteException;

	void agregarOficina(String nombre) throws RemoteException;

	void modificarOficina(int id, String nombre) throws RemoteException;

	void borrarOficina(int id) throws RemoteException;

	// Relaciones de Confianza

	Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws RemoteException;

	RelacionConfianzaVO obtenerRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException;

	void agregarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException;

	void modificarRelacionConfianza(int idOficinaOrigen, int idOficinaDestinoOriginal, int idOficinaDestinoNueva) throws RemoteException;

	void borrarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException;

	// Usuarios

	Collection<UsuarioVO> obtenerUsuarios() throws RemoteException;

	void agregarUsuario(String nombreUsuario, String password) throws RemoteException;

	void modificarUsuario(int idUsuario, String nombre, String password) throws RemoteException;

	void borrarUsuario(int id) throws RemoteException;

	// Log

	Collection<LogTraficoVO> obtenerLogs() throws RemoteException;

	void borrarLogs() throws RemoteException;
}
