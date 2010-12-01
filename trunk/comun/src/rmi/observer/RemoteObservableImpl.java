package rmi.observer;
//
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

public class RemoteObservableImpl implements RemoteObservable {

	private static final long serialVersionUID = 1L;

	private Collection<RemoteObserver> remoteObservers = new ArrayList<RemoteObserver>();

	@Override
	public void addRemoteObserver(RemoteObserver remoteObserver) throws RemoteException {
		remoteObservers.add(remoteObserver);
	}

	@Override
	public void deleteRemoteObserver(RemoteObserver remoteObserver) throws RemoteException {
		remoteObservers.remove(remoteObserver);
	}

	public void notifyRemoteObservers(EventoObservable eo) throws RemoteException {
		for (RemoteObserver remoteObserver : remoteObservers) {
			try {
				remoteObserver.update(this, eo);
			} catch (RemoteException e) {
				remoteObservers.remove(remoteObserver);
				e.printStackTrace();
			}
		}
	}

}
