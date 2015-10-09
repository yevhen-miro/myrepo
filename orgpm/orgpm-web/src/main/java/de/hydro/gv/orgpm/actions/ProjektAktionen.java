package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.ProjektService;

@SessionScoped
@Named
public class ProjektAktionen implements Serializable {

	private static final long serialVersionUID = 123423423423L;

	private Projekt projekt = new Projekt();

	private Aktivitaet aktivitaet = new Aktivitaet();

	@Inject
	private ProjektService projektService;

	@Inject
	private AktivitaetService aktivitaetService;

	@Inject
	MitarbeiterAktionen mitarbeiterAktionen;

	private ArrayList<Projekt> cachedProjektList;

	private Collection<Aktivitaet> cachedAktivitaetenList;

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
		this.projektService.addProjekt( this.projekt );
		this.cachedProjektList = null;
		return "projekte";
	}

	public String removeProjekt() throws Exception {
		this.projektService.deleteProjekt( this.projekt );
		this.cachedProjektList = null;
		return null;
	}

	public String updateProjekt() throws Exception {
		System.out.println( "The id is " + this.projekt.getId().toString() );
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

}
