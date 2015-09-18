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

import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.ProjektService;

@RunWith( Arquillian.class )
public class ProjektDaoTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create( JavaArchive.class, "test.jar" ).addPackages( true, "de.hydro.gv.orgpm.dao" )
				.addPackages( true, "de.hydro.gv.orgpm.data" ).addPackages( true, "de.hydro.gv.orgpm.util" )
				.addPackages( true, "de.hydro.gv.orgpm.services" )
				.addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
				.addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
	}

	@Inject
	private ProjektService projektService;

	private Projekt createTestProjekt() {
		Projekt projekt = new Projekt();

		projekt.setProjektId( "TTST" );

		return projekt;

	}

	@Test
	public void testCreateProjekt() throws Exception {

		Projekt projekt = this.createTestProjekt();

		this.projektService.addProjekt( projekt );

		Assert.assertEquals( 1, this.projektService.getAlleProjekte().size() );

	}

}