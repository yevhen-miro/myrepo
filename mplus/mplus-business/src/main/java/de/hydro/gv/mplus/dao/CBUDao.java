package de.hydro.gv.mplus.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.SystemUser;

@RequestScoped
public class CBUDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public CBUDao() {
	
}

public void createBU( CBU c ) {

	this.entityManager.persist( c );
}

public void updateBU( CBU c ) {

	this.entityManager.merge( c );
}

public List<CBU> readAllCBUs() {	
return this.entityManager.createQuery( "SELECT c FROM CBU c ORDER by c.name", CBU.class ).getResultList();
}

@SuppressWarnings("unchecked")
public List<CBU> findCBUsByBU(BU pBU) {	
	return this.entityManager.createNamedQuery( "cbu.find.by.bu").setParameter("bu", pBU).getResultList();
}
}
