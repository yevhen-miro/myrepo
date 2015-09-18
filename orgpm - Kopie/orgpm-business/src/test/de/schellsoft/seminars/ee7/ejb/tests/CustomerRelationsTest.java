package de.schellsoft.seminars.ee7.ejb.tests;

import java.util.Collection;
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
import de.schellsoft.seminars.ee7.custimercare.ejb.Order;
import de.schellsoft.seminars.ee7.custimercare.ejb.interceptors.PerformanceInterceptor;

@RunWith(Arquillian.class)
public class CustomerRelationsTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(CustomerPersistanceService.class.getPackage())
				.addPackage(PerformanceInterceptor.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml");
	}
	@Inject
	private CustomerPersistanceServiceLocal persistanceService;
	
	@Test
	public void createCustomerAndOrders(){
		Customer customer = new Customer ("Yevhen","Miroshnychenko",new Date());
		customer.addOrder(new Order(new Date(),99.99f));
		customer.addOrder(new Order(new Date(),999.99f));
		customer.addOrder(new Order(new Date(),9999.99f));
		
		persistanceService.createCustomer(customer);
		Collection<?> customerFound = persistanceService.executeQueryWithResults("customer.find.all");
		Assert.assertEquals(1, customerFound.size());
		Customer tempCustomer = (Customer) customerFound.iterator().next();
		Assert.assertEquals(1, tempCustomer.getOrders().size());
		
	}
	@Before
	@After
	public void resetDatabaseTables() {
		persistanceService.executeQuery("order.delete.all");
		persistanceService.executeQuery("customer.delete.all");
		Assert.assertEquals(0, persistanceService.executeQueryWithResults("order.find.all").size());
		
	}

}
