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

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;

@RequestScoped
public class PlantDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public PlantDao() {
	
}

public void createPlant( Plant p ) {

	this.entityManager.persist( p );
}

public void updatePlant( Plant p ) {

	this.entityManager.merge( p );
}

public Plant findPlantById (Long pId) {
	return entityManager.find(Plant.class, pId);
}

public List<Plant> readAllPlants() {
	
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	 
	  CriteriaQuery<Plant> q = cb.createQuery(Plant.class);
	  Root<Plant> p = q.from(Plant.class);
	  q.orderBy(cb.desc(p.get("PlantName")));
	  
	  TypedQuery<Plant> query = entityManager.createQuery(q);
	    List<Plant> results = query.getResultList();
	    return results;
	//return this.entityManager.createQuery( "SELECT c FROM Contract c ORDER by c.id DESC", Contract.class ).getResultList();
}


}
