package de.hydro.gv.orgpm.actions;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.BuchungService;
import de.hydro.gv.orgpm.services.MitarbeiterService;

@ManagedBean
@ViewScoped
public class BuchungActions implements Serializable {
	private static final long serialVersionUID = 8776872386309781977L;

	private Buchung aktBuchung = new Buchung();

	@Inject
	private BuchungService buchungService;

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	private AktivitaetService aktivitaetService;

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

	public String saveBuchung() throws Exception {
		this.aktBuchung.setMitarbeiter( this.mitarbeiterService.getMitarbeiterByHydroId( this.getHydroId()
				.toUpperCase() ) );
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
			ret = null;
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
	private void clearSessionCache() {
		this.cachedBuchungList = null;
		this.cachedAktivitaetenList = null;
		this.aktBuchung.setId( null );
		this.aktBuchung.setTaetigkeiten( "" );
		this.aktBuchung.setAnfangZeit( null );
		this.aktBuchung.setEndeZeit( null );
		this.aktBuchung.setDatum( null );
		this.aktBuchung.setMin( 0L );
		this.aktBuchung.setStd( 0L );
		this.aktBuchung.setPauseVon( null );
		this.aktBuchung.setPauseBis( null );
		this.aktBuchung.setProjekt( null );
	}

}
