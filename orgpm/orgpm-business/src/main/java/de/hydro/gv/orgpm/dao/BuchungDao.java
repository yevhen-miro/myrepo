package de.hydro.gv.orgpm.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Buchung;

@RequestScoped
public class BuchungDao {

	@Inject
	@ImplByConsole
	// CDI Annotation
	private LogService logService;

	private SessionContext sessionContext;

	@PersistenceContext
	private EntityManager em;

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

	public void createBuchung( Buchung b ) {
		this.em.clear();
		this.em.persist( b );

	}

	public void updateBuchung( Buchung b ) {
		this.em.merge( b );

	}

	public void deleteBuchung( Buchung b ) {
		this.em.remove( this.em.merge( b ) );

	}

	public List<Buchung> readAllBuchungen() {
		return this.em.createQuery( "SELECT b FROM Buchung b", Buchung.class ).getResultList();
	}

	public void removeAlleBuchungen() {
		this.em.createQuery( "DELETE FROM Buchungen" ).executeUpdate();

	}

	public List<?> executeQueryWithResults( String queryName ) {
		return this.em.createNamedQuery( queryName ).getResultList();
	}

	public void executeQuery( String queryName ) {
		this.em.createNamedQuery( queryName ).executeUpdate();

	}

	@SuppressWarnings( "unchecked" )
	public List<Buchung> getBuchungByMitarbeiter( String _hydroid, Date _datum ) {

		return this.em.createNamedQuery( "buchung.find.buchung.by.mitarbeiter" ).setParameter( "hydroid", _hydroid )
				.setParameter( "datum", _datum ).getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public HashMap<String, Long> getDauerByMitarbeiter( String _hydroid ) {
		// create empty map to store results in. If we don't find results, an
		// empty hashmap will be returned
		HashMap<String, Long> results = new HashMap<String, Long>();

		// Construct and run query
		List<Object[]> resultList = this.em.createNamedQuery( "buchung.find.duration.by.mitarbeiter" )
				.setParameter( "hydroid", _hydroid ).getResultList();

		// Place results in map
		for ( Object[] borderTypes : resultList ) {
			results.put( (String) borderTypes[0], (Long) borderTypes[1] );
		}

		return results;
	}

	public Buchung getBuchungById( Long _id ) {
		return this.em.find( Buchung.class, _id );
	}

}
