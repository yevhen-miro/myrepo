package de.hydro.gv.mplus.actions;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.services.CustomerService;

@RequestScoped
@Named
public class CustomerActions implements Serializable {

private static final long serialVersionUID = 2279216426451381465L;

private Customer actCustomer;

private CustomerParent actParent;

private String customerName;

private List<Customer> customers;
	
public List<Customer> getCustomers() {
	return customers;
}

public void setCustomers(List<Customer> customers) {
	this.customers = customers;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public Customer getActCustomer() {
	return actCustomer;
}

public void setActCustomer(Customer actCustomer) {
	this.actCustomer = actCustomer;
}

public CustomerParent getActParent() {
	return actParent;
}

public void setActParent(CustomerParent actParent) {
	this.actParent = actParent;
}

@Inject
private CustomerService customerService;

private List<Customer> cachedCustomersList;

public List<Customer> getAllCustomers() throws Exception {

	if( this.cachedCustomersList == null ) {
		this.cachedCustomersList = this.customerService.getAllCustomers();

	}
	return this.cachedCustomersList;
}

public List<CustomerParent> getAllCustomerParents () throws Exception {
	cachedCustomersList = null;
	return customerService.getAllCustomerParents();
}

public List<Customer> getCustomersByParent () throws Exception {
	List<Customer> customerList;
	customerList = customerService.getAllCustomersByParent(this.actParent);
	if (this.actParent == null) {
		cachedCustomersList = null;
		//CustomerParent cp = customerService.findCustomerParentById(0L);
		//customerList = customerService.getAllCustomersByParent(cp);
	}
	else
		customerList = customerService.getAllCustomersByParent(this.actParent);
	return customerList;
}

public void onParentChange() throws Exception {
	
	if( this.actParent.getId() != null ) {
		this.cachedCustomersList = this.getCustomersByParent();
		this.customers = cachedCustomersList;
		
	} else {
		this.cachedCustomersList = this.getCustomersByParent();
		this.customers = cachedCustomersList;
	}

}

public void onCustomerChanged() throws Exception {	
	if( this.actParent.getId() != null ) {
		this.cachedCustomersList = this.getCustomersByParent();
		
	} else {
		this.cachedCustomersList = this.getCustomersByParent();
	}
}


public List<Customer> findCustomersByName () throws Exception {
	String text = this.customerName == null ? "": this.customerName;
	customers = customerService.findCustomersByName(text.toUpperCase());
	return customers;
}

public String clickOk() {
	
	System.out.println(this.actParent);
	return "contracts.xhtml";
}


public void init() throws Exception {
	List<Customer> customerList;
	customerList = customerService.getAllCustomersByParent(this.actParent);
	if (this.actParent == null) {
		cachedCustomersList = null;
		//CustomerParent cp = customerService.findCustomerParentById(0L);
		//customerList = customerService.getAllCustomersByParent(cp);
	}
	else
		customerList = customerService.getAllCustomersByParent(this.actParent);
}

public void resetDialog () {
	this.actCustomer = null;
	this.actParent = null;
	this.cachedCustomersList = null;
	this.customerName = null;
	this.customers = null;
}

}
