package de.hydro.gv.orgpm.dao.tests;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.dao.AktivitaetDao;
import de.hydro.gv.orgpm.dao.ProjektDao;
import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;

@RunWith( Arquillian.class )
public class AktivitaetDaoTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create( JavaArchive.class, "test.jar" ).addPackages( true, "de.hydro.gv.orgpm.dao" )
				.addPackages( true, "de.hydro.gv.orgpm.data" ).addPackages( true, "de.hydro.gv.orgpm.util" )
				.addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
				.addAsResource( "META-INF/initial_load.sql", "META-INF/initial_load.sql" )
				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
	}

	@Inject
	private AktivitaetDao aktivitaetDao;

	@Inject
	private ProjektDao projektDao;

	@Test
	public void testGetAktivitaetByProjektName() {

		Assert.assertEquals( 15, this.aktivitaetDao.getAktivitaetenByProjektName( "fls000" ).size() );

	}

	@Test
	public void testGetAktivitaetByProjekt() throws Exception {

		Projekt projekt = new Projekt();
		projekt.setProjektName( "test" );
		projekt.setId( 300L );
		this.projektDao.createProjekt( projekt );

		Aktivitaet akt = new Aktivitaet();
		akt.setAktivitaetNr( 1 );
		akt.setAktivitaetText( "test" );
		akt.setProjekt( projekt );
		this.aktivitaetDao.addAktivitaet( akt );

		Aktivitaet akt2 = new Aktivitaet();
		akt.setAktivitaetNr( 2 );
		akt.setProjekt( projekt );
		this.aktivitaetDao.addAktivitaet( akt2 );

		Assert.assertEquals( 1, this.aktivitaetDao.getAktivitaetenByProjekt( projekt.getProjektId() ).size() );

	}

}