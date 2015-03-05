package de.hydro.gv.orgpm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

import de.hydro.gv.orgpm.data.Customer;

@Stateful
public class CustomerBatchService implements CustomerBatchServiceLocal{
	
	
	private LogService logService;
	
	@Inject
	private CustomerPersistanceServiceLocal bean;
	private final ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
		
	}
	
	public List<Customer> getAllCustomers(){
		return customers;
	}
	
	public void flushCustomers() {
		for(Customer customer : customers)
			bean.createCustomer(customer);
		customers.clear();
	}
	
	@Remove // to take the bean state away
	public void removeBeanInstance() {
		logService.logMessage("Bean wurde entfernt");
	}


}
