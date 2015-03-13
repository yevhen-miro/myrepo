package de.hydro.gv.orgpm.actions;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.dao.BuchungDaoLocal;
import de.hydro.gv.orgpm.dao.CustomerPersistanceServiceLocal;
import de.hydro.gv.orgpm.dao.ImplByConsole;
import de.hydro.gv.orgpm.dao.LogService;
import de.hydro.gv.orgpm.dao.LogServiceByConsole;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.dao.MitarbeiterDaoLocal;
import de.hydro.gv.orgpm.models.Buchung;
import de.hydro.gv.orgpm.models.CreditCard;
import de.hydro.gv.orgpm.models.Customer;
import de.hydro.gv.orgpm.models.Mitarbeiter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

//have to be CDI
//@ConversationScoped
// Conversation scope for the conversation similar to request scope
@RequestScoped
@Named
public class Actions implements Serializable {
	
/*	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;*/

	@Inject
	private Conversation conversation; // Interface from JEE Context and have to
										// be injected

	private Customer customer = new Customer();

	@Inject
	private CustomerPersistanceServiceLocal persistanceService;
	

	private ArrayList<Customer> cachedCustomerList;

	public String saveCustomer() { // have to be of String type, without
									// parameters and return result
		if (!conversation.isTransient()) {
			persistanceService.createCustomer(convertToEntity(customer));
			conversation.end();
		}
		// conversation.end();// finishes the conversation of an opened
		// conversation
		persistanceService.createCustomer(convertToEntity(customer));
		// cachedCustomerList = null;
		return "customer-overview.xhtml"; // return page, if null remains on the
											// input page
	}


	private de.hydro.gv.orgpm.data.Customer convertToEntity(
			Customer custToConvert) {
		de.hydro.gv.orgpm.data.Customer customerEntity = new de.hydro.gv.orgpm.data.Customer();
		customerEntity.setFirstName(custToConvert.getFirstName());
		customerEntity.setLastName(custToConvert.getLastName());
		customerEntity.setDateOfBirth(custToConvert.getDateOfBirth());
		return customerEntity;
	}
	

	public String removeCustomer() { // have to be of String type, without
										// parameters and return result
		de.hydro.gv.orgpm.data.Customer tempEntity = new de.hydro.gv.orgpm.data.Customer();
		tempEntity.setId(customer.getId());
		persistanceService.deleteCustomer(tempEntity);
		cachedCustomerList = null;
		return null;
	}


	public String updateCustomer() { // have to be of String type, without
										// parameters and return result
		System.out.println("Actions.updateCustomers()");
		return null;
	}


	public List<Customer> getAllCustomers() {
		if (cachedCustomerList == null)
			cachedCustomerList = readAndConvertCustomers();
		return cachedCustomerList;
	}

	private ArrayList<Customer> readAndConvertCustomers() {
		@SuppressWarnings("unchecked")
		List<de.hydro.gv.orgpm.data.Customer> results = (List<de.hydro.gv.orgpm.data.Customer>) persistanceService
				.executeQueryWithResults("customer.find.all");
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		for (de.hydro.gv.orgpm.data.Customer customer : results)
			arrayList.add(convertToModel(customer));
		return arrayList;
	}

	private Customer convertToModel(
			de.hydro.gv.orgpm.data.Customer customer) {
		Customer customerModel = new Customer();
		customerModel.setId(customer.getId());
		customerModel.setFirstName(customer.getFirstName());
		customerModel.setLastName(customer.getLastName());
		customerModel.setDateOfBirth(customer.getDateOfBirth());
		return customerModel;
	}

	public List<CreditCard> getAllCreditCards() { // this method will be used in
													// JSF without "get"
													// prefix!!!Default!
		ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
		creditCards.add(new CreditCard(1, "Visa Card", "VISA Inc."));
		creditCards.add(new CreditCard(2, "Master Card", "Mastercard Inc."));
		creditCards.add(new CreditCard(3, "American Express Card",
				"American Express Inc."));
		return creditCards;
	}

	public String startConversation() { // Active methods must be of String type
										// and without parameters
		if (conversation.isTransient()) {
			conversation.begin();
		}
		; // begins your conversation
		return "customer-input-step2.xhtml";
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
    public void CustomerOnRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Customer geändert", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void CustomerOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
