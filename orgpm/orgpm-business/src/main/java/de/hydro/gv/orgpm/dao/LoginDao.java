package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.hydro.gv.orgpm.auth.Login;

@RequestScoped
@Transactional
public class LoginDao {

	@PersistenceContext
	private EntityManager entityManager;

	public LoginDao() {

	}

	public void createLogin( Login l ) {

		this.entityManager.persist( l );
	}

	public List<Login> readAlleLogins() {
		return this.entityManager.createQuery( "FROM Login", Login.class ).getResultList();
	}

	public void updateLogin( Login l ) {
		this.entityManager.merge( l );

	}

	public void deleteLogin( Login l ) {

		this.entityManager.remove( this.entityManager.merge( l ) );
	}

}
