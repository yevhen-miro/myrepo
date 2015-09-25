package de.hydro.gv.orgpm.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Projekt;

@Stateless
public class ProjektDao {

	@Inject
	@ImplByConsole
	// CDI Annotation
	private LogService logService;

	private SessionContext sessionContext;

	@PersistenceContext
	private EntityManager entityManager;

	// @PostConstruct
	// public void postConstruct() {
	// this.logService.logMessage( "bean was created via PostConstruct" ); //
	// @PostConstruct
	// // creates
	// // a
	// // bean
	// };
	//
	// @PreDestroy
	// public void preDestroy() {
	// this.logService.logMessage( "bean was removed via preDestroy" ); // kills
	// // the
	// // bean
	// }
	//
	// public ProjektDao() {
	//
	// }

	public void createProjekt( Projekt p ) {
		this.entityManager.persist( p );
	}

	public List<Projekt> readAlleProjekte() {
		return this.entityManager.createNamedQuery( "projekt.alle", Projekt.class ).getResultList();
	}

	public void updateProjekt( Projekt p ) {
		this.entityManager.merge( p );

	}

	public void deleteProjekt( Projekt p ) {
		this.entityManager.remove( this.entityManager.merge( p ) );
	}

	public void executeQuery( String queryName ) {
		this.entityManager.createNamedQuery( queryName ).executeUpdate();
	}

	public List<?> executeQueryWithResults( String queryName ) {
		return this.entityManager.createNamedQuery( queryName ).getResultList();
	}

	public void getProjektById( Long id ) {
		this.entityManager.getReference( Projekt.class, id );

	}

	public Collection<Projekt> getProjektByMitarbeiterId( String hydroid ) {
		return this.entityManager.createNamedQuery( "projekt.find.by.mitarbeiter", Projekt.class )
				.setParameter( "hydroid", hydroid ).getResultList();
	}

}