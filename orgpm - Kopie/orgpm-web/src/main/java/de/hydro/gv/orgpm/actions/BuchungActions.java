package de.hydro.gv.orgpm.actions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.models.AktivitaetModel;
import de.hydro.gv.orgpm.models.BuchungModel;
import de.hydro.gv.orgpm.models.MitarbeiterModel;
import de.hydro.gv.orgpm.models.ProjektModel;
import de.hydro.gv.orgpm.services.AktivitaetService;
import de.hydro.gv.orgpm.services.BuchungService;
import de.hydro.gv.orgpm.services.MitarbeiterService;
import de.hydro.gv.orgpm.services.ProjektService;

@ManagedBean
@ViewScoped
public class BuchungActions {

	private BuchungModel aktBuchung = new BuchungModel( new Buchung() );

	private MitarbeiterModel selectedMitarbeiter;
	private ProjektModel selectedProjekt;
	private AktivitaetModel selectedAktivitaet;
	private String user;

	private List<BuchungModel> selectedBuchungen;
	private Date date;

	private ArrayList<BuchungModel> cachedBuchungList;
	private ArrayList<ProjektModel> cachedProjektList;
	private ArrayList<MitarbeiterModel> cachedMitarbeiterList;
	private Collection<AktivitaetModel> cachedAktivitaetenList;

	@Inject
	private BuchungService buchungService;

	@Inject
	private SecurityActions securityActions;

	@Inject
	private ProjektService projektService;

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	private AktivitaetService aktivitaetService;

	@Inject
	private MitarbeiterAktionen mitarbeiterAktionen;

	@Inject
	private AktivitaetAktionen aktivitaetAktionen;

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

	public MitarbeiterModel getAktMitarbeiter() {
		return this.selectedMitarbeiter;
	}

	public void setAktMitarbeiter( MitarbeiterModel aktMitarbeiter ) {
		this.selectedMitarbeiter = aktMitarbeiter;
	}

	public ProjektModel getSelectedProjekt() {
		return this.selectedProjekt;
	}

	public void setSelectedProjekt( ProjektModel selectedProjekt ) {
		this.selectedProjekt = selectedProjekt;
	}

	public AktivitaetModel getSelectedAktivitaet() {
		return this.selectedAktivitaet;
	}

	public void setSelectedAktivitaet( AktivitaetModel selectedAktivitaet ) {
		this.selectedAktivitaet = selectedAktivitaet;
	}

	public List<BuchungModel> getSelectedBuchungen() {
		return this.selectedBuchungen;
	}

	public void setSelectedBuchungen( List<BuchungModel> selectedBuchungen ) {
		this.selectedBuchungen = selectedBuchungen;
	}

	public Collection<BuchungModel> getAlleBuchungen() throws Exception {
		if( this.cachedBuchungList == null ) {
			this.cachedBuchungList = this.readAndConvertBuchungen();

		} else {

			this.cachedBuchungList = this.filterBuchungenByDate( this.date );
		}
		return this.cachedBuchungList;
	}

	public Collection<BuchungModel> getAlleByDate( Date date ) throws Exception {
		if( this.cachedBuchungList == null ) {
			this.cachedBuchungList = this.filterBuchungenByDate( date );
		}
		return this.cachedBuchungList;
	}

	public Collection<ProjektModel> readAndConvertAlleProjekte() throws Exception {
		Collection<Projekt> projektEntities = this.projektService.getAlleProjekte();
		ArrayList<ProjektModel> projektModel = this.convertieren( projektEntities );
		return projektModel;
	}

	public Collection<ProjektModel> getAlle() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = this.readAndConvertProjekte();
		}
		return this.cachedProjektList;
	}

	public Collection<AktivitaetModel> getAlleAktivitaeten() throws Exception {
		if( this.cachedAktivitaetenList == null ) {
			if( this.selectedProjekt == null && this.selectedAktivitaet == null ) {
				this.cachedAktivitaetenList = this.readAndConvertAktivitaeten();
			}
			this.cachedAktivitaetenList = this.readAndConvertAktivitaeten();
		} else if( this.selectedProjekt != null && this.selectedAktivitaet == null ) {
			this.getAktivitaetenByProjekt();
		} else {
			this.cachedAktivitaetenList = this.getAktivitaetenByProjekt();
		}
		return this.cachedAktivitaetenList;
	}

	public ArrayList<MitarbeiterModel> getAlleMitarbeiter() throws Exception {
		if( this.cachedMitarbeiterList == null ) {
			this.cachedMitarbeiterList = this.mitarbeiterAktionen.readAndConvertMitarbeiter();
		}
		return this.cachedMitarbeiterList;
	}

	public ArrayList<ProjektModel> getAlleProjekte() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = this.readAndConvertProjekte();
		}
		return this.cachedProjektList;
	}

	public Collection<AktivitaetModel> getAlleProjektAktivitaeten() throws Exception {
		if( this.cachedAktivitaetenList == null ) {
			if( this.selectedProjekt == null && this.selectedAktivitaet == null ) {
				this.cachedAktivitaetenList = this.readAndConvertAktivitaeten();
			} else if( this.selectedProjekt != null && this.selectedAktivitaet == null ) {
				this.getAktivitaetenByProjekt();
			} else {
				this.cachedAktivitaetenList = this.readAndConvertAktivitaeten();
			}
		}
		return this.cachedAktivitaetenList;
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

	@SuppressWarnings( "unused" )
	public HashMap<String, Long> getDurationByMitarbeiter() throws Exception {
		return this.buchungService.getDauerByMitarbeiter( this.getHydroId().trim().toUpperCase() );

	}

	private ArrayList<BuchungModel> convertierenBuchungen( Collection<Buchung> buchungEntities ) {
		ArrayList<BuchungModel> buchungModel = new ArrayList<BuchungModel>();
		for ( Buchung buchungEntity : buchungEntities ) {
			buchungModel.add( new BuchungModel( buchungEntity ) );
		}
		return buchungModel;
	}

	// private ArrayList<AktivitaetModel> readAndConvertActivities() throws
	// Exception {
	// Collection<Aktivitaet> actEntities =
	// this.aktivitaetService.getAktivitaetenByProjektName( this.aktProjekt
	// .getProjektname() );
	// ArrayList<AktivitaetModel> actModel = new ArrayList<AktivitaetModel>();
	// for ( Aktivitaet actEntity : actEntities ) {
	// actModel.add( new AktivitaetModel().convertToEntity() );
	// }
	// return actModel;
	// }

	// public Collection<Projekt> readAndConvertEProjekte() throws Exception {
	// Collection<Projekt> projektEntities =
	// this.projektService.getAlleProjekte();
	// ArrayList<ProjektModel> projektModels = new ArrayList<ProjektModel>();
	//
	// for ( ProjektModel projektModel : projektModels ) {
	// projektEntities.add( new ProjektModel().convertToEntity( projektModel )
	// );
	// }
	// return projektEntities;
	// }

	public ArrayList<ProjektModel> readAndConvertProjekte() throws Exception {
		Collection<Projekt> projektEntities = this.projektService.getAlleProjekte();
		ArrayList<ProjektModel> projektModel = this.convertieren( projektEntities );

		return projektModel;
	}

	private ArrayList<ProjektModel> convertieren( Collection<Projekt> projektEntities ) {
		ArrayList<ProjektModel> projektModel = new ArrayList<ProjektModel>();
		for ( Projekt projektEntity : projektEntities ) {
			projektModel.add( new ProjektModel( projektEntity ) );
		}
		return projektModel;
	}

	public Collection<AktivitaetModel> readAndConvertAktivitaeten() throws Exception {

		Collection<Aktivitaet> aktivitaetEntities = this.aktivitaetService.getAlleAktivitaet();
		ArrayList<AktivitaetModel> aktivitaetModel = new ArrayList<AktivitaetModel>();
		for ( Aktivitaet aktivitaetEntity : aktivitaetEntities ) {
			aktivitaetModel.add( new AktivitaetModel( aktivitaetEntity ) );
		}

		return aktivitaetModel;
	}

	public de.hydro.gv.orgpm.models.ProjektModel convertProjektToModel() {
		Projekt projektEntity = new Projekt();
		ProjektModel projektModel = new ProjektModel( projektEntity );
		return projektModel;
	}

	private ArrayList<AktivitaetModel> convertierenAktivitaeten( Collection<Aktivitaet> aktivitaetEntities ) {
		ArrayList<AktivitaetModel> aktivitaetModel = new ArrayList<AktivitaetModel>();
		for ( Aktivitaet aktivitaetEntity : aktivitaetEntities ) {
			aktivitaetModel.add( new AktivitaetModel( aktivitaetEntity ) );
		}
		return aktivitaetModel;
	}

	public String saveBuchung() throws Exception {
		this.buchungService.addBuchung( this.aktBuchung.convertToEntity() );
		this.cachedBuchungList = null;
		this.aktBuchung = null;
		return "buchungen";
	}

	public String updateBuchung() throws Exception {
		this.buchungService.updateBuchung( this.aktBuchung.convertToEntity() );
		this.cachedBuchungList = null;
		return "buchungen";
	}

	public String removeBuchung() throws Exception {
		de.hydro.gv.orgpm.data.Buchung tempBuchung = new de.hydro.gv.orgpm.data.Buchung();
		tempBuchung.setId( this.aktBuchung.getId() );
		this.buchungService.deleteBuchung( tempBuchung );
		this.cachedBuchungList = null;
		return null;
	}

	public void loadActivities( final AjaxBehaviorEvent event ) throws Exception {
		this.aktivitaetService.getAktivitaetenByProjektName( this.selectedProjekt.getProjekt() );
	}

	public Collection<Aktivitaet> getProjektAktivitaeten() throws Exception {
		if( this.cachedAktivitaetenList == null ) {
			this.cachedAktivitaetenList = this.readAndConvertAktivitaeten();
		}
		return this.aktivitaetService.getAktivitaetenByProjektid( this.selectedProjekt.getId() );
	}

	public String addNewBuchung() {
		return "buchungen-input.xhtml";
	}

	public void resetCache() {
		this.cachedProjektList = null;

	}

	public Collection<AktivitaetModel> getAktivitaetenByProjekt() throws Exception {
		if( this.aktBuchung.getProjekt() == null ) {
			return new ArrayList<AktivitaetModel>();
			// } else if( this.selectedProjekt != null ) {
			// Collection<Aktivitaet> actEntities =
			// this.aktivitaetService.getAktivitaetenByProjekt(
			// this.selectedProjekt
			// .convertToEntity() );
			//
			// ArrayList<AktivitaetModel> actModel =
			// this.convertierenAktivitaeten( actEntities );
			// return actModel;
		} else {
			Collection<Aktivitaet> actEntities = this.aktivitaetService.getAktivitaetenByProjekt( this.aktBuchung
					.getProjekt().convertToEntity() );

			ArrayList<AktivitaetModel> actModel = this.convertierenAktivitaeten( actEntities );
			return actModel;
		}

	}

	public Mitarbeiter getMitarbeiterByHydroId( String hydroid ) {
		return this.mitarbeiterService.getMitarbeiterByHydroId( hydroid );
	}

	public String getHydroId() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		System.out.println( principal.toString() );
		return principal != null ? principal.toString() : "UNAUTHORIZED";
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

	public void onRowEdit( RowEditEvent event ) throws Exception {

		UIData table = (UIData) event.getComponent();
		String updateClientId = table.getClientId() + ":" + table.getRowIndex() + "";
		FacesMessage msg = new FacesMessage( "Buchung " + this.aktBuchung.getId() + "geändert" );
		FacesContext.getCurrentInstance().addMessage( null, msg );
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add( updateClientId );

	}

	public void onRowCancel( RowEditEvent event ) {
		FacesMessage msg = new FacesMessage( "Änderung" + this.aktBuchung.getId() + "cancelled" );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

}
