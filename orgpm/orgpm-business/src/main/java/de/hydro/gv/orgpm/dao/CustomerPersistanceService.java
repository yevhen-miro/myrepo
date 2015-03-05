package de.hydro.gv.orgpm.dao;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;




import de.hydro.gv.orgpm.data.Customer;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@Stateless //session Bean, name = class name. If it implements remote interface, it becomes accessible for remote access over network. You can define accessible methods in the interfaces.
@Interceptors(PerformanceInterceptor.class)
//@Pool("slsb-strict-max-pool")
public class CustomerPersistanceService implements CustomerPersistanceServiceRemote, CustomerPersistanceServiceLocal { // F3 on the interface will create interface
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;
	
	@Resource
	private SessionContext sessionContext;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostConstruct
	public void postConstruct(){
		logService.logMessage("bean was created via PostConstruct"); // @PostConstruct creates a bean
	};
	
	@PreDestroy
	public void preDestroy(){
		logService.logMessage("bean was removed via preDestroy"); // kills the bean
	}
	
	
	public void createCustomer( Customer c) {	
		logService.logMessage("CustomerPersistanceService.createCustomer()");
		entityManager.persist(c);//systr Strg+space
	}
	
	public List <Customer> readAllCustomers(){
		logService.logMessage("CustomerPersistanceService.readAllCustomers()"); 
		return entityManager.createQuery("FROM CustomerEntity",Customer.class).getResultList();//FROM name of the entity, that is declared using name attribute
		
	}
	
	public void updateCustomer (Customer c){
		logService.logMessage("CustomerPersistanceService.updateCustomer()");
		entityManager.merge(c);
		}
	
	public void deleteCustomer (Customer c) {
		logService.logMessage("CustomerPersistanceService.deleteCustomer()");
		entityManager.remove(entityManager.merge(c));
	}

	public void flushCUstomers() {
		// TODO Auto-generated method stub
		
	}

	public void removeAllCustomers(){
		entityManager.createQuery("DELETE FROM CustomerEntity").executeUpdate();//FROM name of the entity, that is declared using name attribute
		
	}
	
	public void executeQuery(String queryName){
		entityManager.createNamedQuery(queryName).executeUpdate();
	}
	
	public List<?> executeQueryWithResults (String queryName){
		return entityManager.createNamedQuery(queryName).getResultList();
	}
	
	
	
}

