package de.schellsoft.seminars.ee7.ejb.tests;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.schellsoft.seminars.ee7.custimercare.ejb.*;


@RunWith(Arquillian.class)

public class CustomerBatchServiceTest {
@Deployment
public static JavaArchive createDeployment() {
	return ShrinkWrap.create(JavaArchive.class,"test.jar").addPackage(CustomerPersistanceService.class.getPackage());
}

@Inject
private CustomerBatchServiceLocal bean;

@Test
public void testCustomerBatching() {
	bean.addCustomer(new Customer());
	bean.addCustomer(new Customer());
	bean.addCustomer(new Customer());
	Assert.assertEquals(3, bean.getAllCustomers().size());
	
}

}
