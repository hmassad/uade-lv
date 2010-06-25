package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import beans.CasillaVO;
import beans.MensajeVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;
import enities.Casilla;

public class Gestion extends UnicastRemoteObject implements InterfazGestion {

	private static final long serialVersionUID = 1L;

	public Gestion() throws RemoteException {
		super();
	}

	private Casilla buscarCasilla(CasillaVO casilla) {
		// TODO buscar la casilla en base de datos y devolver la instancia
		return null;
	}

	@Override
	public Collection<CasillaVO> obtenerCasillas() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CasillaVO obtenerCasilla(CasillaVO casilla) throws RemoteException {
		Casilla aux = buscarCasilla(casilla);
		if (aux == null) {
			throw new RemoteException("No encontre la casilla");
		}

		CasillaVO c = new CasillaVO();
		c.setId(aux.getId());
		c.setPassword(aux.getPassword());
		c.setDireccion(aux.getDireccion());
		return c;
	}

	@Override
	public void modificarCasilla(CasillaVO casillaOriginal,
			CasillaVO casillaNueva) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarCasilla(CasillaVO casilla) throws RemoteException {
		Casilla aux = buscarCasilla(casilla);
		if (aux == null) {
			throw new RemoteException("No encontre la casilla");
		}
	}

	@Override
	public Collection<MensajeVO> obtenerMensajes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MensajeVO obtenerMensaje(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarMensaje(String id, MensajeVO nuevoMensaje)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarMensaje(String id) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<OficinaVO> obtenerOficinas() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OficinaVO obtenerOficina(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarOficina(OficinaVO oficinaOriginal,
			OficinaVO oficinaNueva) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarOficina(OficinaVO oficina) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelacionConfianzaVO obtenerRelacionConfianza(
			OficinaVO oficinaOrigen, OficinaVO oficinaDestino)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarRelacionConfianza(OficinaVO oficinaOrigen,
			OficinaVO oficinaDestinoOriginal, OficinaVO oficinaDestinoNueva)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarRelacionConfianza(OficinaVO oficinaOrigen,
			OficinaVO oficinaDestino) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregarUsuario(UsuarioVO usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarUsuario(UsuarioVO usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarUsuario(UsuarioVO usuario) {
		// TODO Auto-generated method stub

	}
}
