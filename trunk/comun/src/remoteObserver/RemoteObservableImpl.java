package remoteObserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class RemoteObservableImpl extends Observable implements RemoteObservable, Serializable {

	private static final long serialVersionUID = 1L;

	private Hashtable<RemoteObserver, ProxyObserver> observers;

	public RemoteObservableImpl() throws RemoteException {
		observers = new Hashtable<RemoteObserver, ProxyObserver>();
	}

	public void addRemoteObserver(RemoteObserver ro) throws RemoteException {
		ProxyObserver po = new ProxyObserver(ro);
		observers.put(ro, po);
		super.addObserver(po);
	}

	@Override
	public void removeRemoteObserver(RemoteObserver ro) throws RemoteException {
		ProxyObserver po = (ProxyObserver) observers.remove(ro);
		super.removeObserver(po);
	}
}
