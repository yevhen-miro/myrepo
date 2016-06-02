package de.hydro.gv.mplus.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.security.Principal;


import de.hydro.gv.mplus.dao.SystemUserDao;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.services.SystemUserService;

@ManagedBean
@SessionScoped
public class AuthService {

private SystemUser user; // The JPA entity.

@Inject
SystemUserService userService;

		public SystemUser getUser() {
			if( this.user == null ) {
				Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
				if( this.user != null ) {
					this.user = userService.findUserById( principal.getName() );																// j_username.
				}
			}
			return this.user;
		}

	}
	

