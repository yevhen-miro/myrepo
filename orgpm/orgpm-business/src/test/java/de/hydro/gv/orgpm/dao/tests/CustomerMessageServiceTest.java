package de.hydro.gv.orgpm.dao.tests;


import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.data.Customer;
import de.hydro.gv.orgpm.dao.CustomerPersistanceService;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@RunWith(Arquillian.class)
public class CustomerMessageServiceTest {
	
	@Deployment (testable=false)
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
	
	@Test
	public void testMessageDrivenService() throws NamingException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory)
				context.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue)
				context.lookup("queues/testQueue");
		
		JMSContext jmsContext = connectionFactory.createContext();
			jmsContext.createProducer().send(queue, jmsContext.createObjectMessage(new Customer()));
		
	}

}
