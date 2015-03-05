package de.hydro.gv.orgpm.dao;

import java.util.List;

import de.hydro.gv.orgpm.data.Customer;

public interface CustomerBatchServiceLocal {
	
	void addCustomer(Customer customer);

	List<Customer> getAllCustomers();

	void removeBeanInstance();


}
