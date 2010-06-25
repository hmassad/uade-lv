package server;

import java.rmi.Naming;
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

	private void inicializarRmi() {
		try {
			LocateRegistry.createRegistry(1099);

			gestion = (InterfazGestion) new Gestion();
			Naming.rebind("//localhost/gestion", gestion);

			mensajeria = (InterfazMensajeria) new Mensajeria();
			Naming.rebind("//localhost/mensajeria", mensajeria);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
