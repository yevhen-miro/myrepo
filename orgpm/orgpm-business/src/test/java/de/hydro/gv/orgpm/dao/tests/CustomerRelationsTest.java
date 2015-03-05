package de.hydro.gv.orgpm.dao.tests;

import java.util.Collection;
import java.util.Date;

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

import de.hydro.gv.orgpm.dao.CustomerPersistanceService;
import de.hydro.gv.orgpm.dao.CustomerPersistanceServiceLocal;
import de.hydro.gv.orgpm.data.Customer;
import de.hydro.gv.orgpm.data.Order;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@RunWith(Arquillian.class)
public class CustomerRelationsTest {
	
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
	private CustomerPersistanceServiceLocal persistanceService;
	
	@Test
	public void createCustomerAndOrders(){
//		Customer customer = new Customer ("Yevhen","Miroshnychenko",new Date());
//		customer.addOrder(new Order(new Date(),99.99f));
//		customer.addOrder(new Order(new Date(),999.99f));
//		customer.addOrder(new Order(new Date(),9999.99f));
//		
//		persistanceService.createCustomer(customer);
//		Collection<?> customerFound = persistanceService.executeQueryWithResults("customer.find.all");
//		Assert.assertEquals(1, customerFound.size());
//		Customer tempCustomer = (Customer) customerFound.iterator().next();
//		Assert.assertEquals(1, tempCustomer.getOrders().size());
		
	}
	@Before
	@After
	public void resetDatabaseTables() {
		persistanceService.executeQuery("order.delete.all");
		persistanceService.executeQuery("customer.delete.all");
		Assert.assertEquals(0, persistanceService.executeQueryWithResults("order.find.all").size());
		
	}

}
