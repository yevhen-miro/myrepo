package de.hydro.gv.orgpm.dao.tests;

import javax.inject.Inject;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.Entities.Mitarbeiter;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.util.Logger;

@RunWith(Arquillian.class)
public class MitarbeiterDaoTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(MitarbeiterDao.class.getPackage())
				.addPackage(Logger.class.getPackage())
				.addPackage(Mitarbeiter.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml").addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
	}

	@Inject
	private MitarbeiterDao mitarbeiterDao;

	private Mitarbeiter createTestMitarbeiter() {
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setVorname("Yevhen");
		mitarbeiter.setName("Miro");
		mitarbeiter.setArbeitszeit(8.0);
		mitarbeiter.setBemerkung("Test bemerkung");
		mitarbeiter.setgruppe("FAS");
		mitarbeiter.setHydroId(136862);
		mitarbeiter.setKartenNum(36558);
		mitarbeiter.setMitarbeiterkennung("gap87");
		mitarbeiter.setMitarbeiterStatus(0.0);
		mitarbeiter.setPersonalNum(7772);
		return mitarbeiter;
	}

	@Before
	@After
	public void resetDatabaseTables() {
		// persistanceService.removeAllCustomers();
		mitarbeiterDao.executeQuery("mitarbeiter.delete.all");
		Assert.assertEquals(0,
				mitarbeiterDao.executeQueryWithResults("mitarbeiter.find.all")
						.size());
	}

	@Test
	public void testCreateMitarbeiter() {

		Mitarbeiter mitarbeiter = createTestMitarbeiter();

		mitarbeiterDao.createMitarbeiter(mitarbeiter);

		Assert.assertEquals(1, mitarbeiterDao.readAllMitarbeiter().size());

	}

}
