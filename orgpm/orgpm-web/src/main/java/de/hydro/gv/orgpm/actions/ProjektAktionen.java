package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.ProjektService;

@ViewScoped
@Named
public class ProjektAktionen implements Serializable {
	private static final long serialVersionUID = 123423423423L;
	private static final Logger logger = Logger.getLogger( ProjektAktionen.class );

	private Projekt projekt = new Projekt();

	String projId;

	public String getProjId() {
		return this.projId;
	}

	public void setProjId( String projId ) {
		this.projId = projId;
	}

	private Aktivitaet aktivitaet = new Aktivitaet();

	@Inject
	private ProjektService projektService;

	@Inject
	private AktivitaetService aktivitaetService;

	@Inject
	MitarbeiterAktionen mitarbeiterAktionen;

	private ArrayList<Projekt> cachedProjektList;

	private Collection<Aktivitaet> cachedAktivitaetenList;

	private boolean loaded = false;

	public Projekt getProjekt() {
		return this.projekt;
	}

	public void setProjekt( Projekt projekt ) {
		this.projekt = projekt;
	}

	public Aktivitaet getAktivitaet() {
		return this.aktivitaet;
	}

	public void setAktivitaet( Aktivitaet aktivitaet ) {
		this.aktivitaet = aktivitaet;
	}

	public Collection<Projekt> getAlleProjekte() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = (ArrayList<Projekt>) this.projektService.getAlleProjekte();
		}
		return this.cachedProjektList;
	}

	public Collection<Projekt> getAlleAktiveProjekte() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = (ArrayList<Projekt>) this.projektService.getAlleAktiveProjekte();
		}
		return this.cachedProjektList;
	}

	public Collection<Aktivitaet> getAktivitaetenByProjekt() throws Exception {
		Collection<Aktivitaet> retVal = null;
		if( this.cachedAktivitaetenList == null ) {
			this.cachedAktivitaetenList = this.aktivitaetService.getAktivitaetenByProjekt( this.projekt );
			retVal = this.cachedAktivitaetenList;
			this.cachedAktivitaetenList = null;
		}
		return retVal;
	}

	public String addNewProjekt() {
		this.clearSessionCache();
		return "projekt-input";
	}

	public String addProjekt() throws Exception {
		logger.infov( "Das neue Projekt -{0}-  wurde hinzugefügt.", this.projekt.getProjektId() );
		this.projektService.addProjekt( this.projekt );
		this.cachedProjektList = null;
		return "projekte";
	}

	public String removeProjekt() throws Exception {
		logger.infov( "Das Projekt -{0}-  wurde gelöscht.", this.projekt.getProjektId() );
		this.projektService.deleteProjekt( this.projekt );
		this.cachedProjektList = null;
		return null;
	}

	public String updateProjekt() throws Exception {
		logger.infov( "Das Projekt -{0}-  wurde aktualisiert.", this.projekt.getProjektId() );
		this.projektService.updateProjekt( this.projekt );
		this.cachedProjektList = null;
		return "projekte";
	}

	public void clearSessionCache() {
		this.projekt.setId( null );
		this.projekt.setBemerkung( null );
		this.projekt.setHauptprojekt( null );
		this.projekt.setPlanaufwand( 0 );
		this.projekt.setProjektende( null );
		this.projekt.setProjektGruppe( null );
		this.projekt.setProjektId( null );
		this.projekt.setProjektLeiter( null );
		this.projekt.setProjektName( null );
		this.projekt.setProjektstart( null );
		this.projekt.setStorniertesProjekt( false );
		this.projekt.setWartungsprojekt( false );
	}

	public void onProjektChange( final AjaxBehaviorEvent event ) throws Exception {
		this.aktivitaetService.getAktivitaetenByProjekt( this.getProjekt() );
	}

	@PostConstruct
	public void init() throws Exception {
		/** Projekt aus DB lesen */
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String projektId = params.get( "projektId" );
		if( projektId != null && !projektId.equals( "" ) && !this.loaded ) {
			try {
				this.projId = projektId;
				this.projekt = this.projektService.getProjektById( new Long( projektId ) );
				this.projekt.setId( new Long( projektId ) );
				this.loaded = true;
			} catch ( Exception e ) {
				FacesContext.getCurrentInstance().addMessage( null,
						new FacesMessage( FacesMessage.SEVERITY_INFO, "Projekt ist nicht vorhanden.", null ) );
				logger.warnv( e, "Projekt {0} nicht gefunden", projektId );
			}
		}
	}

}
