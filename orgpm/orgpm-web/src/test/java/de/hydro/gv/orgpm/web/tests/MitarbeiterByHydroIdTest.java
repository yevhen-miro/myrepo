package de.hydro.gv.orgpm.web.tests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( Arquillian.class )
public class MitarbeiterByHydroIdTest extends SuperTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testFindMitarbeiterByHydroId() {

	}

}
