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

import beans.CasillaVO;
import beans.LogTraficoVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;
import entities.Casilla;
import entities.Oficina;
import entities.RelacionConfianza;
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

	@SuppressWarnings("unused")
	private Usuario buscarUsuario(UsuarioVO u) {
		EntityManager em = emf.createEntityManager();
		try {
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

	private RelacionConfianza buscarRelacionConfianza(RelacionConfianzaVO r) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("SELECT r FROM RelacionConfianza r WHERE r.origen = :origen and r.destino = :destino");
			query.setParameter("origen", r.getOrigen().getId());
			query.setParameter("destino", r.getDestino().getId());
			return (RelacionConfianza) query.getSingleResult();
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
			if (usuario == null) {
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
			ArrayList<CasillaVO> casillasVo = new ArrayList<CasillaVO>();
			@SuppressWarnings("unchecked")
			ArrayList<Casilla> casillas = (ArrayList<Casilla>) query.getResultList();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillaVO.setPassword(casilla.getPassword());
				casillasVo.add(casillaVO);
			}
			return casillasVo;
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
	public void agregarOficina(OficinaVO o) throws RemoteException {
		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {
			// Inicio Transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				// Creo una Oficina
				Oficina oficina = new Oficina();
				oficina.setNombre(o.getNombre());

				// Persisto la Oficina
				em.persist(oficina);

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
		Oficina oficina = buscarOficina(o);
		if (oficina == null) {
			throw new RemoteException("No encontre la oficina");
		}

		OficinaVO oficinaVO = new OficinaVO();
		oficinaVO.setId(oficina.getId());
		oficinaVO.setNombre(oficina.getNombre());
		return oficinaVO;
	}

	@Override
	public void modificarOficina(OficinaVO oOriginal, OficinaVO oNueva) throws RemoteException {
		Oficina oficina = buscarOficina(oOriginal);
		if (oficina == null) {
			throw new RemoteException("No encontre la oficina");
		}

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				if (oNueva.getNombre() != null) {
					oficina.setNombre(oNueva.getNombre());
				}
				em.persist(oficina);
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
	public void borrarOficina(OficinaVO o) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			Oficina oficina = em.getReference(Oficina.class, o.getId());
			tx.begin();
			try {
				em.remove(oficina);
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
	public void agregarRelacionConfianza(RelacionConfianzaVO rc) throws RemoteException {
		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {
			// Inicio Transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				// Creo una RelacionConfianza
				RelacionConfianza relacion = new RelacionConfianza();
				relacion.setOrigen(buscarOficina(rc.getOrigen()));
				relacion.setDestino(buscarOficina(rc.getDestino()));

				// Persisto la RelacionConfianza
				em.persist(relacion);

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
	public Collection<RelacionConfianzaVO> obtenerRelacionesConfianza() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT rc FROM RelacionConfianza rc");
			@SuppressWarnings("unchecked")
			Collection<RelacionConfianza> relacionesConfianza = (Collection<RelacionConfianza>) q.getResultList();
			ArrayList<RelacionConfianzaVO> relacionesConfianzaVO = new ArrayList<RelacionConfianzaVO>();
			for (RelacionConfianza relacionConfianza : relacionesConfianza) {
				OficinaVO origen = new OficinaVO();
				origen.setId(relacionConfianza.getOrigen().getId());
				origen.setNombre(relacionConfianza.getOrigen().getNombre());

				OficinaVO destino = new OficinaVO();
				destino.setId(relacionConfianza.getDestino().getId());
				destino.setNombre(relacionConfianza.getDestino().getNombre());

				relacionesConfianzaVO.add(new RelacionConfianzaVO(origen, destino));
			}
			return relacionesConfianzaVO;
		} finally {
			em.close();
		}
	}

	@Override
	public RelacionConfianzaVO obtenerRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestino) throws RemoteException {
		// TODO Auto-generated method stub
		throw new RemoteException("No Implementado");
	}

	@Override
	public void modificarRelacionConfianza(OficinaVO oOrigen, OficinaVO oDestinoOriginal, OficinaVO oDestinoNueva) throws RemoteException {
		// Busco la RelacionConfianza
		RelacionConfianzaVO relacionVO = new RelacionConfianzaVO(oOrigen, oDestinoOriginal);
		RelacionConfianza relacion = buscarRelacionConfianza(relacionVO);
		if (relacion == null) {
			throw new RemoteException("No encontre la relacion de confianza");
		}

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				if (oDestinoNueva != null) {
					// Busco la Oficina
					Oficina oficina = buscarOficina(oDestinoNueva);
					relacion.setDestino(oficina);
				}
				em.persist(relacion);
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

	@Override
	public Collection<LogTraficoVO> obtenerLogs() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
