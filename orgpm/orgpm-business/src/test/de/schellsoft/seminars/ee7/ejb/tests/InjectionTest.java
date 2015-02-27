package de.schellsoft.seminars.ee7.ejb.tests;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class InjectionTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
					.addPackage(ProductDAO.class.getPackage());
	}
	
	@Inject
	@Named("myWonderfulJdbcImpl") //Named Annotation
	@Jdbc //Qualifier
	private ProductDAO productDao;

	@Test
	public void testInjections(){
		productDao.removeProduct("Waschmaschine");
	}
	

}
