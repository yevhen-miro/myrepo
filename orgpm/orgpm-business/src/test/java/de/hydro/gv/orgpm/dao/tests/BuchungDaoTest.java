package de.hydro.gv.orgpm.dao.tests;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.dao.BuchungDao;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@RunWith( Arquillian.class )
public class BuchungDaoTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create( JavaArchive.class, "test.jar" ).addPackages( true, "de.hydro.gv.orgpm.dao" )
				.addPackages( true, "de.hydro.gv.orgpm.data" ).addPackages( true, "de.hydro.gv.orgpm.util" )
				.addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
	}

	@Inject
	private BuchungDao buchungDao;

	@Inject
	private MitarbeiterDao mitarbeiterDao;

	private Buchung createTestBuchung() {
		Buchung buchung = new Buchung();
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setName( "Miroshnychenko_Test" );
		mitarbeiter.setHydroId( 136862 );
		this.mitarbeiterDao.createMitarbeiter( mitarbeiter );

		buchung.setMitarbeiter( mitarbeiter );
		buchung.setAktivitaetId( new Aktivitaet() );
		buchung.setAnfangZeit( new Date() );
		buchung.setDatum( new Date() );
		buchung.setEndeZeit( new Date() );
		buchung.setMin( 23 );
		buchung.setPauseBis( null );
		buchung.setPauseVon( null );
		buchung.setStd( 2 );
		buchung.setTaetigkeiten( "JUNIT test" );
		buchung.setWartungId( 0 );
		return buchung;

	}

	@Before
	// @After
	public void resetDatabaseTables() {
		this.buchungDao.executeQuery( "buchung.delete.all" );
		this.mitarbeiterDao.executeQuery( "mitarbeiter.delete.all" );
		Assert.assertEquals( 0, this.mitarbeiterDao.executeQueryWithResults( "buchung.find.all" ).size() );
	}

	@Test
	public void testCreateBuchung() {

		Buchung buchung = this.createTestBuchung();

		this.buchungDao.createBuchung( buchung );

		// Assert.assertEquals(1, mitarbeiterDao.readAllMitarbeiter().size());
		// buchung.setMitarbeiter(mitarbeiterDao.);

	}

	// @Test
	public void testGetBuchungByMitarbeiter() {

		Buchung buchung = this.createTestBuchung();

		this.buchungDao.createBuchung( buchung );

		this.buchungDao.getBuchungById( 24L );
		Buchung buchung1 = new Buchung();
		buchung1.setTaetigkeiten( "new test" );
		this.buchungDao.createBuchung( buchung );
		this.buchungDao.updateBuchung( buchung );

		// buchungDao.getBuchungByMitarbeiter(136862);

		// Assert.assertEquals(1,
		// buchungDao.getBuchungByMitarbeiter(136862).size());
	}

	@Test
	public void testUpdateBuchung() {
		Buchung buchung = this.createTestBuchung();
		this.buchungDao.createBuchung( buchung );
		buchung.setTaetigkeiten( "new test" );
		this.buchungDao.updateBuchung( buchung );
		Assert.assertEquals( "new test", this.buchungDao.readAllBuchungen().get( 0 ).getTaetigkeiten() );

	}
}
