package de.hydro.gv.orgpm.dao.tests;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
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

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.dao.MitarbeiterDaoLocal;

@RunWith(Arquillian.class)
public class MitarbeiterDaoTest {

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
	private MitarbeiterDaoLocal mitarbeiterDao;

	private Mitarbeiter createTestMitarbeiter() {
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setVorname("Yevhen");
		mitarbeiter.setName("Miroshnychenko");
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

	@Test
	public void testReadAllMitarbeiter() {

		for (int pos = 0; pos < 3; pos++)
			mitarbeiterDao.createMitarbeiter(new Mitarbeiter());
		Assert.assertEquals(3, mitarbeiterDao.readAllMitarbeiter().size());

	}

	@Test
	public void testUpdateMitarbeiter() {
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		mitarbeiterDao.createMitarbeiter(mitarbeiter);
		mitarbeiter.setVorname("Yevheniy");
		mitarbeiterDao.updateMitarbeiter(mitarbeiter);
		Assert.assertEquals("Yevheniy", mitarbeiterDao
				.readAllMitarbeiter().get(0).getVorname());

	}

	@Test
	public void testDeleteMitarbeiter() {
		Mitarbeiter mitarbeiter = createTestMitarbeiter();

		mitarbeiterDao.createMitarbeiter(mitarbeiter);

		mitarbeiterDao.deleteMitarbeiter(mitarbeiter);
		Assert.assertEquals(0, mitarbeiterDao.readAllMitarbeiter().size());

	}

	@Test
	public void testGetQuery() {
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		mitarbeiterDao.createMitarbeiter(mitarbeiter);
		mitarbeiterDao.executeQueryWithResults("mitarbeiter.find.all");
		Assert.assertEquals(1, mitarbeiterDao.readAllMitarbeiter().size());
	}

	@Test
	public void readAndConvertMitarbeiter() {
		@SuppressWarnings("unchecked")
		List<de.hydro.gv.orgpm.data.Mitarbeiter> results = (List<de.hydro.gv.orgpm.data.Mitarbeiter>) mitarbeiterDao
				.executeQueryWithResults("mitarbeiter.find.all");
		ArrayList<Mitarbeiter> arrayList = new ArrayList<Mitarbeiter>();
		for (de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter : results)
			arrayList.add(convToModel(mitarbeiter));
	}

	private Mitarbeiter convToModel(
			de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter) {
		Mitarbeiter mitarbeiterModel = new Mitarbeiter();
		mitarbeiterModel.setName(mitarbeiter.getName());
		mitarbeiterModel.setVorname(mitarbeiter.getVorname());
		mitarbeiterModel.setArbeitszeit(mitarbeiter.getArbeitszeit());
		mitarbeiterModel.setBemerkung(mitarbeiter.getBemerkung());
		mitarbeiterModel.setgruppe(mitarbeiter.getgruppe());
		mitarbeiterModel.setHydroId(mitarbeiter.getHydroId());
		mitarbeiterModel.setKartenNum(mitarbeiter.getKartenNum());
		mitarbeiterModel.setMitarbeiterkennung(mitarbeiter
				.getMitarbeiterkennung());
		mitarbeiterModel.setMitarbeiterStatus(mitarbeiter
				.getMitarbeiterStatus());
		mitarbeiterModel.setPersonalNum(mitarbeiter.getPersonalNum());

		return mitarbeiterModel;
	}
}
