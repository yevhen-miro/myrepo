package de.hydro.gv.orgpm.dao.tests;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.dao.*;
import de.hydro.gv.orgpm.data.Customer;


@RunWith(Arquillian.class)

public class CustomerBatchServiceTest {
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
private CustomerBatchServiceLocal bean;

@Test
public void testCustomerBatching() {
	bean.addCustomer(new Customer());
	bean.addCustomer(new Customer());
	bean.addCustomer(new Customer());
	Assert.assertEquals(3, bean.getAllCustomers().size());
	
}

}
