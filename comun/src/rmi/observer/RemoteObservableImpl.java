package rmi.observer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

public class RemoteObservableImpl implements RemoteObservable {

	private static final long serialVersionUID = 1L;

	private Collection<RemoteObserver> observers = new ArrayList<RemoteObserver>();

	@Override
	public void addRemoteObserver(RemoteObserver o) throws RemoteException {
		observers.add(o);
	}

	@Override
	public void deleteRemoteObserver(RemoteObserver o) throws RemoteException {
		observers.remove(o);
	}

	public void notifyRemoteObservers(EventoObservable eo) throws RemoteException {
		for (RemoteObserver ro : observers) {
			ro.update(this, eo);
		}
	}

}
