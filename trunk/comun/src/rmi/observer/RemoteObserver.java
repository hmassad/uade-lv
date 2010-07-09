package rmi.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

	void update(RemoteObservable observable, EventoObservable eventoObservable) throws RemoteException;

}
