package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import de.hydro.gv.orgpm.utils.TimingService;

@ManagedBean
@ViewScoped
public class BuchungActions implements Serializable {
	private static final long serialVersionUID = 8776872386309781977L;

	private Buchung aktBuchung = new Buchung();

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
		try {
			this.buchungService.isTimeViolated( this.getHydroId().toUpperCase(), this.aktBuchung.getDatum(),
					this.aktBuchung.getAnfangZeit(), this.aktBuchung.getEndeZeit() );
		} catch ( InvalidDateException e ) {
			String message = "Your date is not valid: " + e.getMessage();
			FacesContext.getCurrentInstance().addMessage( null,
					new FacesMessage( FacesMessage.SEVERITY_ERROR, message, null ) );
			e.printStackTrace(); // Or use a logger.
			return "buchungen";
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
		this.buchungService.addBuchung( this.aktBuchung );
		this.cachedBuchungList = null;
		this.aktBuchung = null;
		return "buchungen";
	}

	public String removeBuchung() throws Exception {
		this.buchungService.deleteBuchung( this.aktBuchung );
		this.clearSessionCache();
		return null;
	}

	public String updateBuchung() throws Exception {
		this.buchungService.updateBuchung( this.aktBuchung );
		this.cachedBuchungList = null;
		return "projekte";
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
			this.aktBuchung.setProjekt( collection.get( 0 ) );
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
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat( "dd.MM.yyyy" );
		Date filterDate = (Date) event.getObject();
		facesContext.addMessage( null,
				new FacesMessage( FacesMessage.SEVERITY_INFO, "Date Selected", format.format( event.getObject() ) ) );
		facesContext.addMessage( null, new FacesMessage( "Date selected" ) );
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

}
