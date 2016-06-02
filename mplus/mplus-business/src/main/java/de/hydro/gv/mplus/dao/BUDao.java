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
public class BUDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public BUDao() {
	
}

public void createBU( BU b ) {

	this.entityManager.persist( b );
}

public void updateBU( BU p ) {

	this.entityManager.merge( p );
}

public List<BU> readAllBUs() {	
return this.entityManager.createQuery( "SELECT b FROM BU b ORDER by b.name", BU.class ).getResultList();
}

public BU findByCBU(CBU pCBU) {	
return (BU) this.entityManager.createNamedQuery("bu.find.by.cbu").setParameter("cbu", pCBU).getSingleResult();
}
}
