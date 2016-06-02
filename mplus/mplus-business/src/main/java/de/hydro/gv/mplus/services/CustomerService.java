package de.hydro.gv.mplus.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.CustomerDao;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;

@Stateless
public class CustomerService {
	
@Inject
private CustomerDao customersDao;	

public List<Customer> getAllCustomers() throws Exception {
	return this.customersDao.readAllCustomers();
}

public List<CustomerParent> getAllCustomerParents() throws Exception {
	return this.customersDao.readAllCustomerParents();
}

public List<Customer> getAllCustomersByParent(CustomerParent pParent) throws Exception {
	return this.customersDao.readCustomersByParent(pParent);
}

public List<Customer> findCustomersByName(String pName) throws Exception {
	return this.customersDao.findCustomersByName(pName);
}

public Customer findCustomersByExactName(String pName) throws Exception {
	return this.customersDao.findCustomersByExactName(pName);
}


public CustomerParent findCustomerParentById(Long pId) throws Exception {
	return this.customersDao.findCustomerParentById(pId);
}

public Customer findCustomerById(Long pId) {
	return this.customersDao.findCustomerById(pId);
}

public CustomerParent findCustomerParentByCustomer(Customer pCustomer)  {
return this.customersDao.findCustomerParentByCustomer(pCustomer);
}

public List<Object[]> findCustomerTonnageByCustomer(Long pCustomer) {
	return this.customersDao.findCustomerQuantityPerformanceByCustomer(pCustomer);
}

public List<Object[]> findCustomerTonnageByCustomerAndMonth(Long pCustomer, Integer pMonth) {
	return this.customersDao.findCustomerQuantityPerformanceByCustomerAndMonth(pCustomer, pMonth);
}

public BigDecimal findCustomerTonnageByCustomerAndMonthAndYear(Long pCustomer, Integer pMonth, Integer pYear) {
	return this.customersDao.findCustomerQuantityPerformanceByCustomerAndMonthAndYear(pCustomer, pMonth, pYear);
}
}
