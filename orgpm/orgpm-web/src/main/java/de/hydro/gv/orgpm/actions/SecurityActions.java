package de.hydro.gv.orgpm.actions;

import java.security.Principal;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.hydro.gv.orgpm.services.MitarbeiterService;

@SessionScoped
@ManagedBean
@Named
public class SecurityActions {

	@Inject
	MitarbeiterService mitarbeiterService;

	public String getSecurityPrincipalForLoggedInUser() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		System.out.println( principal.toString() );
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

	public String getMitarbeiterByHydroId() {
		String vorname = this.mitarbeiterService.getMitarbeiterByHydroId(
				this.getSecurityPrincipalForLoggedInUser().toUpperCase() ).getVorname();
		String nachname = this.mitarbeiterService.getMitarbeiterByHydroId(
				this.getSecurityPrincipalForLoggedInUser().toUpperCase() ).getName();
		String hydroid = this.getSecurityPrincipalForLoggedInUser();

		return vorname + " " + nachname + " (" + hydroid + ")";
	}

	public String getMitarbeiterRolleByHydroId() {
		String rolle = this.mitarbeiterService.getMitarbeiterRolleByHydroId( this.getSecurityPrincipalForLoggedInUser()
				.toUpperCase() );
		return rolle;
	}

	public Boolean isMitarbeiterAdmin() {
		String rolle = this.mitarbeiterService.getMitarbeiterRolleByHydroId( this.getSecurityPrincipalForLoggedInUser()
				.toUpperCase() );
		Boolean retVal = ( rolle == "ADMIN" ) ? true : false;
		return retVal;
	}
}
