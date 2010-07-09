package rmi.observer;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObservable extends Remote, Serializable {

	void addRemoteObserver(RemoteObserver o) throws RemoteException;

	void deleteRemoteObserver(RemoteObserver o) throws RemoteException;

	void notifyRemoteObservers(EventoObservable eventoObservable) throws RemoteException;

}
