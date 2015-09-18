package de.hydro.gv.orgpm.actions;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Stateless
@Named
public class LoginAction {

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
}
