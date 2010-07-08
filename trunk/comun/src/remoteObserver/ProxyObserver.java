package remoteObserver;

public class ProxyObserver implements Observer {

	public ProxyObserver(RemoteObserver ro) {
		remoteObserver = ro;
	}

	@Override
	public void update(Observable obs, EventoObservable roe) {
		try {
			if (obs instanceof RemoteObservable)
				remoteObserver.update((RemoteObservable) obs, roe);
		} catch (Exception re) {
			System.err.println("Remote Observer error: " + re);
			re.printStackTrace();
		}
	}

	private RemoteObserver remoteObserver;
}
