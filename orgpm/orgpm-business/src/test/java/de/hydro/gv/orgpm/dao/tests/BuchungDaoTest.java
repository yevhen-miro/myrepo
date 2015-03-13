package de.hydro.gv.orgpm.dao.tests;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.dao.BuchungDaoLocal;
import de.hydro.gv.orgpm.dao.MitarbeiterDaoLocal;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@RunWith(Arquillian.class)
public class BuchungDaoTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackages(true, "de.hydro.gv.orgpm.dao")
				.addPackages(true, "de.hydro.gv.orgpm.data")
				.addPackages(true, "de.hydro.gv.orgpm.util")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private BuchungDaoLocal buchungDao;

	@Inject
	private MitarbeiterDaoLocal mitarbeiterDao;

	private Buchung createTestBuchung() {
		Buchung buchung = new Buchung();
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setName("Miroshnychenko_Test");
		mitarbeiter.setHydroId(136862);
		mitarbeiterDao.createMitarbeiter(mitarbeiter);

		buchung.setMitarbeiter(mitarbeiter);
		buchung.setAktivitaetId(1);
		buchung.setAnfangZeit(new Date());
		buchung.setDatum(new Date());
		buchung.setEndeZeit(new Date());
		buchung.setMin(23);
		buchung.setPauseBis(null);
		buchung.setPauseVon(null);
		buchung.setProjektId("f3ls");
		buchung.setStd(2);
		buchung.setTaetigkeiten("JUNIT test");
		buchung.setWartungId(0);
		return buchung;

	}

	@Before
	// @After
	public void resetDatabaseTables() {
		buchungDao.executeQuery("buchung.delete.all");
		mitarbeiterDao.executeQuery("mitarbeiter.delete.all");
		Assert.assertEquals(0,
				mitarbeiterDao.executeQueryWithResults("buchung.find.all")
						.size());
	}

	@Test
	public void testCreateBuchung() {

		Buchung buchung = createTestBuchung();

		buchungDao.createBuchung(buchung);

		// Assert.assertEquals(1, mitarbeiterDao.readAllMitarbeiter().size());
		// buchung.setMitarbeiter(mitarbeiterDao.);

	}
	
	//@Test
	public void testGetBuchungByMitarbeiter() {
		
		Buchung buchung = createTestBuchung();

		buchungDao.createBuchung(buchung);
		
		buchungDao.getBuchungById(24L);
		Buchung buchung1 = new Buchung();
		buchung1.setTaetigkeiten("new test");
		buchungDao.createBuchung(buchung);
		buchungDao.updateBuchung(buchung);
		
		//buchungDao.getBuchungByMitarbeiter(136862);
		
		//Assert.assertEquals(1, buchungDao.getBuchungByMitarbeiter(136862).size());
	}
	
	@Test
	public void testUpdateBuchung() {
		Buchung buchung = createTestBuchung();
		buchungDao.createBuchung(buchung);
		buchung.setTaetigkeiten("new test");
		buchungDao.updateBuchung(buchung);
		Assert.assertEquals("new test", buchungDao
				.readAllBuchungen().get(0).getTaetigkeiten());

	}
}
