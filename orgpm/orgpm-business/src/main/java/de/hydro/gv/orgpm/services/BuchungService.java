package de.hydro.gv.orgpm.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.BuchungDao;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.util.InvalidDateException;

@Stateless
public class BuchungService {

	@Inject
	private BuchungDao buchungDao;

	public Collection<Buchung> getAlleBuchungen() throws Exception {
		return this.buchungDao.readAllBuchungen();
	}

	public void deleteBuchung( Buchung b ) throws Exception {
		this.buchungDao.deleteBuchung( b );
	};

	public void addBuchung( Buchung b ) throws Exception {
		this.buchungDao.createBuchung( b );
		;
	}

	public void updateBuchung( Buchung b ) throws Exception {
		this.buchungDao.updateBuchung( b );
	}

	public Collection<Buchung> getBuchungenByMitarbeiter( String hydroid, Date date ) throws Exception {
		return this.buchungDao.getBuchungByMitarbeiter( hydroid, date );
	}

	public HashMap<String, Long> getDauerByMitarbeiter( String hydroid ) throws Exception {
		return this.buchungDao.getDauerByMitarbeiter( hydroid );
	}

	public Boolean isTimeViolated( String hydroid, Date date, Date azeit, Date ezeit ) throws Exception {
		Boolean ret = false;
		if( azeit == null || ezeit == null ) {
			throw new InvalidDateException( "Anfangszeit / Endezeit darf nicht null sein" );
		}
		if( azeit.after( ezeit ) ) {
			throw new InvalidDateException( "Die Anfangszeit muss kleiner Endezeit sein" );
		}
		for ( Buchung b : this.getBuchungenByMitarbeiter( hydroid, date ) ) {
			if( azeit.after( b.getAnfangZeit() ) && azeit.before( b.getEndeZeit() ) || ezeit.after( b.getAnfangZeit() )
					&& ezeit.before( b.getEndeZeit() ) || azeit.after( b.getAnfangZeit() )
					&& azeit.before( b.getEndeZeit() ) ) {
				ret = true;
				throw new InvalidDateException( "Die Aktivitätszeit wurde schon gebucht " );
			}
			ret = false;
		}
		return ret;

	}

	public Long findDurationByDate( String hydroid, Date date ) throws Exception {
		return this.buchungDao.findDurationByDate( hydroid, date );
	}

	public Buchung getBuchungById( Long id ) {
		return this.buchungDao.getBuchungById( id );
	}
}
