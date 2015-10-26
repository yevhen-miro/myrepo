package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
	private static final long ANFANG = 28800000L;
	private static final long ENDE = 60000000L;
	private static final String TÄTIGKEIT = "Urlaub / Schulung / Gleittag";

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
	private SecurityActions securityService;

	@Inject
	private TimingService timingService;

	private Date date = new Date(); // current Date variable
	private static Date dateT;
	private Date filterDate;

	public Date getFilterDate() {
		return this.filterDate;
	}

	public void setFilterDate( Date filterDate ) {
		this.filterDate = filterDate;
	}

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

	@SuppressWarnings( "static-access" )
	public Collection<Buchung> getAlleBuchungen() throws Exception {
		Collection<Buchung> retVal = new ArrayList<Buchung>();

		// if( this.cachedBuchungList == null ) {
		// this.cachedBuchungList = this.filterBuchungenByDate( this.date );
		// } else {
		// this.cachedBuchungList = this.filterBuchungenByDate( this.filterDate
		// );
		// }
		// return this.cachedBuchungList;
		if( this.dateT == null ) {
			retVal = this.filterBuchungenByDate( this.date );
		} else {
			retVal = this.filterBuchungenByDate( this.dateT );
		}
		return retVal;
	}

	public Collection<Projekt> getAlleZugelasseneProjekte() throws Exception {
		return this.projektService.getAlleZugelasseneProjekte( this.securityService
				.getSecurityPrincipalForLoggedInUser().toUpperCase() );
	}

	@SuppressWarnings( "static-access" )
	public String saveBuchung() throws Exception {

		if( this.aktBuchung.getDatum() == null ) {
			String message = "Das Datum darf nicht leer sein";
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}",
					this.securityService.getSecurityPrincipalForLoggedInUser(), message );
			return null;
		}

		try {
			this.buchungService.isTimeViolated( this.securityService.getSecurityPrincipalForLoggedInUser()
					.toUpperCase(), this.aktBuchung.getDatum(), this.aktBuchung.getAnfangZeit(), this.aktBuchung
					.getEndeZeit() );
		} catch ( InvalidDateException e ) {
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}",
					this.securityService.getSecurityPrincipalForLoggedInUser(), e.toString() );
			return null;
		}

		this.aktBuchung.setMitarbeiter( this.mitarbeiterService.getMitarbeiterByHydroId( this.securityService
				.getSecurityPrincipalForLoggedInUser().toUpperCase() ) );
		this.aktBuchung.setMin( this.timingService.calculateMinutes( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		this.aktBuchung.setPauseVon( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_V ) : null );
		this.aktBuchung.setPauseBis( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_B ) : null );
		this.aktBuchung.setStd( this.timingService.calculateHours( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		logger.infov( "Der Benutzer {0} hat eine neue Buchung -{1}- für {2} erfasst.",
				this.securityService.getSecurityPrincipalForLoggedInUser(), this.aktBuchung.getTaetigkeiten(),
				this.aktBuchung.getDatum().toString() );
		this.setDate( this.aktBuchung.getDatum() );
		this.buchungService.addBuchung( this.aktBuchung );
		// this.cachedBuchungList = null;
		// this.aktBuchung = null;
		return "buchungen";
	}

	public String removeBuchung() throws Exception {
		logger.infov( "Der Benutzer {0} hat die Buchung -{1}- von {2} gelöscht.",
				this.securityService.getSecurityPrincipalForLoggedInUser(), this.aktBuchung.getTaetigkeiten(),
				this.aktBuchung.getDatum().toString() );
		this.buchungService.deleteBuchung( this.aktBuchung );
		this.clearSessionCache();
		return null;
	}

	@SuppressWarnings( "static-access" )
	public String updateBuchung() throws Exception {
		if( this.aktBuchung.getDatum() == null ) {
			String message = "Das Datum darf nicht leer sein";
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}",
					this.securityService.getSecurityPrincipalForLoggedInUser(), message );
			return null;
		}

		try {
			this.buchungService.isTimeViolated( this.securityService.getSecurityPrincipalForLoggedInUser()
					.toUpperCase(), this.aktBuchung.getDatum(), this.aktBuchung.getAnfangZeit(), this.aktBuchung
					.getEndeZeit() );
		} catch ( InvalidDateException e ) {
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null ) );
			logger.warnv( "Benutzer {0} hat ein Eingabefehler : {1}",
					this.securityService.getSecurityPrincipalForLoggedInUser(), e.toString() );
			return null;
		}

		this.aktBuchung.setMitarbeiter( this.mitarbeiterService.getMitarbeiterByHydroId( this.securityService
				.getSecurityPrincipalForLoggedInUser().toUpperCase() ) );
		this.aktBuchung.setMin( this.timingService.calculateMinutes( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		this.aktBuchung.setPauseVon( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_V ) : null );
		this.aktBuchung.setPauseBis( this.timingService.isPauseTime( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) ? new Date( this.timingService.PAUSE_B ) : null );
		this.aktBuchung.setStd( this.timingService.calculateHours( this.aktBuchung.getAnfangZeit(),
				this.aktBuchung.getEndeZeit() ) );
		logger.infov( "Der Benutzer {0} hat eine neue Buchung -{1}- für {2} erfasst.",
				this.securityService.getSecurityPrincipalForLoggedInUser(), this.aktBuchung.getTaetigkeiten(),
				this.aktBuchung.getDatum().toString() );
		this.setDate( this.aktBuchung.getDatum() );
		this.buchungService.updateBuchung( this.aktBuchung );
		// this.cachedBuchungList = null;
		// this.aktBuchung = null;
		return "buchungen";
	}

	public String getHydroId() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

	public Collection<Aktivitaet> getAktivitaetenByProjekt() throws Exception {
		ArrayList<Aktivitaet> ret;
		if( this.aktBuchung.getProjekt() == null ) {
			List<Projekt> collection = (List<Projekt>) this.projektService
					.getProjekteByMitarbeiter( this.securityService.getSecurityPrincipalForLoggedInUser().toUpperCase() );
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
		Collection<Buchung> buchungEntities = this.buchungService.getBuchungenByMitarbeiter( this.securityService
				.getSecurityPrincipalForLoggedInUser().trim().toUpperCase(), date );
		return (ArrayList<Buchung>) buchungEntities;
	}

	private void clearSessionCache() {
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

	@SuppressWarnings( "static-access" )
	public void onDateSelect( SelectEvent event ) throws Exception {
		Date filterDate = (Date) event.getObject();
		// this.filterBuchungenByDate( filterDate );
		this.dateT = (Date) filterDate.clone();
		// this.filterDate = filterDate;
	}

	public void onClick( ActionEvent event ) throws Exception {
		this.filterBuchungenByDate( this.filterDate );
	}

	public void onProjektChange() throws Exception {
		if( this.aktBuchung.getProjekt() != null ) {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
			this.aktBuchung.setActivities( this.cachedAktivitaetenList );
			if( this.aktBuchung.getProjekt().isGanztaegig() == true ) {
				this.aktBuchung.setAnfangZeit( new Date( ANFANG ) );
				this.aktBuchung.setEndeZeit( new Date( ENDE ) );
				this.aktBuchung.setTaetigkeiten( TÄTIGKEIT );
			} else {
				this.aktBuchung.setAnfangZeit( null );
				this.aktBuchung.setEndeZeit( null );
				this.aktBuchung.setTaetigkeiten( "" );

			}
		} else {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
		}
	}

	public void onAktivitätChange() throws Exception {
		if( this.aktBuchung.getProjekt().isGanztaegig() == true ) {
			this.aktBuchung.setAnfangZeit( new Date() );
			this.aktBuchung.setEndeZeit( new Date() );
			this.aktBuchung.setTaetigkeiten( "Test" );
		}

	}

	public void onRowEdit( RowEditEvent event ) throws Exception {
		DataTable s = (DataTable) event.getSource();
		Buchung b = (Buchung) s.getRowData();
		this.buchungService.updateBuchung( b );
		FacesMessage msg = new FacesMessage( "Buchung Edited", ( (Buchung) event.getObject() ).getId().toString() );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public Long findDurationAsLong() throws Exception {
		return this.buchungService.findDurationByDate( this.securityService.getSecurityPrincipalForLoggedInUser()
				.toUpperCase(), dateT );
	}

	public String findDurationByDate() throws Exception {
		Long t = this.buchungService.findDurationByDate( this.securityService.getSecurityPrincipalForLoggedInUser()
				.toUpperCase(), dateT );
		if( t == null ) {
			t = new Long( 0 );
		}
		int hours = (int) Math.floor( t / 60 );
		int minutes = (int) ( t % 60 );
		return String.format( "%d:%02d", hours, minutes );
	}

	@SuppressWarnings( "static-access" )
	public String calculateRest() throws Exception {
		Long t = this.buchungService.findDurationByDate( this.securityService.getSecurityPrincipalForLoggedInUser()
				.toUpperCase(), this.dateT );
		if( t == null ) {
			t = new Long( 0 );
		}
		Long rest = 480 - t;
		int hours = (int) Math.floor( rest / 60 );
		int minutes = (int) Math.abs( ( rest % 60 ) );
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
