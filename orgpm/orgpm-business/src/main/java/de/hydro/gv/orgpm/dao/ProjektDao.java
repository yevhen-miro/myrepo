package de.hydro.gv.orgpm.dao;

import java.util.Collection;
import java.util.List;

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

	@PersistenceContext
	private EntityManager entityManager;

	public void createProjekt( Projekt p ) {
		this.entityManager.clear();
		this.entityManager.persist( p );
	}

	public List<Projekt> readAlleProjekte() {
		return this.entityManager.createNamedQuery( "projekt.alle", Projekt.class ).getResultList();
	}

	public List<Projekt> readAlleAktiveProjekte() {
		return this.entityManager.createNamedQuery( "projekt.alle.active", Projekt.class ).getResultList();
	}

	public void updateProjekt( Projekt p ) {

		this.entityManager.merge( p );
		this.entityManager.flush();

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

	public Projekt getProjektById( Long id ) {
		return this.entityManager.getReference( Projekt.class, id );

	}

	public Collection<Projekt> getProjektByMitarbeiterId( String hydroid ) {
		return this.entityManager.createNamedQuery( "projekt.find.by.mitarbeiter", Projekt.class )
				.setParameter( "hydroid", hydroid ).getResultList();
	}

	public List<Projekt> getAlleZugelasseneProjekte( String hydroid ) {
		return this.entityManager.createNamedQuery( "projekt.find.ebabled.projects.by.mitarbeiter", Projekt.class )
				.setParameter( "hydroid", hydroid ).getResultList();
	}

}