package test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPersistence {

	private EntityManagerFactory emf;

	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("default");
	}

	@After
	public void tearDown() {
		if (emf != null) {
			emf.close();
		}
	}

	@Test
	public void tesConection() {
		Assert.assertTrue(emf.isOpen());
	}
}
