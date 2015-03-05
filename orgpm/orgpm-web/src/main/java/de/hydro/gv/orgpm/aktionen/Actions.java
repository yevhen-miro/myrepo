package de.hydro.gv.orgpm.aktionen;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.dao.CustomerPersistanceServiceLocal;
import de.hydro.gv.orgpm.dao.ImplByConsole;
import de.hydro.gv.orgpm.dao.LogService;
import de.hydro.gv.orgpm.dao.LogServiceByConsole;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.dao.MitarbeiterDaoLocal;
import de.hydro.gv.orgpm.model.Mitarbeiter;
import de.hydro.gv.orgpm.model.CreditCard;
import de.hydro.gv.orgpm.model.Customer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

//have to be CDI
//@ConversationScoped
// Conversation scope for the conversation similar to request scope
@RequestScoped
@Named
public class Actions implements Serializable {
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;

	@Inject
	private Conversation conversation; // Interface from JEE Context and have to
										// be injected

	private Customer customer = new Customer();
	private Mitarbeiter mitarbeiter = new Mitarbeiter();

	@Inject
	private CustomerPersistanceServiceLocal persistanceService;
	
	@Inject
	private MitarbeiterDaoLocal mitarbeiterDao;
	

	private ArrayList<Customer> cachedCustomerList;
	private ArrayList<Mitarbeiter> cachedMitarbeiterList;

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
	public String saveMitarbeiter() {
		mitarbeiterDao.createMitarbeiter(convertToEntity(mitarbeiter));
		return "mitarbeiter-overview.xhtml";
	}

	private de.hydro.gv.orgpm.data.Mitarbeiter convertToEntity(
			Mitarbeiter mitToConvert) {
		de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiterEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
		mitarbeiterEntity.setVorname(mitToConvert.getVorname());
		mitarbeiterEntity.setName(mitToConvert.getName());
		mitarbeiterEntity.setArbeitszeit(mitToConvert.getArbeitszeit());
		mitarbeiterEntity.setBemerkung(mitToConvert.getBemerkung());
		mitarbeiterEntity.setgruppe(mitToConvert.getGruppe());
		mitarbeiterEntity.setHydroId(mitToConvert.getHydroid());
		mitarbeiterEntity.setKartenNum(mitToConvert.getKartenid());
		mitarbeiterEntity.setMitarbeiterkennung(mitToConvert.getKennung());
		mitarbeiterEntity.setMitarbeiterStatus(mitToConvert.getStatus());
		mitarbeiterEntity.setPersonalNum(mitToConvert.getPersonalid());
		return mitarbeiterEntity;
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
	public String removeMitarbeiter() { // have to be of String type, without
		// parameters and return result
		de.hydro.gv.orgpm.data.Mitarbeiter tempEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
		tempEntity.setId(mitarbeiter.getId());
		mitarbeiterDao.deleteMitarbeiter(tempEntity);
		cachedMitarbeiterList = null;
		logService.logMessage("remove mitarbeiter message");
		return null;
	}

	public String updateCustomer() { // have to be of String type, without
										// parameters and return result
		System.out.println("Actions.updateCustomers()");
		return null;
	}
	public String updateMitarbeiter() {
		//de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter tempMitarbeiter = new de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter();
		//tempMitarbeiter.setId(mitarbeiter.getId());
		//tempMitarbeiter.setVorname(mitarbeiter.getVorname());
		mitarbeiterDao.updateMitarbeiter(convertToEntity(mitarbeiter));
		cachedMitarbeiterList = null;
		return null;
	}

	public List<Customer> getAllCustomers() {
		if (cachedCustomerList == null)
			cachedCustomerList = readAndConvertCustomers();
		return cachedCustomerList;
	}
	public List<Mitarbeiter> getAllMitarbeiter() {
		if (cachedMitarbeiterList == null)
			cachedMitarbeiterList = readAndConvertMitarbeiter();
		return cachedMitarbeiterList;
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
	private ArrayList<Mitarbeiter> readAndConvertMitarbeiter() {
		@SuppressWarnings("unchecked")
		List<de.hydro.gv.orgpm.data.Mitarbeiter> results = (List<de.hydro.gv.orgpm.data.Mitarbeiter>) mitarbeiterDao
				.executeQueryWithResults("mitarbeiter.find.all");
		ArrayList<Mitarbeiter> arrayList = new ArrayList<Mitarbeiter>();
		for (de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter : results)
			arrayList.add(convToModel(mitarbeiter));
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

	private Mitarbeiter convToModel(
			de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter) {
		Mitarbeiter mitarbeiterModel = new Mitarbeiter();
		mitarbeiterModel.setId(mitarbeiter.getId());
		mitarbeiterModel.setName(mitarbeiter.getName());
		mitarbeiterModel.setVorname(mitarbeiter.getVorname());
		mitarbeiterModel.setArbeitszeit(mitarbeiter.getArbeitszeit());
		mitarbeiterModel.setBemerkung(mitarbeiter.getBemerkung());
		mitarbeiterModel.setGruppe(mitarbeiter.getgruppe());
		mitarbeiterModel.setHydroid(mitarbeiter.getHydroId());
		mitarbeiterModel.setKartenid(mitarbeiter.getKartenNum());
		mitarbeiterModel.setKennung(mitarbeiter.getMitarbeiterkennung());
		mitarbeiterModel.setStatus(mitarbeiter.getMitarbeiterStatus());
		mitarbeiterModel.setPersonalid(mitarbeiter.getPersonalNum());

		return mitarbeiterModel;
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

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
    public void MitarbeiterOnRowEdit(RowEditEvent event) {
    	updateMitarbeiter();
        FacesMessage msg = new FacesMessage("Mitarbeiter geändert", ((Mitarbeiter) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void MitarbeiterOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Ã„nderung abgebrochen", ((Mitarbeiter) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void CustomerOnRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Customer geändert", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void CustomerOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

	
	
	public String addNewMitarbeiter() {
		return "mitarbeiter-input.xhtml";
	}

}
