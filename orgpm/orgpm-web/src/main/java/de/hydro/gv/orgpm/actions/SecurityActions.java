package de.hydro.gv.orgpm.actions;

import java.security.Principal;

import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Stateful
@Named
public class SecurityActions {

	public String getSecurityPrincipalForLoggedInUser() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

}
