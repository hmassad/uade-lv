package controlador;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Observable;

import remoteObserver.EventoObservable;
import remoteObserver.RemoteObservable;
import remoteObserver.RemoteObserver;
import rmi.InterfazGestion;
import beans.CasillaVO;
import beans.LogTraficoVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;

public class ControladorGestion extends Observable implements RemoteObserver, Serializable {

	private static final long serialVersionUID = 1L;

	private InterfazGestion gestion;

	public ControladorGestion() throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		gestion = (InterfazGestion) Naming.lookup("//localhost/gestion");
		gestion.addRemoteObserver(this);
	}

	@Override
	protected void finalize() throws Throwable {
		gestion.removeRemoteObserver(this);
		super.finalize();
	}

	@Override
	public void update(RemoteObservable observable, EventoObservable roe) throws RemoteException {
		notifyObservers(roe);
	}

	public Collection<CasillaVO> obtenerCasillas() throws Exception {
		return gestion.obtenerCasillas();
	}

	public Collection<UsuarioVO> obtenerUsuarios() throws Exception {
		return gestion.obtenerUsuarios();
	}

	public Collection<CasillaVO> obtenerCasillasPorUsuario(int idUsuario) throws Exception {
		return gestion.obtenerCasillasPorUsuario(idUsuario);
	}

	public Collection<OficinaVO> obtenerOficinasPorCasilla(int idCasilla) throws Exception {
		return gestion.obtenerOficinasPorCasilla(idCasilla);
	}

	public void agregarUsuario(String nombreUsuario, String password) throws Exception {
		gestion.agregarUsuario(nombreUsuario, password);
	}

	public void agregarOficina(String nombre) throws Exception {
		gestion.agregarOficina(nombre);
	}

	public void agregarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws Exception {
		gestion.agregarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
	}

	public void agregarCasilla(int idUsuario, String direccion) throws Exception {
		gestion.agregarCasilla(idUsuario, direccion);
	}

	public void agregarCasillaAOficina(int idOficina, int idCasilla) throws RemoteException {
		gestion.agregarCasillaAOficina(idOficina, idCasilla);
	}

	public void borrarCasillaDeOficina(int idOficina, int idCasilla) throws RemoteException {
		gestion.borrarCasillaDeOficina(idOficina, idCasilla);
	}

	public void eliminarUsuario(int id) throws Exception {
		gestion.borrarUsuario(id);
	}

	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws Exception {
		return gestion.obtenerRelacionesConfianza();
	}

	public Collection<OficinaVO> obtenerOficinas() throws Exception {
		return gestion.obtenerOficinas();
	}

	public void borrarOficina(int id) throws Exception {
		gestion.borrarOficina(id);
	}

	public void borrarCasilla(int id) throws Exception {
		gestion.borrarCasilla(id);
	}

	public void eliminarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws Exception {
		gestion.borrarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
	}

	public Collection<LogTraficoVO> obtenerLogsTrafico() throws Exception {
		return gestion.obtenerLogs();
	}

	public void borrarLogsTrafico() throws Exception {
		gestion.borrarLogs();
	}
}
