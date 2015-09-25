package de.hydro.gv.orgpm.services;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.ProjektDao;
import de.hydro.gv.orgpm.data.Projekt;

@Stateless
public class ProjektService {

	@Inject
	private ProjektDao projektDao;

	public Collection<Projekt> getAlleProjekte() throws Exception {
		return this.projektDao.readAlleProjekte();
	}

	public void deleteProjekt( Projekt p ) throws Exception {
		this.projektDao.deleteProjekt( p );
	};

	public void addProjekt( Projekt p ) throws Exception {
		this.projektDao.createProjekt( p );
		;
	}

	public void updateProjekt( Projekt p ) throws Exception {
		this.projektDao.updateProjekt( p );
	}

	public Collection<Projekt> getProjekteByMitarbeiter( String hydroid ) {
		return this.projektDao.getProjektByMitarbeiterId( hydroid );
	}

}
