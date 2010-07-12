package rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import rmi.observer.EventoObservable;
import rmi.observer.RemoteObservable;
import rmi.observer.RemoteObservableImpl;
import rmi.observer.RemoteObserver;
import seguridad.Encriptador;
import beans.CasillaVO;
import beans.LogTraficoVO;
import beans.OficinaVO;
import beans.RelacionConfianzaVO;
import beans.UsuarioVO;
import entities.Casilla;
import entities.LogTrafico;
import entities.Oficina;
import entities.RelacionConfianza;
import entities.RelacionConfianzaPk;
import entities.Usuario;

public class Gestion extends UnicastRemoteObject implements InterfazGestion, Serializable, RemoteObservable {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf;

	private RemoteObservable remoteObservable;

	public Gestion() throws RemoteException {
		super();
		emf = Persistence.createEntityManagerFactory("default");
		remoteObservable = new RemoteObservableImpl();
	}

	@Override
	protected void finalize() throws Throwable {
		if (emf != null) {
			emf.close();
		}
		super.finalize();
	}

	@Override
	public void addRemoteObserver(RemoteObserver o) throws RemoteException {
		remoteObservable.addRemoteObserver(o);
	}

	@Override
	public void deleteRemoteObserver(RemoteObserver o) throws RemoteException {
		remoteObservable.deleteRemoteObserver(o);
	}

	@Override
	public void notifyRemoteObservers(EventoObservable eventoObservable) throws RemoteException {
		remoteObservable.notifyRemoteObservers(eventoObservable);
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
	public void agregarUsuario(String nombreUsuario, String password) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				Usuario usuario = new Usuario();
				usuario.setNombre(nombreUsuario);
				usuario.setPassword(Encriptador.encriptar(password));
				em.persist(usuario);
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Usuarios);
	}

	@Override
	public void modificarUsuario(int idUsuario, String nombre) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();

				Usuario usuario = em.find(Usuario.class, idUsuario);
				usuario.setNombre(nombre);
				em.merge(usuario);

				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Usuarios);
	}

	@Override
	public String resetearContraseña(int idUsuario) throws RemoteException {
		String nuevaContraseña = new SimpleDateFormat("ddMMyyyy").format(new Date());
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				Usuario usuario = em.find(Usuario.class, idUsuario);
				usuario.setPassword(Encriptador.encriptar(nuevaContraseña));
				em.merge(usuario);
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
			return nuevaContraseña;
		} finally {
			em.close();
		}
	}

	@Override
	public void borrarUsuario(int id) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				Usuario usuario = em.getReference(Usuario.class, id);
				em.remove(usuario);
				em.flush();
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Usuarios);
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
				casillasVo.add(casillaVO);
			}
			return casillasVo;
		} finally {
			em.close();
		}
	}

	@Override
	public CasillaVO obtenerCasilla(int idCasilla) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = (Casilla) em.find(Casilla.class, idCasilla);
			if (casilla == null) {
				throw new RemoteException("Casilla no encontrada.");
			}

			CasillaVO casillaVO = new CasillaVO();
			casillaVO.setId(casilla.getId());
			casillaVO.setDireccion(casilla.getDireccion());
			return casillaVO;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<CasillaVO> obtenerCasillasPorUsuario(int idUsuario) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select c from Casilla c join c.usuario u where u.id = :usuarioid");
			query.setParameter("usuarioid", idUsuario);
			@SuppressWarnings("unchecked")
			List<Casilla> casillas = (List<Casilla>) query.getResultList();

			ArrayList<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillasVO.add(casillaVO);
			}
			return casillasVO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<CasillaVO> obtenerCasillasPorOficina(int idOficina) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select c from Casilla c join c.oficina o where o.id = :oficina_id");
			query.setParameter("oficina_id", idOficina);
			@SuppressWarnings("unchecked")
			List<Casilla> casillas = (List<Casilla>) query.getResultList();

			ArrayList<CasillaVO> casillasVO = new ArrayList<CasillaVO>();
			for (Casilla casilla : casillas) {
				CasillaVO casillaVO = new CasillaVO();
				casillaVO.setId(casilla.getId());
				casillaVO.setDireccion(casilla.getDireccion());
				casillasVO.add(casillaVO);
			}
			return casillasVO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarCasilla(int idUsuario, String direccion) throws RemoteException {
		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {

			// busco el usuario
			Usuario usuario = em.find(Usuario.class, idUsuario);
			if (usuario == null) {
				throw new RemoteException("Usuario no encontrado.");
			}

			// Creo una Casilla
			Casilla casilla = new Casilla();
			casilla.setDireccion(direccion);
			usuario.agregarCasilla(casilla);

			// Inicio Transacción
			EntityTransaction et = em.getTransaction();
			et.begin();
			try {

				// Persisto la Casilla
				em.persist(casilla);

				// Persisto el Usuario
				em.merge(usuario);

				em.flush();

				// Commit de la Transacción
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Casillas);
	}

	@Override
	public void agregarCasillaAOficina(int idOficina, int idCasilla) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();
				Oficina oficina = em.find(Oficina.class, idOficina);
				Casilla casilla = em.find(Casilla.class, idCasilla);

				oficina.agregarCasilla(casilla);

				em.merge(oficina);
				em.merge(casilla);
				em.flush();

				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Oficinas);
	}

	@Override
	public void modificarCasilla(int idCasilla, String direccion) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Casilla casilla = em.find(Casilla.class, idCasilla);
			if (casilla == null) {
				throw new RemoteException("Casilla no encontrada.");
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				casilla.setDireccion(direccion);
				em.merge(casilla);
				em.flush();
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Casillas);
	}

	@Override
	public void borrarCasilla(int idCasilla) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			Casilla casilla = em.find(Casilla.class, idCasilla);
			tx.begin();
			try {
				em.remove(casilla);
				em.flush();
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Casillas);
	}

	@Override
	public void borrarCasillaDeOficina(int idOficina, int idCasilla) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				Oficina oficina = em.find(Oficina.class, idOficina);
				Casilla casilla = em.find(Casilla.class, idCasilla);

				oficina.borrarCasilla(casilla);

				em.merge(oficina);
				em.merge(casilla);
				em.flush();

				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Oficinas);
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
	public OficinaVO obtenerOficina(int idOficina) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Oficina oficina = em.find(Oficina.class, idOficina);
			if (oficina == null) {
				throw new RemoteException("Oficina no encontrada.");
			}

			OficinaVO oficinaVO = new OficinaVO();
			oficinaVO.setId(oficina.getId());
			oficinaVO.setNombre(oficina.getNombre());
			return oficinaVO;
		} finally {
			em.close();
		}
	}

	@Override
	public Collection<OficinaVO> obtenerOficinasPorCasilla(int idCasilla) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery("select o from Oficina o join o.casillas c where c.id = :casilla_id");
			query.setParameter("casilla_id", idCasilla);
			@SuppressWarnings("unchecked")
			List<Oficina> oficinas = (List<Oficina>) query.getResultList();

			ArrayList<OficinaVO> oficinasVO = new ArrayList<OficinaVO>();
			for (Oficina oficina : oficinas) {
				OficinaVO oficinaVO = new OficinaVO();
				oficinaVO.setId(oficina.getId());
				oficinaVO.setNombre(oficina.getNombre());
				oficinasVO.add(oficinaVO);
			}
			return oficinasVO;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoteException(e.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarOficina(String nombre) throws RemoteException {
		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {
			// Inicio Transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				// Creo una Oficina
				Oficina oficina = new Oficina();
				oficina.setNombre(nombre);

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
		notifyRemoteObservers(EventoObservable.Oficinas);
	}

	@Override
	public void modificarOficina(int idOficina, String nombre) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Oficina oficina = (Oficina) em.find(Oficina.class, idOficina);
			if (oficina == null) {
				throw new RemoteException("Oficina no encontrada.");
			}

			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				oficina.setNombre(nombre);
				em.merge(oficina);
				em.flush();
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Oficinas);
	}

	@Override
	public void borrarOficina(int idOficina) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			Oficina oficina = em.getReference(Oficina.class, idOficina);
			tx.begin();
			try {
				em.remove(oficina);
				em.flush();
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.Oficinas);
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
	public RelacionConfianzaVO obtenerRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("SELECT r FROM RelacionConfianza r WHERE r.origen.id = :origen_id and r.destino = :destino_id");
			query.setParameter("origen_id", idOficinaOrigen);
			query.setParameter("destino_id", idOficinaDestino);
			RelacionConfianza relacion = (RelacionConfianza) query.getSingleResult();

			RelacionConfianzaVO rcVO = new RelacionConfianzaVO();

			OficinaVO oficinaOrigenVO = new OficinaVO();
			oficinaOrigenVO.setId(relacion.getOrigen().getId());
			oficinaOrigenVO.setNombre(relacion.getOrigen().getNombre());
			rcVO.setOrigen(oficinaOrigenVO);

			OficinaVO oficinaDestinoVO = new OficinaVO();
			oficinaOrigenVO.setId(relacion.getDestino().getId());
			oficinaOrigenVO.setNombre(relacion.getDestino().getNombre());
			rcVO.setDestino(oficinaDestinoVO);

			return rcVO;
		} finally {
			em.close();
		}
	}

	@Override
	public void agregarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException {
		// Crear el EntityManager
		EntityManager em = emf.createEntityManager();
		try {
			// Inicio Transacción
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			try {
				// Creo una RelacionConfianza
				RelacionConfianza relacion = new RelacionConfianza();
				relacion.setOrigen((Oficina) em.find(Oficina.class, idOficinaOrigen));
				relacion.setDestino((Oficina) em.find(Oficina.class, idOficinaDestino));

				// Persisto la RelacionConfianza
				em.persist(relacion);
				em.flush();

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
		notifyRemoteObservers(EventoObservable.RelacionesConfianza);
	}

	@Override
	public void modificarRelacionConfianza(int idOficinaOrigen, int idOficinaDestinoOriginal, int idOficinaDestinoNueva) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();

				RelacionConfianzaPk rcpk = new RelacionConfianzaPk();
				rcpk.setOrigen(em.find(Oficina.class, idOficinaOrigen));
				rcpk.setDestino(em.find(Oficina.class, idOficinaDestinoOriginal));
				RelacionConfianza rc = em.find(RelacionConfianza.class, rcpk);
				rc.setDestino(em.find(Oficina.class, idOficinaDestinoNueva));
				em.merge(rc);
				em.flush();

				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.RelacionesConfianza);
	}

	@Override
	public void borrarRelacionConfianza(int idOficinaOrigen, int idOficinaDestino) throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();

				RelacionConfianzaPk rcpk = new RelacionConfianzaPk();
				rcpk.setOrigen(em.find(Oficina.class, idOficinaOrigen));
				rcpk.setDestino(em.find(Oficina.class, idOficinaDestino));
				RelacionConfianza rc = em.find(RelacionConfianza.class, rcpk);
				em.remove(rc);
				em.flush();

				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.RelacionesConfianza);
	}

	@Override
	public Collection<LogTraficoVO> obtenerLogs() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT l FROM LogTrafico l");

			@SuppressWarnings("unchecked")
			List<LogTrafico> logs = (List<LogTrafico>) q.getResultList();

			ArrayList<LogTraficoVO> logsVO = new ArrayList<LogTraficoVO>();
			for (LogTrafico log : logs) {
				LogTraficoVO logVO = new LogTraficoVO();
				logVO.setFecha(log.getFecha());
				logVO.setOrigen(log.getOrigen());
				for (String destino : log.getDestinos()) {
					logVO.agregarDestino(destino);
				}
				logsVO.add(logVO);
			}
			return logsVO;
		} finally {
			em.close();
		}
	}

	@Override
	public void borrarLogs() throws RemoteException {
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			try {
				et.begin();

				Query q = em.createQuery("SELECT l FROM LogTrafico l");
				@SuppressWarnings("unchecked")
				List<LogTrafico> logs = (List<LogTrafico>) q.getResultList();
				for (LogTrafico log : logs) {
					em.remove(log);
				}
				em.flush();
				et.commit();
			} catch (Exception e) {
				et.rollback();
				e.printStackTrace();
				throw new RemoteException(e.getMessage());
			}
		} finally {
			em.close();
		}
		notifyRemoteObservers(EventoObservable.LogTrafico);
	}
}
