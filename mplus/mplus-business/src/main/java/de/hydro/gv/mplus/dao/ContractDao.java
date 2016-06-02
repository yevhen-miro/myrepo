package de.hydro.gv.mplus.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.ClauseType;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractActivity;
import de.hydro.gv.mplus.data.ContractApprover;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.ContractPlant;
import de.hydro.gv.mplus.data.Currency;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.data.Incoterms;
import de.hydro.gv.mplus.data.PayTerm;
import de.hydro.gv.mplus.data.PayTermId;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;

@RequestScoped
public class ContractDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public ContractDao() {
	
}

public void createContract( Contract c ) {

	this.entityManager.persist( c );
}

public void updateContract( Contract c ) {

	this.entityManager.merge( c );
}

public List<Contract> readAllContracts() {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	 
	  CriteriaQuery<Contract> q = cb.createQuery(Contract.class);
	  Root<Contract> c = q.from(Contract.class);
	  q.orderBy(cb.desc(c.get("id")));
	  
	  TypedQuery<Contract> query = entityManager.createQuery(q);
	    List<Contract> results = query.getResultList();
	    return results;
	//return this.entityManager.createQuery( "SELECT c FROM Contract c ORDER by c.id DESC", Contract.class ).getResultList();
}

@SuppressWarnings("unchecked")
public Contract readAllContractsById(Long pId) {
	return (Contract) this.entityManager.createNamedQuery( "contracts.find.by.id" ).setParameter( "id", pId ).getSingleResult();
}

@SuppressWarnings("unchecked")
public List<Contract> readAllContractsByCustomer(Customer pCustomer) {
	return this.entityManager.createNamedQuery( "contracts.find.by.customer" ).setParameter( "customer", pCustomer ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> readAllContractsByPlant(Plant pPlant) {
return this.entityManager.createNamedQuery( "contracts.find.by.plant" ).setParameter( "plant", pPlant ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> findAllContractsBySeller(SystemUser pSeller) {
	return this.entityManager.createNamedQuery( "contracts.find.by.seller" ).setParameter( "seller", pSeller ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> readAllContractsByStatus(Integer pStatus) {
	return this.entityManager.createNamedQuery( "contracts.find.by.status" ).setParameter( "status", pStatus ).getResultList();
}

@SuppressWarnings("unchecked")
public List<ContractItem> readContractItemsByContract(Contract pContract) {
	return this.entityManager.createNamedQuery( "contractitems.find.by.contract" ).setParameter( "contract", pContract ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> findContractsByCBU(CBU pCBU) {
	return this.entityManager.createNamedQuery( "contracts.find.by.cbu" ).setParameter( "cbu", pCBU ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> findContractsByBU(BU pBU) {
	return this.entityManager.createNamedQuery( "contracts.find.by.bu" ).setParameter( "bu", pBU ).getResultList();
}

@SuppressWarnings("unchecked")
public List<Contract> findContractsByBUAndCBU(BU pBU, CBU pCBU) {
	return this.entityManager.createNamedQuery( "contracts.find.by.bu.and.cbu" ).setParameter( "bu", pBU ).setParameter("cbu", pCBU).getResultList();
}

public List<ContractItem> readAllContractItems() {
	return this.entityManager.createQuery( "SELECT c FROM ContractItem c ORDER by c.id DESC", ContractItem.class ).getResultList();
}

public Incoterms findIncotermById(String pId) {
	return this.entityManager.find(Incoterms.class, pId);
}

public Currency findCurrencyById(String pId) {
	return this.entityManager.find(Currency.class, pId);
}

public PayTerm findPayTermById(PayTermId pId) {
	return this.entityManager.find(PayTerm.class, pId);
}

public List<ClauseType> findAllClauses() {
	return entityManager.createQuery("SELECT cl from ClauseType cl ORDER by cl.category", ClauseType.class).getResultList();
}

@SuppressWarnings("unchecked")
public List<ContractApprover> findContractApproversByContract(Contract pContract) {
	return this.entityManager.createNamedQuery( "contract.approvers.find.by.contract" ).setParameter( "contract", pContract ).getResultList();
}

@SuppressWarnings("unchecked")
public List<ContractActivity> findContractActivitiesByContract(Contract pContract) {
	return this.entityManager.createNamedQuery( "contract.activities.find.by.contract" ).setParameter( "contract", pContract).getResultList();
}

@SuppressWarnings("unchecked")
public List<ContractActivity> findAllContractActivities () {
	return this.entityManager.createNamedQuery("contract.activities.find.all").getResultList();
}

}
