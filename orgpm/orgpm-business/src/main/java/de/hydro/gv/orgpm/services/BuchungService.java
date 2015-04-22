package de.hydro.gv.orgpm.services;

import java.util.Collection;

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

	public void deleteBuchung(Buchung b) throws Exception {
		this.buchungDao.deleteBuchung(b);
	};

	public void addBuchung(Buchung b) throws Exception {
		this.buchungDao.createBuchung(b);
		;
	}

	public void updateBuchung(Buchung b) throws Exception {
		this.buchungDao.updateBuchung(b);
	}
	
	

}
