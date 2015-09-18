package de.hydro.gv.orgpm.util;

import java.security.Principal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@ManagedBean
@SessionScoped
public class Auth {

	private Mitarbeiter mitarbeiter; // The JPA entity.

	@Inject
	MitarbeiterDao mitarbeiterDao;

	public Mitarbeiter getMitarbeiter() {
		if( this.mitarbeiter == null ) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if( this.mitarbeiter != null ) {
				this.mitarbeiter = this.mitarbeiterDao.getMitarbeiterByHydroId( principal.getName() ); // Find
																										// User
																										// by
																										// j_username.
			}
		}
		return this.mitarbeiter;
	}

}