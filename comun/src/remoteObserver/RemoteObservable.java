package remoteObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObservable extends Remote {

	void addRemoteObserver(RemoteObserver observer) throws RemoteException;

	void removeRemoteObserver(RemoteObserver observer) throws RemoteException;

}
