package remoteObserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

	void update(RemoteObservable observable, EventoObservable roe) throws RemoteException;
}
