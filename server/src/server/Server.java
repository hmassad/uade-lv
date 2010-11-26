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

/**
 * @author  hmassad
 */
public class Server {

	public static void main(String[] args) {
		new Server();
	}

	/**
	 * @uml.property  name="gestion"
	 * @uml.associationEnd  
	 */
	private InterfazGestion gestion;
	/**
	 * @uml.property  name="mensajeria"
	 * @uml.associationEnd  
	 */
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
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://hmassad-laptop/gestion", gestion);
			Naming.rebind("rmi://hmassad-laptop/mensajeria", mensajeria);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void desinicializarRmi() throws RemoteException, MalformedURLException, NotBoundException {
		Naming.unbind("rmi://hmassad-laptop/gestion");
		Naming.unbind("rmi://hmassad-laptop/mensajeria");
	}
}
