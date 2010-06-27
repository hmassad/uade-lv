package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Usuario;

public class JpaTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		try {
			EntityManager em = emf.createEntityManager();
			try {
				Query q = em.createQuery("select u from entities.Usuario u");

				@SuppressWarnings("unchecked")
				List<Usuario> usuarios = (List<Usuario>)q.getResultList();
				
				for (Usuario u : usuarios) {
					System.out.println(u.toString());
				}
			} finally {
				em.close();
			}
		} finally {
			emf.close();
		}
	}

}
