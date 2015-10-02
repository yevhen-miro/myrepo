package de.hydro.gv.orgpm.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.AktivitaetDao;
import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;

@Stateless
public class AktivitaetService {

	@Inject
	private AktivitaetDao aktivitaetDao;

	public Collection<Aktivitaet> getAlleAktivitaet() throws Exception {
		return this.aktivitaetDao.readAllAktivitaeten();
	}

	public void deleteAktivitaet( Aktivitaet a ) throws Exception {
		this.aktivitaetDao.deleteAktivitaet( a );
	};

	public void addAktivitaet( Aktivitaet a ) throws Exception {
		this.aktivitaetDao.addAktivitaet( a );
		;
	}

	public void updateAktivitaet( Aktivitaet a ) throws Exception {
		this.aktivitaetDao.updateAktivitaet( a );
	}

	public List<Aktivitaet> getAktivitaetenByProjektName( String projektName ) throws Exception {
		return this.aktivitaetDao.getAktivitaetenByProjektName( projektName );
	}

	public List<Aktivitaet> getAktivitaetenByProjektid( Long projektid ) throws Exception {
		return this.aktivitaetDao.getAktivitaetenByProjektid( projektid );
	}

	public List<Aktivitaet> getAktivitaetenByProjekt( Projekt projekt ) throws Exception {
		return this.aktivitaetDao.getAktivitaetenByProjekt( projekt.getProjektId() );
	}

	public Aktivitaet getAktivitaetById( Long id ) {
		return this.aktivitaetDao.getAktivitaetById( id );
	}

	public Integer getMaxAktivitaetIdByProjektId( Long id ) {
		return this.aktivitaetDao.getMaxAktivitaetIdByProjektId( id );
	}

	public Collection<Aktivitaet> getAlleAktuelleAktivitaeten() throws Exception {
		return this.aktivitaetDao.readAllAktivitaeten();
	}

	public Collection<Aktivitaet> getAlleDisabledAktivitaeten() throws Exception {
		return this.aktivitaetDao.readAllAktivitaeten();
	}

}
