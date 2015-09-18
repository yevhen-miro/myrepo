package de.schellsoft.seminars.ee7.ejb.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter;
import de.schellsoft.seminars.ee7.custimercare.ejb.MitarbeiterPersistanceService;
import de.schellsoft.seminars.ee7.custimercare.ejb.MitarbeiterPersistanceServiceLocal;
import de.schellsoft.seminars.ee7.custimercare.ejb.interceptors.PerformanceInterceptor;
import de.schellsoft.seminars.ee7.customercare.cdi.Producers;


@RunWith(Arquillian.class)
public class MitarbeiterPersistanceServiceTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(MitarbeiterPersistanceService.class.getPackage())
				.addPackage(PerformanceInterceptor.class.getPackage()).addPackage(Producers.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml");
	}
	
	@Inject
	private MitarbeiterPersistanceServiceLocal maPersistanceService;
	
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
	public void resetDatabaseTables (){
		//persistanceService.removeAllCustomers();
		maPersistanceService.executeQuery("mitarbeiter.delete.all");
		Assert.assertEquals(0, maPersistanceService.executeQueryWithResults("mitarbeiter.find.all").size());
	}
	
	@Test
	public void testCreateMitarbeiter() {
		
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		
		maPersistanceService.createMitarbeiter(mitarbeiter);
		
		Assert.assertEquals(1,maPersistanceService.readAllMitarbeiter().size() );
		
	}
	
	@Test
	public void testReadAllMitarbeiter() {
		
		for(int pos = 0; pos<3; pos ++)
			maPersistanceService.createMitarbeiter(new Mitarbeiter());
		Assert.assertEquals(3, maPersistanceService.readAllMitarbeiter().size());
		
	}
	
	@Test
	public void testUpdateMitarbeiter() {
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		maPersistanceService.createMitarbeiter(mitarbeiter);
		mitarbeiter.setVorname("Yevheniy");
		maPersistanceService.updateMitarbeiter(mitarbeiter);
		Assert.assertEquals("Yevheniy", maPersistanceService.readAllMitarbeiter().get(0).getVorname());
		
	}
	
	@Test
	public void testDeleteMitarbeiter(){
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		
		maPersistanceService.createMitarbeiter(mitarbeiter);
		
		maPersistanceService.deleteMitarbeiter(mitarbeiter);
		Assert.assertEquals(0,maPersistanceService.readAllMitarbeiter().size() );
		
	}
	
	@Test
	public void testGetQuery(){
		Mitarbeiter mitarbeiter = createTestMitarbeiter();
		maPersistanceService.createMitarbeiter(mitarbeiter);
		maPersistanceService.executeQueryWithResults("mitarbeiter.find.all");
		Assert.assertEquals(1,maPersistanceService.readAllMitarbeiter().size() );
	}
	
	@Test
	public void  readAndConvertMitarbeiter() {
		@SuppressWarnings("unchecked")
		List<de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter> results = (List<de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter>) maPersistanceService
				.executeQueryWithResults("mitarbeiter.find.all");
		ArrayList<Mitarbeiter> arrayList = new ArrayList<Mitarbeiter>();
		for (de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter mitarbeiter : results)
			arrayList.add(convToModel(mitarbeiter));
	}
	
	private Mitarbeiter convToModel(
			de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter mitarbeiter) {
		Mitarbeiter mitarbeiterModel = new Mitarbeiter();
		mitarbeiterModel.setName(mitarbeiter.getName());
		mitarbeiterModel.setVorname(mitarbeiter.getVorname());
		mitarbeiterModel.setArbeitszeit(mitarbeiter.getArbeitszeit());
		mitarbeiterModel.setBemerkung(mitarbeiter.getBemerkung());
		mitarbeiterModel.setgruppe(mitarbeiter.getgruppe());
		mitarbeiterModel.setHydroId(mitarbeiter.getHydroId());
		mitarbeiterModel.setKartenNum(mitarbeiter.getKartenNum());
		mitarbeiterModel.setMitarbeiterkennung(mitarbeiter.getMitarbeiterkennung());
		mitarbeiterModel.setMitarbeiterStatus(mitarbeiter.getMitarbeiterStatus());
		mitarbeiterModel.setPersonalNum(mitarbeiter.getPersonalNum());

		return mitarbeiterModel;
	}

}
