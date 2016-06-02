package de.hydro.gv.mplus.actions;

import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.spi.Context;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@javax.faces.bean.ManagedBean
@Stateful
@Named
public class Auth {

    private String username;
    private String password;
    private String originalURL;

    public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getOriginalURL() {
		return originalURL;
	}



	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}



	@PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }




	public String getUser()  {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		String user = httpServletRequest.getRemoteUser();
		Principal principal = httpServletRequest.getUserPrincipal();
        
		
        System.out.println("User login successful: " + user + principal);
        return user;
    }

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    // Getters/setters for username and password.
}