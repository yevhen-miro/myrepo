package de.hydro.gv.orgpm.dao.tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class SuperTest {

	protected static Logger logger = Logger.getLogger( "de.hydro.gv.orgpm" );

	@Deployment
	public static JavaArchive createDeployment() {

		JavaArchive arc = ShrinkWrap.create( JavaArchive.class, "orgpm-test.jar" )
				.addPackages( true, "de.hydro.gv.orgpm.actions" ).addPackages( true, "de.hydro.gv.orgpm.converters" )
				.addPackages( true, "de.hydro.gv.orgpm.models" ).addPackages( true, "de.hydro.gv.orgpm.view" )
				.addPackages( true, "de.hydro.gv.orgpm.web.tests" ).addPackages( true, "de.hydro.gv.orgpm.auth" )
				.addPackages( true, "de.hydro.gv.orgpm.data" ).addPackages( true, "de.hydro.gv.orgpm.dao" )
				.addPackages( true, "de.hydro.gv.orgpm.services" ).addPackages( true, "de.hydro.gv.orgpm.util" )
				.addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
				.addAsResource( "META-INF/initial_data.sql", "META-INF/initial_data.sql" )
				.addAsResource( "META-INF/ejb-jar.xml", "META-INF/ejb-jar.xml" );

		logger.info( arc.toString( true ) );
		return arc;

	}

}
