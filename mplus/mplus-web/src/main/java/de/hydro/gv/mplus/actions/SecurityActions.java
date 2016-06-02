package de.hydro.gv.mplus.actions;

import java.security.Principal;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.hydro.gv.mplus.data.SystemRole;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.services.SystemUserService;

@Stateless
@Named
public class SecurityActions {

	@Inject
	SystemUserService userService;

	public String getSecurityPrincipalForLoggedInUser() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

	public String getMitarbeiterByHydroId() {
		SystemUser su = this.userService.findUserByHydroId(this.getSecurityPrincipalForLoggedInUser());
		return su.getFullName();
	}

	public String getMitarbeiterRolleByHydroId() {
		SystemRole role = this.userService.findRoleByHydroId( this.getSecurityPrincipalForLoggedInUser());
		return role.getDisplayName();
	}

	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
/*
	public Boolean isMitarbeiterAdmin() {
		String rolle = this.mitarbeiterService.getMitarbeiterRolleByHydroId( this.getSecurityPrincipalForLoggedInUser()
				.toUpperCase() );
		Boolean retVal = ( rolle == "ADMIN" ) ? true : false;
		return retVal;
	}
*/
	// public RolleEnum[] getAlle() {
	// return RolleEnum.values();
	// }

}
