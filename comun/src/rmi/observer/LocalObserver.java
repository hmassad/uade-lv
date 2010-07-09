package rmi.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LocalObserver extends Remote {

	void update(RemoteObserverLocalObservable remoteObserverLocalObservable, EventoObservable eventoObservable) throws RemoteException;

}
