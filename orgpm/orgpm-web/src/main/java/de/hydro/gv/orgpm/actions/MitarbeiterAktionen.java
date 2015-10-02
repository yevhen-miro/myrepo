package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.dao.LoginDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.services.MitarbeiterService;

@RolesAllowed( "ADMIN" )
@SessionScoped
@Named
public class MitarbeiterAktionen implements Serializable {

	private static final long serialVersionUID = 7278252373699029199L;

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	private LoginAction loginAction;

	@Inject
	private LoginDao loginDao;

	private Mitarbeiter mitarbeiter = new Mitarbeiter();

	String neuesPasswort;

	private ArrayList<Mitarbeiter> cachedMitarbeiterList;

	public Mitarbeiter getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( Mitarbeiter mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
	}

	public String getNeuesPasswort() {
		return this.neuesPasswort;
	}

	public void setNeuesPasswort( String neuesPasswort ) {
		this.neuesPasswort = neuesPasswort;
	}

	public Collection<Mitarbeiter> getAlleMitarbeiter() throws Exception {
		if( this.cachedMitarbeiterList == null ) {
			this.cachedMitarbeiterList = (ArrayList<Mitarbeiter>) this.mitarbeiterService.getAlleMitarbeiter();
		}
		return this.cachedMitarbeiterList;
	}

	public String addNewMitarbeiter() {
		this.clearSessionCache();
		return "mitarbeiter-input";
	}

	public String addMitarbeiter() throws Exception {
		Login log = new Login();
		log.setMitarbeiter( this.mitarbeiter );
		log.setPassword( this.encryptPassword( this.getNeuesPasswort() ) );
		this.loginDao.createLogin( log );
		this.mitarbeiterService.updateMitarbeiter( this.mitarbeiter );
		this.cachedMitarbeiterList = null;
		return "mitarbeiter";
	}

	public String removeMitarbeiter() throws Exception {
		this.mitarbeiterService.deleteMitarbeiter( this.mitarbeiter );
		this.cachedMitarbeiterList = null;
		return null;
	}

	public String updateMitarbeiter() throws Exception {
		this.mitarbeiterService.updateMitarbeiter( this.mitarbeiter );
		this.cachedMitarbeiterList = null;
		return "mitarbeiter";
	}

	public Mitarbeiter getMitarbeiterByHydroId() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		String hydroid = principal.getName().toUpperCase();
		this.mitarbeiterService.getMitarbeiterByHydroId( hydroid );
		Mitarbeiter mitarbeiter = this.mitarbeiterService.getMitarbeiterByHydroId( hydroid );
		return mitarbeiter;
	}

	public String updatePasswort() throws Exception {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		String hydroid = principal.getName().toUpperCase();
		Login l = this.mitarbeiterService.getLoginByMitarbeiter( hydroid );
		l.setPassword( this.encryptPassword( this.getNeuesPasswort() ) );
		this.loginDao.updateLogin( l );
		this.loginAction.logout();
		return "/login.xhtml?faces-redirect=true";
	}

	public String addLoginForMitarbeiter() {
		Login l = new Login();
		l.setMitarbeiter( this.mitarbeiter );
		l.setPassword( this.getNeuesPasswort() );
		this.loginDao.createLogin( l );
		return "mitarbeiter";

	}

	private String encryptPassword( String password ) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance( "SHA-256" );
		byte[] passwordBytes = password.getBytes();
		byte[] hash = md.digest( passwordBytes );
		String passwordHash = Base64.getEncoder().encodeToString( hash );
		return passwordHash;
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	public void clearSessionCache() {
		this.mitarbeiter.setArbeitszeit( 0 );
		this.mitarbeiter.setBemerkung( null );
		this.mitarbeiter.setEinstellungsdatum( null );
		this.mitarbeiter.setGeburtsdatum( null );
		this.mitarbeiter.setGruppe( null );
		this.mitarbeiter.setHydroId( null );
		this.mitarbeiter.setId( null );
		this.mitarbeiter.setKartenNum( 0 );
		this.mitarbeiter.setKuendigungsdatum( null );
		this.mitarbeiter.setLogin( null );
		this.mitarbeiter.setMitarbeiterkennung( null );
		this.mitarbeiter.setMitarbeiterStatus( 0 );
		this.mitarbeiter.setName( null );
		this.mitarbeiter.setPersonalNum( 0 );
		this.mitarbeiter.setRolle( null );
		this.mitarbeiter.setVorname( null );
	}

}
