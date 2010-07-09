package controlador;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Collection;

import rmi.InterfazGestion;
import rmi.observer.EventoObservable;
import rmi.observer.LocalObserver;
import rmi.observer.RemoteObservable;
import rmi.observer.RemoteObserverLocalObservable;
import rmi.observer.RemoteObserverLocalObservableImpl;
import beans.CasillaVO;
import beans.LogTraficoVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;

public class ControladorGestion implements RemoteObserverLocalObservable {

	private static final long serialVersionUID = 1L;

	private InterfazGestion gestion;

	private RemoteObserverLocalObservable remoteObserverLocalObservable;

	public ControladorGestion() throws Exception {
		super();
		gestion = (InterfazGestion) Naming.lookup("rmi://localhost/gestion");
		remoteObserverLocalObservable = new RemoteObserverLocalObservableImpl();
		gestion.addRemoteObserver(remoteObserverLocalObservable);
	}

	@Override
	protected void finalize() throws Throwable {
		gestion.deleteRemoteObserver(remoteObserverLocalObservable);
		super.finalize();
	}

	@Override
	public void addLocalObserver(LocalObserver localObserver) throws RemoteException {
		remoteObserverLocalObservable.addLocalObserver(localObserver);
	}

	@Override
	public void deleteLocalObserver(LocalObserver localObserver) throws RemoteException {
		remoteObserverLocalObservable.deleteLocalObserver(localObserver);
	}

	@Override
	public void update(RemoteObservable observable, EventoObservable eventoObservable) throws RemoteException {
		remoteObserverLocalObservable.update(observable, eventoObservable);
	}

	public Collection<UsuarioVO> obtenerUsuarios() throws Exception {
		return gestion.obtenerUsuarios();
	}

	public void agregarUsuario(String nombreUsuario, String password) throws Exception {
		gestion.agregarUsuario(nombreUsuario, password);
	}

	public void borrarUsuario(int id) throws Exception {
		gestion.borrarUsuario(id);
	}

	public Collection<CasillaVO> obtenerCasillas() throws Exception {
		return gestion.obtenerCasillas();
	}

	public Collection<CasillaVO> obtenerCasillasPorUsuario(int idUsuario) throws Exception {
		return gestion.obtenerCasillasPorUsuario(idUsuario);
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

	public void borrarCasilla(int id) throws Exception {
		gestion.borrarCasilla(id);
	}

	public Collection<OficinaVO> obtenerOficinasPorCasilla(int idCasilla) throws Exception {
		return gestion.obtenerOficinasPorCasilla(idCasilla);
	}

	public Collection<OficinaVO> obtenerOficinas() throws Exception {
		return gestion.obtenerOficinas();
	}

	public void agregarOficina(String nombre) throws Exception {
		gestion.agregarOficina(nombre);
	}

	public void borrarOficina(int id) throws Exception {
		gestion.borrarOficina(id);
	}

	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws Exception {
		return gestion.obtenerRelacionesConfianza();
	}

	public void agregarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws Exception {
		gestion.agregarRelacionConfianza(idOficinaOrigen, idOficinaDestino);
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
