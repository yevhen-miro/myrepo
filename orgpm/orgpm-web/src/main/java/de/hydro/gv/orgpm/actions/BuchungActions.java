package de.hydro.gv.orgpm.actions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.models.BuchungModel;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.BuchungService;
import de.hydro.gv.orgpm.services.ProjektService;

@ManagedBean
@ViewScoped
public class BuchungActions {

	private BuchungModel aktBuchung = new BuchungModel( new Buchung() );

	private String user; // user Id
	private Date date; // filter Date variable

	private ArrayList<BuchungModel> cachedBuchungList;
	private ArrayList<Projekt> cachedProjektList;
	private Collection<Aktivitaet> cachedAktivitaetenList;

	@Inject
	private BuchungService buchungService;

	@Inject
	private ProjektService projektService;

	@Inject
	private AktivitaetService aktivitaetService;

	public Date getDate() {
		return this.date;
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	public BuchungModel getAktBuchung() {
		return this.aktBuchung;
	}

	public void setAktBuchung( BuchungModel aktBuchung ) {
		this.aktBuchung = aktBuchung;
	}

	public Collection<BuchungModel> getAlleBuchungen() throws Exception {
		if( this.cachedBuchungList == null ) {
			this.cachedBuchungList = this.readAndConvertBuchungen();

		} else {

			this.cachedBuchungList = this.filterBuchungenByDate( this.date );
		}
		return this.cachedBuchungList;
	}

	public ArrayList<Projekt> getAlleProjekte() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = this.readAndConvertProjekte();
		}
		return this.cachedProjektList;
	}

	public Collection<Aktivitaet> getAktivitaetenByProjekt() throws Exception {
		ArrayList<Aktivitaet> ret;
		if( this.aktBuchung.getProjekt() == null ) {
			ret = new ArrayList<Aktivitaet>();
		} else {
			Collection<Aktivitaet> actEntities = this.aktivitaetService.getAktivitaetenByProjekt( this.aktBuchung
					.getProjekt() );
			ret = this.convertierenAktivitaeten( actEntities );
		}
		return ret;
	}

	public String getHydroId() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		System.out.println( principal.toString() );
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

	private ArrayList<BuchungModel> readAndConvertBuchungen() throws Exception {
		Collection<Buchung> buchungEntities = this.buchungService.getBuchungenByMitarbeiter( this.getHydroId().trim()
				.toUpperCase(), this.aktBuchung.getInitDate() );
		ArrayList<BuchungModel> buchungModel = this.convertierenBuchungen( buchungEntities );

		return buchungModel;
	}

	private ArrayList<BuchungModel> filterBuchungenByDate( Date date ) throws Exception {
		Collection<Buchung> buchungEntities = this.buchungService.getBuchungenByMitarbeiter( this.getHydroId().trim()
				.toUpperCase(), date );
		ArrayList<BuchungModel> buchungModel = this.convertierenBuchungen( buchungEntities );
		return buchungModel;
	}

	private ArrayList<BuchungModel> convertierenBuchungen( Collection<Buchung> buchungEntities ) {
		ArrayList<BuchungModel> buchungModel = new ArrayList<BuchungModel>();
		for ( Buchung buchungEntity : buchungEntities ) {
			buchungModel.add( new BuchungModel( buchungEntity ) );
		}
		return buchungModel;
	}

	public ArrayList<Projekt> readAndConvertProjekte() throws Exception {
		Collection<Projekt> projektEntities = this.projektService.getAlleProjekte();
		ArrayList<Projekt> projektModel = this.convertieren( projektEntities );

		return projektModel;
	}

	private ArrayList<Projekt> convertieren( Collection<Projekt> projektEntities ) {
		ArrayList<Projekt> projektModel = new ArrayList<Projekt>();
		for ( Projekt projektEntity : projektEntities ) {
			projektModel.add( new Projekt() );
		}
		return projektModel;
	}

	private ArrayList<Aktivitaet> convertierenAktivitaeten( Collection<Aktivitaet> aktivitaetEntities ) {
		ArrayList<Aktivitaet> aktivitaetModel = new ArrayList<Aktivitaet>();
		for ( Aktivitaet aktivitaetEntity : aktivitaetEntities ) {
			aktivitaetModel.add( new Aktivitaet() );
		}
		return aktivitaetModel;
	}

	public String saveBuchung() throws Exception {
		this.buchungService.addBuchung( this.aktBuchung.convertToEntity() );
		this.cachedBuchungList = null;
		this.aktBuchung = null;
		return "buchungen";
	}

	public String removeBuchung() throws Exception {
		this.resetCache();
		de.hydro.gv.orgpm.data.Buchung tempBuchung = new de.hydro.gv.orgpm.data.Buchung();
		tempBuchung.setId( this.aktBuchung.getId() );
		this.buchungService.deleteBuchung( tempBuchung );
		this.cachedBuchungList = null;
		return null;
	}

	public void resetCache() {
		this.cachedBuchungList = null;

	}

	@PostConstruct
	public void onProjektChange() throws Exception {
		if( this.aktBuchung.getProjekt() != null ) {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
			this.aktBuchung.setActivities( this.cachedAktivitaetenList );
		} else {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
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

}
