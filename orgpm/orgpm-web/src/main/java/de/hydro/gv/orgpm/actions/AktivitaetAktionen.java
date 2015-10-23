package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostPersist;

import org.jboss.logging.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.AktivitaetService;

@javax.faces.view.ViewScoped
@Named
public class AktivitaetAktionen implements Serializable {
	private static final long serialVersionUID = 7556527018928839740L;
	private static final Logger logger = Logger.getLogger( AktivitaetAktionen.class );

	private Aktivitaet aktAktivitaet = new Aktivitaet();

	@Inject
	private AktivitaetService aktivitaetService;

	@SuppressWarnings( "cdi-ambiguous-dependency" )
	@Inject
	private ProjektAktionen projektAktionen;

	private Collection<Aktivitaet> cachedAktivitaetenList;

	private boolean loaded = false;

	public Aktivitaet getAktAktivitaet() {
		return this.aktAktivitaet;
	}

	public void setAktAktivitaet( Aktivitaet aktAktivitaet ) {
		this.aktAktivitaet = aktAktivitaet;
	}

	public Collection<Aktivitaet> getAlle() throws Exception {

		if( this.cachedAktivitaetenList == null ) {
			this.cachedAktivitaetenList = this.aktivitaetService.getAlleAktivitaet();

		}
		return this.cachedAktivitaetenList;
	}

	public Collection<Aktivitaet> getAlleAktuelle() throws Exception {

		if( this.cachedAktivitaetenList == null ) {
			this.cachedAktivitaetenList = this.aktivitaetService.getAlleAktuelleAktivitaeten();

		}
		return this.cachedAktivitaetenList;
	}

	public Collection<Aktivitaet> getAlleDisabled() throws Exception {

		if( this.cachedAktivitaetenList == null ) {
			this.cachedAktivitaetenList = this.aktivitaetService.getAlleDisabledAktivitaeten();

		}
		return this.cachedAktivitaetenList;
	}

	public String addAktivitaet() throws Exception {
		Integer aktId;
		Projekt proj = this.projektAktionen.getProjekt();
		if( this.getMaxAktivitaetIdByProjektId( proj.getId() ) == null ) {
			aktId = 0;
		} else {
			aktId = this.getMaxAktivitaetIdByProjektId( proj.getId() );
		}
		this.aktAktivitaet.setAktivitaetNr( aktId + 1 );
		this.aktAktivitaet.setAktivitaetStatus( true );
		this.aktAktivitaet.setProjekt( this.projektAktionen.getProjekt() );
		logger.infov( "Eine neue Aktivität -{0}- für das Projekt {1} wurde hinzugefügt.",
				this.aktAktivitaet.getAktivitaetText(), this.aktAktivitaet.getProjekt().toString() );
		this.aktivitaetService.addAktivitaet( this.aktAktivitaet );
		this.cachedAktivitaetenList = null;
		this.clearSessionCache();
		return null;
	}

	public String addNewAktivitaet() {
		this.clearSessionCache();
		return "aktivitaet-input";
	}

	public String removeAktivitaet() throws Exception {
		de.hydro.gv.orgpm.data.Aktivitaet tempEntity = new de.hydro.gv.orgpm.data.Aktivitaet();
		tempEntity.setId( this.aktAktivitaet.getId() );
		logger.infov( "Die Aktivität -{0}- für das Projekt {1} wurde gelöscht.",
				this.aktAktivitaet.getAktivitaetText(), this.aktAktivitaet.getProjekt().toString() );
		this.aktivitaetService.deleteAktivitaet( tempEntity );
		this.cachedAktivitaetenList = null;
		this.clearSessionCache();
		return null;
	}

	public String updateAktivitaet() throws Exception {
		logger.infov( "Die Aktivität -{0}- für das Projekt {1} wurde aktualisiert.",
				this.aktAktivitaet.getAktivitaetText(), this.aktAktivitaet.getProjekt().toString() );
		this.aktivitaetService.updateAktivitaet( this.aktAktivitaet );
		return "/verwaltung/projekt-edit";
		// return "/verwaltung/projekte";
	}

	public Aktivitaet findAktivitaetById() {
		return this.aktivitaetService.getAktivitaetById( this.aktAktivitaet.getId() );
	}

	public void onRowEdit( RowEditEvent event ) throws Exception {
		DataTable s = (DataTable) event.getSource();
		Aktivitaet d = (Aktivitaet) s.getRowData();
		this.aktivitaetService.updateAktivitaet( ( d ) );
		FacesMessage msg = new FacesMessage( "Edit Activity", ( (Aktivitaet) event.getObject() ).getAktivitaetNr()
				.toString() );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public void onRowCancel( RowEditEvent event ) {
		FacesMessage msg = new FacesMessage( "Edit Cancelled", ( (Aktivitaet) event.getObject() ).getAktivitaetNr()
				.toString() );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public void onCellEdit( CellEditEvent event ) throws Exception {
		DataTable d = (DataTable) event.getSource();
		Aktivitaet a = (Aktivitaet) d.getRowData();

		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if( newValue != null && !newValue.equals( oldValue ) ) {
			a.setAktivitaetText( newValue.toString() );
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue
					+ ", New:" + newValue );
			this.aktivitaetService.updateAktivitaet( a );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
	}

	@PostPersist
	public void clearSessionCache() {

		this.aktAktivitaet.setId( null );
		this.aktAktivitaet.setAktivitaetStatus( false );
		this.aktAktivitaet.setAktivitaetText( null );
		this.aktAktivitaet.setBemerkung( null );
		this.aktAktivitaet.setPlanaufwand( null );
		this.aktAktivitaet.setProjekt( null );
		this.aktAktivitaet.setAktivitaetNr( null );

		System.out.println( "Clear session" );

	}

	public Integer getMaxAktivitaetIdByProjektId( Long id ) {
		return this.aktivitaetService.getMaxAktivitaetIdByProjektId( id );
	}

	public void onProjektChange( final AjaxBehaviorEvent event ) throws Exception {
		this.aktivitaetService.getAktivitaetenByProjekt( this.projektAktionen.getProjekt() );
	}

	@PostConstruct
	public void init() throws Exception {
		/** Aktivität aus DB lesen */
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String aktId = params.get( "aktivitaetId" );
		if( aktId != null && !aktId.equals( "" ) && !this.loaded ) {
			try {
				this.aktAktivitaet = this.aktivitaetService.getAktivitaetById( new Long( aktId ) );
				this.aktAktivitaet.setId( new Long( aktId ) );
				this.loaded = true;
			} catch ( Exception e ) {
				FacesContext.getCurrentInstance().addMessage( null,
						new FacesMessage( FacesMessage.SEVERITY_INFO, "Aktivität ist nicht vorhanden.", null ) );
				logger.warnv( e, "Aktivität {0} nicht gefunden", aktId );
			}
		}
	}
}
