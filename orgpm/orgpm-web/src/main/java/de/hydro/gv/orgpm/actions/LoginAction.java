package de.hydro.gv.orgpm.actions;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.jboss.logging.Logger;

@Stateless
@Named
public class LoginAction {
	private static final Logger logger = Logger.getLogger( LoginAction.class );

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		logger.infov( "Session beendet" );
		return "/login.xhtml?faces-redirect=true";
	}
}
