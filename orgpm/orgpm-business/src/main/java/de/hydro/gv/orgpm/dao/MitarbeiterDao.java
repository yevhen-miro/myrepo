package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Mitarbeiter;

@RequestScoped
public class MitarbeiterDao {

	@Inject
	@ImplByConsole
	// CDI Annotation
	private LogService logService;

	private SessionContext sessionContext;

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void postConstruct() {
		this.logService.logMessage( "bean was created via PostConstruct" ); // @PostConstruct
																			// creates
																			// a
																			// bean
	};

	@PreDestroy
	public void preDestroy() {
		this.logService.logMessage( "bean was removed via preDestroy" ); // kills
																			// the
																			// bean
	}

	public MitarbeiterDao() {

	}

	public void createMitarbeiter( Mitarbeiter m ) {
		this.entityManager.persist( m );
	}

	public List<Mitarbeiter> readAllMitarbeiter() {
		return this.entityManager.createQuery( "FROM Mitarbeiter", Mitarbeiter.class ).getResultList();
	}

	public void updateMitarbeiter( Mitarbeiter m ) {
		this.entityManager.merge( m );

	}

	public void deleteMitarbeiter( Mitarbeiter m ) {
		this.entityManager.remove( this.entityManager.merge( m ) );
	}

	public void executeQuery( String queryName ) {
		this.entityManager.createNamedQuery( queryName ).executeUpdate();
	}

	public List<?> executeQueryWithResults( String queryName ) {
		return this.entityManager.createNamedQuery( queryName ).getResultList();
	}

	public void removeAllMitarbeiter() {
		this.entityManager.createQuery( "DELETE FROM MitarbeiterEntity" ).executeUpdate();

	}

	public void getMitarbeiterById( Long id ) {
		this.entityManager.getReference( Mitarbeiter.class, id );

	}

	public Mitarbeiter getMitarbeiterByName( String _name ) {
		return (Mitarbeiter) this.entityManager.createNamedQuery( "mitarbeiter.find.by.lastName" )
				.setParameter( "vorname", _name ).getSingleResult();
	}

}