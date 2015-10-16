package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.BuchungService;
import de.hydro.gv.orgpm.services.MitarbeiterService;
import de.hydro.gv.orgpm.services.ProjektService;
import de.hydro.gv.orgpm.util.InvalidDateException;
import de.hydro.gv.orgpm.util.Logged;
import de.hydro.gv.orgpm.utils.TimingService;

@ManagedBean
@ViewScoped
@Logged
public class BuchungActions implements Serializable {
	private static final long serialVersionUID = 8776872386309781977L;
	private static final Logger logger = Logger.getLogger( BuchungActions.class );

	private Buchung aktBuchung = new Buchung();
	private boolean loaded = false;
	@Inject
	private BuchungService buchungService;

	@Inject
	private ProjektService projektService;

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	private AktivitaetService aktivitaetService;

	@Inject
	private TimingService timingService;

	private Date date = new Date(); // current Date variable
	private ArrayList<Buchung> cachedBuchungList;
	private Collection<Aktivitaet> cachedAktivitaetenList;

	public Date getDate() {
		return this.date;
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public Buchung getAktBuchung() {
		return this.aktBuchung;
	}

	public void setAktBuchung( Buchung aktBuchung ) {
		this.aktBuchung = aktBuchung;
	}

	public Collection<Buchung> getAlleBuchungen() throws Exception {
		if( this.cachedBuchungList == null ) {
			this.cachedBuchungList = this.filterBuchungenByDate( this.date );
		} else {
			this.cachedBuchungList = this.filterBuchungenByDate( this.date );
		}
		return this.cachedBuchungList;
	}

	public Collection<Projekt> getAlleZugelasseneProjekte() throws Exception {
		return this.projektService.getAlleZugelasseneProjekte( this.getHydroId().toUpperCase() );
	}

	@SuppressWarnings( "static-access" )
	public String saveBuchung() throws Exception {

		if( this.aktBuchung.getDatum() == null ) {
			String message = "Das Datum darf nicht leer sein";
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}", this.getHydroId(), message );
			return null;
		}

		try {
			this.buchungService.isTimeViolated( this.getHydroId().toUpperCase(), this.aktBuchung.getDatum(),
					this.aktBuchung.getAnfangZeit(), this.aktBuchung.getEndeZeit() );
		} catch ( InvalidDateException e ) {
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}", this.getHydroId(), e.toString() );
			return null;
		}

		this.aktBuchung.setMitarbeiter( this.mitarbeiterService.getMitarbeiterByHydroId( this.getHydroId()
				.toUpperCase() ) );
		this.aktBuchung.setMin( this.timingService.calculateMinutes( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		this.aktBuchung.setPauseVon( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_V ) : null );
		this.aktBuchung.setPauseBis( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_B ) : null );
		this.aktBuchung.setStd( this.timingService.calculateHours( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		logger.infov( "Der Benutzer {0} hat eine neue Buchung -{1}- für {2} erfasst.", this.getHydroId(),
				this.aktBuchung.getTaetigkeiten(), this.aktBuchung.getDatum().toString() );
		this.setDate( this.aktBuchung.getDatum() );
		this.buchungService.addBuchung( this.aktBuchung );
		this.cachedBuchungList = null;
		this.aktBuchung = null;
		return "buchungen";
	}

	public String removeBuchung() throws Exception {
		logger.infov( "Der Benutzer {0} hat die Buchung -{1}- von {2} gelöscht.", this.getHydroId(),
				this.aktBuchung.getTaetigkeiten(), this.aktBuchung.getDatum().toString() );
		this.buchungService.deleteBuchung( this.aktBuchung );
		this.clearSessionCache();
		return null;
	}

	public String updateBuchung() throws Exception {
		logger.infov( "Der Benutzer {0} hat die Buchung -{1}- von {2} aktualisiert.", this.getHydroId(),
				this.aktBuchung.getTaetigkeiten(), this.aktBuchung.getDatum().toString() );
		this.buchungService.updateBuchung( this.aktBuchung );
		this.cachedBuchungList = null;
		return "/buchungen/buchungen.xhtml";
	}

	public String getHydroId() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		System.out.println( principal.toString() );
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

	public Collection<Aktivitaet> getAktivitaetenByProjekt() throws Exception {
		ArrayList<Aktivitaet> ret;
		if( this.aktBuchung.getProjekt() == null ) {
			List<Projekt> collection = (List<Projekt>) this.projektService.getProjekteByMitarbeiter( this.getHydroId()
					.toUpperCase() );
			if( collection != null ) {
				this.aktBuchung.setProjekt( collection.get( 0 ) );
			} else {
				ret = null;
			}
			Collection<Aktivitaet> actEntities = this.aktivitaetService.getAktivitaetenByProjekt( this.aktBuchung
					.getProjekt() );
			ret = (ArrayList<Aktivitaet>) actEntities;
		} else {
			Collection<Aktivitaet> actEntities = this.aktivitaetService.getAktivitaetenByProjekt( this.aktBuchung
					.getProjekt() );
			ret = (ArrayList<Aktivitaet>) actEntities;
		}
		return ret;
	}

	private ArrayList<Buchung> filterBuchungenByDate( Date date ) throws Exception {
		Collection<Buchung> buchungEntities = this.buchungService.getBuchungenByMitarbeiter( this.getHydroId().trim()
				.toUpperCase(), date );
		return (ArrayList<Buchung>) buchungEntities;
	}

	@PostPersist
	@PostRemove
	@PostUpdate
	@PreDestroy
	private void clearSessionCache() {
		this.cachedBuchungList = null;
		this.cachedAktivitaetenList = null;
		this.aktBuchung.setId( null );
		this.aktBuchung.setTaetigkeiten( "" );
		this.aktBuchung.setAnfangZeit( null );
		this.aktBuchung.setEndeZeit( null );
		this.aktBuchung.setDatum( null );
		this.aktBuchung.setMin( 0L );
		this.aktBuchung.setStd( 0.0 );
		this.aktBuchung.setPauseVon( null );
		this.aktBuchung.setPauseBis( null );
		this.aktBuchung.setProjekt( null );
	}

	public void onCellEdit( CellEditEvent event ) throws Exception {
		DataTable d = (DataTable) event.getSource();
		Buchung b = (Buchung) d.getRowData();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if( newValue != null && !newValue.equals( oldValue ) ) {
			b.setTaetigkeiten( newValue.toString() );
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue
					+ ", New:" + newValue );
			this.buchungService.updateBuchung( b );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
	}

	public void onDateSelect( SelectEvent event ) throws Exception {
		Date filterDate = (Date) event.getObject();
		this.setDate( filterDate );
		this.filterBuchungenByDate( filterDate );
	}

	public void onProjektChange() throws Exception {
		if( this.aktBuchung.getProjekt() != null ) {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
			this.aktBuchung.setActivities( this.cachedAktivitaetenList );
		} else {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
		}
	}

	public Long findDurationAsLong() throws Exception {
		return this.buchungService.findDurationByDate( this.getHydroId().toUpperCase(), this.getDate() );
	}

	public String findDurationByDate() throws Exception {
		Long t = this.buchungService.findDurationByDate( this.getHydroId().toUpperCase(), this.getDate() );
		if( t == null ) {
			t = new Long( 0 );
		}
		int hours = (int) Math.floor( t / 60 );
		int minutes = (int) ( t % 60 );
		return String.format( "%d:%02d", hours, minutes );
	}

	public String calculateRest() throws Exception {
		Long t = this.buchungService.findDurationByDate( this.getHydroId().toUpperCase(), this.getDate() );
		if( t == null ) {
			t = new Long( 0 );
		}
		Long rest = 480 - t;
		int hours = (int) Math.floor( rest / 60 );
		int minutes = (int) ( rest % 60 );
		return String.format( "%d:%02d", hours, minutes );
	}

	@PostConstruct
	public void init() throws Exception {
		/** Buchung aus DB lesen */
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String buchungId = params.get( "buchung" );
		if( buchungId != null && !buchungId.equals( "" ) && !this.loaded ) {
			try {
				this.aktBuchung = this.buchungService.getBuchungById( new Long( buchungId ) );
				this.aktBuchung.setId( new Long( buchungId ) );
				this.loaded = true;
			} catch ( Exception e ) {
				FacesContext.getCurrentInstance().addMessage( null,
						new FacesMessage( FacesMessage.SEVERITY_INFO, "Buchung ist nicht vorhanden.", null ) );
				logger.warnv( e, "Aktivität {0} nicht gefunden", buchungId );
			}
		}
	}
}
