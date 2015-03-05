package de.hydro.gv.orgpm.dao;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import de.hydro.gv.orgpm.data.Customer;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/queues/testQueue") })
public class CustomerMessageService implements MessageListener { // implements
																	// interface
																	// to react
																	// to JMS
																	// messages
	@Inject
	private CustomerPersistanceServiceLocal persistenceService;

	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;

		try {
			Customer customer = (Customer) objectMessage.getObject();
			persistenceService.createCustomer(customer);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
