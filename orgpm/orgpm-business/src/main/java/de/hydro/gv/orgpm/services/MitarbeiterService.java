package de.hydro.gv.orgpm.services;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.auth.RolleEnum;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@Stateless
public class MitarbeiterService {

	@Inject
	private MitarbeiterDao mitarbeiterDao;

	@Resource
	private SessionContext sessionContext;

	public Collection<Mitarbeiter> getAlleMitarbeiter() throws Exception {
		return this.mitarbeiterDao.readAllMitarbeiter();
	}

	public void deleteMitarbeiter( Mitarbeiter m ) throws Exception {
		this.mitarbeiterDao.deleteMitarbeiter( m );
	};

	public void addMitarbeiter( Mitarbeiter m ) throws Exception {
		this.mitarbeiterDao.createMitarbeiter( m );
		;
	}

	public void updateMitarbeiter( Mitarbeiter m ) throws Exception {
		this.mitarbeiterDao.updateMitarbeiter( m );
	}

	public Mitarbeiter getMitarbeiterByHydroId( String hydroid ) {
		return this.mitarbeiterDao.getMitarbeiterByHydroId( hydroid );
	}

	public Mitarbeiter getMitarbeiterById( Long id ) {
		return this.mitarbeiterDao.getMitarbeiterById( id );
	}

	public String getMitarbeiterRolleByHydroId( String hydroid ) {
		return this.mitarbeiterDao.getMitarbeiterRolleByHydroId( hydroid );
	}

	// public Rolle getRolleByHydroId( String hydroid ) {
	// return this.mitarbeiterDao.getRolleByHydroId( hydroid );
	// }
	//
	// public Login getLoginByHydroId( String hydroid ) {
	// return this.mitarbeiterDao.getLoginByHydroId( hydroid );
	// }
	//
	// public Rolle getRolleById( Long id ) {
	// return this.mitarbeiterDao.getRolleById( id );
	// }

	public List<RolleEnum> getAlleRollen() {
		return this.mitarbeiterDao.readAllRollen();
	}

	public Login getLoginByMitarbeiter( String hydroid ) {
		return this.mitarbeiterDao.getLoginByMitarbeiter( hydroid );
	}
}
