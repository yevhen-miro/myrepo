package de.schellsoft.seminars.ee7.ejb.tests;


import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.schellsoft.seminars.ee7.custimercare.ejb.Customer;
import de.schellsoft.seminars.ee7.custimercare.ejb.CustomerPersistanceService;
import de.schellsoft.seminars.ee7.custimercare.ejb.interceptors.PerformanceInterceptor;

@RunWith(Arquillian.class)
public class CustomerMessageServiceTest {
	
	@Deployment (testable=false)
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addPackage(CustomerPersistanceService.class.getPackage())
				.addPackage(PerformanceInterceptor.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml");
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
