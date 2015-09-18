package de.schellsoft.seminars.ee7.ejb.tests;

import java.util.Date;

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

import de.schellsoft.seminars.ee7.custimercare.ejb.Customer;
import de.schellsoft.seminars.ee7.custimercare.ejb.CustomerPersistanceService;
import de.schellsoft.seminars.ee7.custimercare.ejb.CustomerPersistanceServiceLocal;
import de.schellsoft.seminars.ee7.custimercare.ejb.interceptors.PerformanceInterceptor;
import de.schellsoft.seminars.ee7.customercare.cdi.Producers;



@RunWith(Arquillian.class)
public class CustomerPersistanceServiceTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(CustomerPersistanceService.class.getPackage())
				.addPackage(PerformanceInterceptor.class.getPackage()).addPackage(Producers.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml");
	}
	
	@Inject
	private CustomerPersistanceServiceLocal persistanceService;
	
	private Customer createTestCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Yevhen");
		customer.setLastName("Miroshnychenko");
		customer.setDateOfBirth(new Date());
		return customer;
	}
	
	@Before
	@After
	public void resetDatabaseTables (){
		//persistanceService.removeAllCustomers();
		persistanceService.executeQuery("order.delete.all");
		persistanceService.executeQuery("customer.delete.all");
		Assert.assertEquals(0, persistanceService.executeQueryWithResults("order.find.all").size());
		Assert.assertEquals(0, persistanceService.executeQueryWithResults("customer.find.all").size());
	}
	
	@Test
	public void testCreateCustomer() {
		
		Customer customer = createTestCustomer();
		
		persistanceService.createCustomer(customer);
		
		Assert.assertEquals(1,persistanceService.readAllCustomers().size() );
		
	}
	
	@Test
	public void testReadAllCustomers() {
		
		for(int pos = 0; pos<3; pos ++)
			persistanceService.createCustomer(new Customer());
		Assert.assertEquals(3, persistanceService.readAllCustomers().size());
		
	}
	
	@Test
	public void testUpdateCustomers() {
		Customer customer = createTestCustomer();
		persistanceService.createCustomer(customer);
		customer.setFirstName("Yevheniy");
		persistanceService.updateCustomer(customer);
		Assert.assertEquals("Yevheniy", persistanceService.readAllCustomers().get(0).getFirstName());
		
	}
	
	@Test
	public void testHashCode(){
		persistanceService.hashCode();
	}
	
	@Test
	public void testDeleteCustomers(){
		Customer customer = createTestCustomer();
		
		persistanceService.createCustomer(customer);
		
		persistanceService.deleteCustomer(customer);
		Assert.assertEquals(0,persistanceService.readAllCustomers().size() );
		
	}



	
	
	
	
}
