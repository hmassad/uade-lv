package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.Gestion;
import rmi.InterfazGestion;
import rmi.InterfazMensajeria;
import rmi.Mensajeria;

public class Server {

	public static void main(String[] args) {
		new Server();
	}

	private InterfazGestion gestion;
	private InterfazMensajeria mensajeria;

	public Server() {
		try {
			gestion = new Gestion();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			mensajeria = new Mensajeria();
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		inicializarRmi();
	}

	@Override
	protected void finalize() throws Throwable {
		desinicializarRmi();
		super.finalize();
	}

	private void inicializarRmi() {
		try {
			// System.setSecurityManager(new RMISecurityManager());
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost/gestion", gestion);
			Naming.rebind("//localhost/mensajeria", mensajeria);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void desinicializarRmi() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind("//localhost/gestion");
		Naming.unbind("//localhost/mensajeria");
	}
}
