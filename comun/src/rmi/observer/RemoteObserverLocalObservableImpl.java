package rmi.observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

public class RemoteObserverLocalObservableImpl extends UnicastRemoteObject implements RemoteObserverLocalObservable, Serializable {

	public RemoteObserverLocalObservableImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	private Collection<LocalObserver> localObservers = new ArrayList<LocalObserver>();

	@Override
	public void addLocalObserver(LocalObserver localObserver) {
		localObservers.add(localObserver);
	}

	@Override
	public void deleteLocalObserver(LocalObserver localObserver) {
		localObservers.remove(localObserver);
	}

	@Override
	public void update(RemoteObservable observable, EventoObservable eventoObservable) throws RemoteException {
		for (LocalObserver localObserver : localObservers) {
			localObserver.update(this, eventoObservable);
		}
	}

}
