package de.hydro.gv.mplus.dao.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
 
public class SuperTest {
 
   protected static Logger logger = Logger.getLogger( "de.hydro.gv.mplus" );
 
   @Deployment
   public static WebArchive createWarDeployment() {
       MavenResolverSystem resolver = Maven.resolver();
 
       WebArchive war = ShrinkWrap
               .create( WebArchive.class, "mplus-test.war" )
               .addAsWebInfResource( "mplus-test-ds.xml", "mplus-test-ds.xml" )
               .addAsWebInfResource( EmptyAsset.INSTANCE, "beans.xml" )
               .addAsLibraries( createJarDeployment() )
               .addAsLibraries(
                       resolver.resolve( "org.apache.commons:commons-lang3:3.4",
                               "org.apache.commons:commons-collections4:4.0",
                               "org.hamcrest:hamcrest-all:1.3" )
                               .withoutTransitivity().as( JavaArchive.class ) );
 
       logger.info( war.toString( true ) );
       return war;
   }

   public static JavaArchive createJarDeployment() {
       JavaArchive arc = ShrinkWrap.create( JavaArchive.class, "mplus-test.jar" )
               .addPackages( true, "de.hydro.gv.mplus.data" )
               .addPackages( true, "de.hydro.gv.mplus.utils" )
               .addPackages( true, "de.hydro.gv.mplus.dao" )
               .addPackages( true, "de.hydro.gv.mplus.dao.test" )
               .addAsResource( "META-INF/persistence.xml", "META-INF/persistence.xml" )
               .addAsResource( "META-INF/MANIFEST.MF", "META-INF/MANIFEST.MF" )
               .addAsResource( "META-INF/initial_data.sql")
               .addAsResource( "META-INF/ejb-jar.xml", "META-INF/ejb-jar.xml" ).addAsResource( "users.properties" )
               .addAsManifestResource( EmptyAsset.INSTANCE, "beans.xml" );
 
       logger.info( arc.toString( true ) );
       return arc;
 
   }
}
