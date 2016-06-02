package de.hydro.gv.mplus.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemRole;
import de.hydro.gv.mplus.data.SystemUser;

@RequestScoped
public class SystemUserDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public SystemUserDao() {
	
}

public void createUser( SystemUser u ) {

	this.entityManager.persist( u );
}

public void updateUser( SystemUser u ) {

	this.entityManager.merge( u );
}

public SystemUser findUserById (String pId) {
	//return entityManager.find(SystemUser.class, pId);
	return (SystemUser) this.entityManager.createNamedQuery("users.find.by.id").setParameter("id", pId).getSingleResult();
}

public SystemUser findUserByHydroId (String pHydroId) {
	//return entityManager.find(SystemUser.class, pId);
	return (SystemUser) this.entityManager.createNamedQuery("users.find.by.hydroid").setParameter("hydroid", pHydroId).getSingleResult();
}


public List<SystemUser> findAllUsers () {
	return this.entityManager.createQuery( "SELECT u FROM SystemUser u WHERE u.isEnabled = true ORDER by u.fullName", SystemUser.class ).getResultList();
}

@SuppressWarnings("unchecked")
public List<String> findAllUsersNames () {
	return this.entityManager.createNamedQuery("users.find.all.usernames").getResultList();
}

public SystemRole findRoleByHydroId (String pHydroId) {
	return (SystemRole) this.entityManager.createNamedQuery("roles.find.role.by.hydroid").setParameter("hydroid", pHydroId).getSingleResult();
}


}
