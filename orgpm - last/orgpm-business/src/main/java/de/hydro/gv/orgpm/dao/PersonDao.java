package de.hydro.gv.orgpm.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.auth.Person;

@RequestScoped
public class PersonDao {

	@PersistenceContext
	EntityManager em;

	// @Inject
	LogService logger;

	public Person getEntityByName( String _name ) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person getPersonByHydroId( String _hydroId ) throws Exception {
		try {
			return (Person) this.em.createQuery( "SELECT p FROM Person p WHERE UPPER(p.hydroid) = UPPER(:hydroid) " )
					.setParameter( "hydroid", _hydroId ).getSingleResult();
		} catch ( NonUniqueResultException e ) {
			this.logger.logMessage( "PersonDao: " + _hydroId + " :  mehr als ein Mitarbeiter mit dieser HydroID" );
			throw new Exception( "PersonDao: " + _hydroId + " :  mehr als ein Mitarbeiter mit dieser HydroID" );
		} catch ( NoResultException e ) {
			this.logger.logMessage( "PersonDao: " + _hydroId + " :  Mitarbeiter mit dieser HydroID unbekannt" );
			throw new Exception( "PersonDao: " + _hydroId + " :  Mitarbeiter mit dieser HydroID unbekannt" );
		}
	}
}
