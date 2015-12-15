package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.jboss.logging.Logger;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.auth.RolleEnum;
import de.hydro.gv.orgpm.dao.LoginDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.data.MitarbeiterProjekte;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.MitarbeiterService;
import de.hydro.gv.orgpm.services.ProjektService;

@RolesAllowed( "ADMIN" )
@ViewScoped
@Named
public class MitarbeiterAktionen implements Serializable {
	private static final long serialVersionUID = 7278252373699029199L;
	private static final Logger logger = Logger.getLogger( MitarbeiterAktionen.class );

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	ProjektService projektService;

	@Inject
	private LoginAction loginAction;

	@Inject
	private LoginDao loginDao;

	private Mitarbeiter mitarbeiter = new Mitarbeiter();
	private DualListModel<Projekt> zugelasseneProjekte;
	String neuesPasswort;
	private boolean loaded = false;

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

	public DualListModel<Projekt> getZugelasseneProjekte() {
		return this.zugelasseneProjekte;
	}

	public void setZugelasseneProjekte( DualListModel<Projekt> zugelasseneProjekte ) {
		this.zugelasseneProjekte = zugelasseneProjekte;
	}

	public Collection<Mitarbeiter> getAlleMitarbeiter() throws Exception {
		if( this.cachedMitarbeiterList == null ) {
			this.cachedMitarbeiterList = (ArrayList<Mitarbeiter>) this.mitarbeiterService.getAlleMitarbeiter();
		}
		return this.cachedMitarbeiterList;
	}

	public Collection<Mitarbeiter> getAlleITMitarbeiter() throws Exception {
		if( this.cachedMitarbeiterList == null ) {
			this.cachedMitarbeiterList = (ArrayList<Mitarbeiter>) this.mitarbeiterService.getAlleITMitarbeiter();
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
		logger.infov( "Ein neuer Mitarbeiter -{0}- wurde hinzugefügt.", this.mitarbeiter.getHydroId() );
		this.mitarbeiterService.updateMitarbeiter( this.mitarbeiter );
		this.cachedMitarbeiterList = null;
		return "mitarbeiter";
	}

	public String removeMitarbeiter() throws Exception {
		logger.infov( "Der Mitarbeiter -{0}- wurde gelöscht.", this.mitarbeiter.getHydroId() );
		this.mitarbeiterService.deleteMitarbeiter( this.mitarbeiter );
		this.cachedMitarbeiterList = null;
		return null;
	}

	public String updateMitarbeiter() throws Exception {
		logger.infov( "Der Mitarbeiter -{0}- wurde aktualisiert.", this.mitarbeiter.getHydroId() );
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
		this.mitarbeiter.setProjekte( null );
	}

	// @PostConstruct
	public void loadEnabledProjects() throws Exception {
		List<Projekt> projekteSource = new ArrayList<Projekt>();
		List<Projekt> projekteTarget = new ArrayList<Projekt>();

		projekteSource = (List<Projekt>) this.projektService.getAlleAktiveProjekte();

		try {
			projekteTarget = this.projektService.getAlleZugelasseneProjekte( this.mitarbeiter.getHydroId() );
		} catch ( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if( projekteTarget.size() != 0 ) {
			List<Projekt> projsource = new ArrayList<Projekt>();
			for ( Projekt p : projekteSource ) {
				projsource.add( p );
			}
			for ( Projekt ps : projekteSource ) {
				for ( Projekt pt : projekteTarget ) {
					if( ps.equals( pt ) ) {
						projsource.remove( ps );
					}
				}

			}
			projekteSource = projsource;
		}

		this.zugelasseneProjekte = new DualListModel<Projekt>( projekteSource, projekteTarget );

	}

	public void onTransfer( TransferEvent event ) throws Exception {
		if( event.isAdd() || event.isRemove() ) {
			List<Projekt> projekte = this.zugelasseneProjekte.getTarget();
			List<MitarbeiterProjekte> mprojekte = new ArrayList<MitarbeiterProjekte>();
			for ( Projekt p : projekte ) {
				MitarbeiterProjekte mprojekt = new MitarbeiterProjekte();
				mprojekt.setMitarbeiter( this.mitarbeiter );
				mprojekt.setProjekt( p );
				mprojekte.add( mprojekt );
			}
			this.mitarbeiterService.removeOldMitarbeiterProjekte( this.mitarbeiter );
			this.mitarbeiter.setProjekte( mprojekte );
			;
		}

	}

	@PostConstruct
	public void init() throws Exception {
		/** Mitarbeiter aus DB lesen */
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String mitarbeiterId = params.get( "ma-id" );
		if( mitarbeiterId != null && !mitarbeiterId.equals( "" ) && !this.loaded ) {
			try {
				this.mitarbeiter = this.mitarbeiterService.getMitarbeiterById( new Long( mitarbeiterId ) );
				this.mitarbeiter.setId( new Long( mitarbeiterId ) );
				this.loaded = true;
			} catch ( Exception e ) {
				FacesContext.getCurrentInstance().addMessage( null,
						new FacesMessage( FacesMessage.SEVERITY_INFO, "Mitarbeiter ist nicht vorhanden.", null ) );
				logger.warnv( e, "Mitarbeiter {0} nicht gefunden", mitarbeiterId );
			}
		}
		this.loadEnabledProjects();
	}

	public void onTabChange( TabChangeEvent event ) throws Exception {
		this.loadEnabledProjects();
	}

	public RolleEnum[] getAlleRollen() {
		return RolleEnum.values();
	}

}
