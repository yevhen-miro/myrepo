package de.hydro.gv.orgpm.dao;

import java.util.Date;
import java.util.List;

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

	// private SessionContext sessionContext;

	@PersistenceContext
	private EntityManager em;

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

	@SuppressWarnings( "unchecked" )
	public List<Object> readAlleBuchungenByMonth( Integer month, Integer year ) {
		return this.em.createNamedQuery( "buchung.find.all.by.month" ).setParameter( "month", month )
				.setParameter( "year", year ).getResultList();
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

	// @SuppressWarnings( "unchecked" )
	// public HashMap<String, Long> getDauerByMitarbeiter( String _hydroid ) {
	// // create empty map to store results in. If we don't find results, an
	// // empty hashmap will be returned
	// HashMap<String, Long> results = new HashMap<String, Long>();
	//
	// // Construct and run query
	// List<Object[]> buchungen = this.em.createNamedQuery(
	// "buchung.find.duration.by.mitarbeiter" )
	// .setParameter( "hydroid", _hydroid ).getResultList();
	//
	// // Place results in map
	// for ( Object[] projekt : buchungen ) {
	// results.put( (String) projekt[0], (Long) projekt[1] );
	// }
	//
	// return results;
	// }

	public List<Object[]> getDauerByMitarbeiter( String _hydroid ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> buchungen = this.em.createNamedQuery( "buchung.find.duration.by.mitarbeiter" )
				.setParameter( "hydroid", _hydroid ).getResultList();
		return buchungen;
	}

	public List<Object[]> getDauerByMitarbeiterAndMonth( String hydroid, Integer month ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> buchungen = this.em.createNamedQuery( "buchung.find.duration.by.mitarbeiter.and.month" )
				.setParameter( "hydroid", hydroid ).setParameter( "month", month ).getResultList();
		return buchungen;
	}

	public Long getDauerByMitarbeiterAndDay( String hydroid, Integer day, Integer month, Integer year ) {
		return (Long) this.em.createNamedQuery( "buchung.find.duration.by.mitarbeiter.and.day" )
				.setParameter( "hydroid", hydroid ).setParameter( "day", day ).setParameter( "month", month )
				.setParameter( "year", year ).getSingleResult();
	}

	// @SuppressWarnings( "unchecked" )
	// public List<Object[]> getDauerByMitarbeiterAndMonth( String _hydroid,
	// Integer _month ) {
	// String queryString =
	// "SELECT b.datum,sum(b.minuten) FROM buchungen AS b Inner Join Mitarbeiter AS m ON b.mitarbeiter = m.ID WHERE m.hydroId  = ?1 and month(b.datum) = ?2 group by b.datum order by b.datum";
	// Query query = this.em.createNativeQuery( queryString, Buchung.class );
	// query.setParameter( 1, _hydroid );
	// query.setParameter( 2, _month );
	// List<Object[]> buchungen = query.getResultList();
	// return buchungen;
	// }

	public List<Object[]> getDauerByProjektUndMitarbeiter( String _hydroid ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> buchungen = this.em.createNamedQuery( "buchung.find.duration.by.projekt.and.mitarbeiter" )
				.setParameter( "hydroid", _hydroid ).getResultList();

		return buchungen;
	}

	public List<Object[]> getDauerByProjektUndMitarbeiterAndMonth( String _hydroid, Integer _month, Integer _year ) {

		@SuppressWarnings( "unchecked" )
		List<Object[]> buchungen = this.em
				.createNamedQuery( "buchung.find.duration.by.projekt.and.mitarbeiter.and.month.and.year" )
				.setParameter( "hydroid", _hydroid ).setParameter( "month", _month ).setParameter( "year", _year )
				.getResultList();

		return buchungen;
	}

	public Buchung getBuchungById( Long _id ) {
		return this.em.find( Buchung.class, _id );
	}

	public Long findDurationByDate( String hydroid, Date date ) {
		return (Long) this.em.createNamedQuery( "buchung.find.duration.by.date" ).setParameter( "hydroid", hydroid )
				.setParameter( "datum", date ).getSingleResult();
	}

}
