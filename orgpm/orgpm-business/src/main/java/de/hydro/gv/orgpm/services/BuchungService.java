package de.hydro.gv.orgpm.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.BuchungDao;
import de.hydro.gv.orgpm.data.Buchung;

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

}
