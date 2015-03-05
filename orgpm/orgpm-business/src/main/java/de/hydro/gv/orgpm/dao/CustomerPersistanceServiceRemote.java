package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.ejb.Remote;

import de.hydro.gv.orgpm.data.Customer;

@Remote //implements methods for remote access
public interface CustomerPersistanceServiceRemote {

	public abstract void createCustomer(Customer c);

	public abstract List<Customer> readAllCustomers();

	public abstract void updateCustomer(Customer c);

	public abstract void deleteCustomer(Customer c);

}