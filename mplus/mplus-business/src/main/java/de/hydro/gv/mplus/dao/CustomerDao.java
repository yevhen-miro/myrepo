package de.hydro.gv.mplus.dao;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;

@RequestScoped
public class CustomerDao {

	@PersistenceContext
	private EntityManager entityManager;

	public CustomerDao() {

	}

	public void createCustomer(Customer c) {

		this.entityManager.persist(c);
	}

	public List<Customer> readAllCustomers() {
		entityManager.flush();
		entityManager.clear();
		return this.entityManager.createQuery("FROM Customer", Customer.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CustomerParent> readAllCustomerParents() {
		entityManager.flush();
		entityManager.clear();
		return this.entityManager.createNamedQuery("customerparents.find.all").getResultList();
	}

	public CustomerParent findCustomerParentById(Long pId) {
		entityManager.flush();
		entityManager.clear();
		return (CustomerParent) this.entityManager.createNamedQuery("customerparents.find.by.id")
				.setParameter("id", pId).getSingleResult();
	}
	
	public CustomerParent findCustomerParentByCustomer(Customer pCustomer) {
		return (CustomerParent) this.entityManager.createNamedQuery("customers.find.parent.by.customer")
				.setParameter("customer", pCustomer).getSingleResult();
	}

//	public HashMap<String, BigDecimal> getTonnageByCustomer(Customer p_cus) {
//		String query = "select datename(month,Fact_Date) Month, sum(Quantity) Tonnage from MARGIN_PLUS.dbo.Fact_Actual where Customer_Id = 23186 and Fact_Date between '2015-01-01' and '2016-01-01' group by datename(month,Fact_Date)";
//		entityManager.createNativeQuery(query);
//		
//		return null;
//
//	}

	@SuppressWarnings("unchecked")
	public List<Customer> readCustomersByParent(CustomerParent pParent) {
		entityManager.flush();
		entityManager.clear();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
		Root<Customer> cs = q.from(Customer.class);
		ParameterExpression<CustomerParent> p = cb.parameter(CustomerParent.class);
		q.select(cs).where(cb.equal(cs.get("parent_id"), p));

		TypedQuery<Customer> query = entityManager.createQuery(q);
		query.setParameter(p, pParent);
		List<Customer> results = query.getResultList();
		return results;

		// return this.entityManager.createNamedQuery(
		// "customers.find.by.parent").setParameter("parent",
		// pParent).getResultList();
		// Configuration configuration = new Configuration();
		// configuration.configure("hibernate.cfg.xml");
		// StandardServiceRegistryBuilder ssrb = new
		// StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		// SessionFactory factory =
		// configuration.buildSessionFactory(ssrb.build());
		// Session session = factory.openSession();
		// String hql = "from Customer where parent = :parent";
		// Query query = session.createQuery(hql);
		// query.setParameter("parent",pParent );
		//
		// List<Customer> listCustomers = query.list();
		//
		// return listCustomers;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findCustomersByName(String pText) {
		entityManager.flush();
		entityManager.clear();
		return this.entityManager.createNamedQuery("customers.find.by.name")
				.setParameter("text", "%" + pText.toUpperCase() + "%").getResultList();
	}
	
	public Customer findCustomersByExactName(String pName) {

		return (Customer) this.entityManager.createNamedQuery("customers.find.by.exact.name")
				.setParameter("name", pName).getSingleResult();
	}

	public Customer findCustomerById(Long pId) {
		entityManager.flush();
		entityManager.clear();
		return (Customer) this.entityManager.createNamedQuery("customers.find.by.id").setParameter("id", pId)
				.getSingleResult();
	}
	
	public List<Object[]> findCustomerQuantityPerformanceByCustomer( Long pCustomer ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> tonnage = this.entityManager.createNamedQuery( "customers.performance.find.by.customer" )
				.setParameter( "customer", pCustomer ).getResultList();
		return tonnage;
	}
	
	public List<Object[]> findCustomerQuantityPerformanceByCustomerAndMonth( Long pCustomer, Integer pMonth ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> tonnage = this.entityManager.createNamedQuery( "customers.performance.find.by.customer.and.month" )
				.setParameter( "customer", pCustomer ).setParameter( "month", pMonth ).getResultList();
		return tonnage;
	}
	
	public BigDecimal findCustomerQuantityPerformanceByCustomerAndMonthAndYear( Long pCustomer, Integer pMonth, Integer pYear ) {

		BigDecimal tonnage = (BigDecimal) this.entityManager.createNamedQuery( "customers.performance.find.by.customer.and.month.and.year" )
				.setParameter( "customer", pCustomer ).setParameter( "month", pMonth ).setParameter( "year", pYear ).getSingleResult();
		return tonnage;
	}
	
	

}
