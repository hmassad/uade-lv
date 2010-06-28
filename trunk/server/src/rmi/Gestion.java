package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import server.Server;
import beans.CasillaVO;
import beans.MensajeVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;
import entities.Casilla;
import entities.Oficina;
import entities.Usuario;

public class Gestion extends UnicastRemoteObject implements InterfazGestion {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf;

	public Gestion() throws RemoteException {
		super();
		emf = Persistence.createEntityManagerFactory("default");
	}

	@Override
	protected void finalize() throws Throwable {
		if (emf != null) {
			emf.close();
		}
		super.finalize();
	}

	private Casilla buscarCasilla(CasillaVO c) {
		// crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {
			// crear consulta
			Query query = em.createNamedQuery("SELECT c FROM Casilla c WHERE c.id = :id");
			// agregar parámetros a consulta
			query.setParameter("id", c.getId());
			// ejecutar la consulta y devolver el resultado
			return (Casilla) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	private Usuario buscarUsuario(UsuarioVO u) {
		EntityManager em = emf.createEntityManager();
		try {
			// Query query =
			// em.createNamedQuery("SELECT u FROM Usuario u WHERE u.id = :id");
			// query.setParameter("id", u.getId());
			// return (Usuario) query.getSingleResult();
			return (Usuario) em.find(Usuario.class, u.getId());
		} finally {
			em.close();
		}
	}

	private Oficina buscarOficina(OficinaVO o) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("SELECT o FROM Oficina o WHERE o.id = :id");
			query.setParameter("id", o.getId());
			return (Oficina) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarCasillaAUsuario(UsuarioVO u, CasillaVO c) throws RemoteException {

		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {

			// busco el usuario
			Usuario usuario = em.find(Usuario.class, u.getId());
			if (usuario == null){
				throw new RemoteException("Usuario no encontrado.");
			}
			
			// Creo una Casilla
			Casilla casilla = new Casilla();
			casilla.setDireccion(c.getDireccion());
			casilla.setPassword(c.getPassword());
			usuario.agregarCasilla(casilla);

			// Inicio Transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {

				// Persisto la Casilla
				em.persist(casilla);

				// Persisto el Usuario
				em.persist(usuario);

				// Commit de la Transacción
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarCasillaAOficina(OficinaVO o, CasillaVO c) throws RemoteException {
		// TODO:
		throw new RemoteException("No Implementado");
	}

	@Override
	public Collection<CasillaVO> obtenerCasillas() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select c from Casilla c");
			ArrayList<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			@SuppressWarnings("unchecked")
			List<Casilla> casillas = (List<Casilla>) query.getResultList();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillaVO.setPassword(casilla.getPassword());
				casillasVO.add(casillaVO);
			}
			return casillasVO;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<CasillaVO> obtenerCasillasPorUsuario(UsuarioVO u) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select c from Casilla c join c.usuario u where u.id = :usuarioid");
			query.setParameter("usuarioid", u.getId());
			@SuppressWarnings("unchecked")
			List<Casilla> casillas = (List<Casilla>) query.getResultList();

			ArrayList<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillaVO.setPassword(casilla.getPassword());
				casillasVO.add(casillaVO);
			}
			return casillasVO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage(), e);
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<CasillaVO> obtenerCasillasPorOficina(OficinaVO o) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select c from Casilla c join c.usuario u where u.id = :usuarioid");
			query.setParameter("usuarioid", o.getId());
			@SuppressWarnings("unchecked")
			List<Casilla> casillas = (List<Casilla>) query.getResultList();

			ArrayList<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillaVO.setPassword(casilla.getPassword());
				casillasVO.add(casillaVO);
			}
			return casillasVO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage(), e);
		} finally {
			em.close();
		}
	}

	@Override
	public CasillaVO obtenerCasilla(CasillaVO c) throws RemoteException {
		Casilla casilla = buscarCasilla(c);
		if (casilla == null) {
			throw new RemoteException("No encontre la casilla");
		}

		CasillaVO casillaVO = new CasillaVO();
		casillaVO.setId(casilla.getId());
		casillaVO.setPassword(casilla.getPassword());
		casillaVO.setDireccion(casilla.getDireccion());
		return casillaVO;
	}

	@Override
	public void modificarCasilla(CasillaVO cOriginal, CasillaVO cNueva) throws RemoteException {
		Casilla casilla = buscarCasilla(cOriginal);
		if (casilla == null) {
			throw new RemoteException("No encontre la casilla");
		}

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				if (cNueva.getDireccion() != null) {
					casilla.setDireccion(cNueva.getDireccion());
				}
				if (cNueva.getPassword() != null) {
					casilla.setPassword(cNueva.getPassword());
				}
				em.persist(casilla);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void borrarCasilla(CasillaVO c) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			// Casilla casilla = em.find(Casilla.class, c.getId());
			Casilla casilla = em.getReference(Casilla.class, c.getId());
			tx.begin();
			try {
				em.remove(casilla);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarMensaje(CasillaVO casillaVO, MensajeVO mensajeVO) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public Collection<MensajeVO> obtenerMensajes() throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public MensajeVO obtenerMensaje(MensajeVO m) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void modificarMensaje(MensajeVO mOriginal, MensajeVO mNuevo) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void borrarMensaje(MensajeVO m) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void agregarOficina(OficinaVO o) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public Collection<OficinaVO> obtenerOficinas() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT o FROM Oficina o");
			@SuppressWarnings("unchecked")
			List<Oficina> oficinas = (List<Oficina>) q.getResultList();
			ArrayList<OficinaVO> oficinasVO = new ArrayList<OficinaVO>();
			for (Oficina oficina : oficinas) {
				OficinaVO oficinaVO = new OficinaVO();
				oficinaVO.setId(oficina.getId());
				oficinaVO.setNombre(oficina.getNombre());
				oficinasVO.add(oficinaVO);
			}
			return oficinasVO;
		} finally {
			em.close();
		}
	}

	@Override
	public OficinaVO obtenerOficina(OficinaVO o) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void modificarOficina(OficinaVO oOriginal, OficinaVO oNueva) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void borrarOficina(OficinaVO o) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void agregarRelacionConfianza(RelacionConfianzaVO rc) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public RelacionConfianzaVO obtenerRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestino) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void modificarRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestinoOriginal, OficinaVO oDestinoNueva) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void borrarRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestino) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public Collection<UsuarioVO> obtenerUsuarios() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u");
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) q.getResultList();
			ArrayList<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>();
			for (Usuario usuario : usuarios) {
				UsuarioVO usuarioVO = new UsuarioVO();
				usuarioVO.setId(usuario.getId());
				usuarioVO.setNombre(usuario.getNombre());
				usuariosVO.add(usuarioVO);
			}
			return usuariosVO;
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarUsuario(UsuarioVO u) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre(u.getNombre());
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				em.persist(usuario);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			}
		} finally {

			em.close();
		}
	}

	@Override
	public void borrarUsuario(UsuarioVO u) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void modificarUsuario(UsuarioVO uOriginal, UsuarioVO uNuevo) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}
}
