package rmi.observer;
//
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @uml.dependency   supplier="controlador.ControladorGestion"
 */
public interface LocalObserver extends Remote {

	void update(RemoteObserverLocalObservable remoteObserverLocalObservable, EventoObservable eventoObservable) throws RemoteException;

}
