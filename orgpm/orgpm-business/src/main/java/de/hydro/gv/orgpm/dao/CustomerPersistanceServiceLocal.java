package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import de.hydro.gv.orgpm.data.Customer;

@Stateless
@Local // local interface for local access 
public interface CustomerPersistanceServiceLocal {

	public abstract void createCustomer(Customer c);

	public abstract List<Customer> readAllCustomers();

	public abstract void updateCustomer(Customer c);

	public abstract void deleteCustomer(Customer c);
	
	public void flushCUstomers();
	
	public void preDestroy();
	
	public void postConstruct();

	public abstract void removeAllCustomers();
	
	public List<?> executeQueryWithResults (String queryName);
	
	public void executeQuery(String queryName);



}