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
		inicializarRmi();
	}

	@Override
	protected void finalize() throws Throwable {
		desinicializarRmi();
		super.finalize();
	}

	private void inicializarRmi() {
		try {
			LocateRegistry.createRegistry(1099);

			gestion = new Gestion();
			Naming.rebind("//localhost/gestion", gestion);

			mensajeria = (InterfazMensajeria) new Mensajeria();
			Naming.rebind("//localhost/mensajeria", mensajeria);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void desinicializarRmi() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind("//localhost/mensajeria");
		Naming.unbind("//localhost/gestion");
	}
}
