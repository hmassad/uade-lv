package rmi.observer;
//
import java.rmi.RemoteException;

public interface RemoteObserverLocalObservable extends RemoteObserver {

	void addLocalObserver(LocalObserver localObserver) throws RemoteException;

	void deleteLocalObserver(LocalObserver localObserver) throws RemoteException;

}
