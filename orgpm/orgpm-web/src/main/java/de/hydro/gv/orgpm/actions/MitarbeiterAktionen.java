package de.hydro.gv.orgpm.actions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.auth.RolleEnum;
import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.models.MitarbeiterModel;
import de.hydro.gv.orgpm.models.ProjektModel;
import de.hydro.gv.orgpm.models.RolleModel;
import de.hydro.gv.orgpm.services.MitarbeiterService;
import de.hydro.gv.orgpm.services.ProjektService;

@RolesAllowed( "ADMIN" )
@RequestScoped
@Named
public class MitarbeiterAktionen {

	private MitarbeiterModel mitarbeiter = new MitarbeiterModel( new Mitarbeiter() );;

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Inject
	private LoginAction loginAction;

	@Inject
	private RollenActions rollenActions;

	@Inject
	private ProjektService projektService;

	@Inject
	private ProjektAktionen projektAktionen;

	private RolleModel rolleModel;

	public RolleModel getRolleModel() {
		return this.rolleModel;
	}

	public void setRolleModel( RolleModel rolleModel ) {
		this.rolleModel = rolleModel;
	}

	private Boolean isAdmin;

	String neuesPasswort;

	private ArrayList<MitarbeiterModel> cachedMitarbeiterList;

	public MitarbeiterModel getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( MitarbeiterModel mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
	}

	public Boolean getIsAdmin() {
		return this.isMitarbeiterAdmin();
	}

	public void setIsAdmin( Boolean isAdmin ) {
		this.isAdmin = isAdmin;
	}

	public String getNeuesPasswort() {
		return this.neuesPasswort;
	}

	public void setNeuesPasswort( String neuesPasswort ) {
		this.neuesPasswort = neuesPasswort;
	}

	public Collection<MitarbeiterModel> getAlleMitarbeiter() throws Exception {
		if( this.cachedMitarbeiterList == null ) {
			this.cachedMitarbeiterList = this.readAndConvertMitarbeiter();
		}
		return this.cachedMitarbeiterList;
	}

	public ArrayList<MitarbeiterModel> readAndConvertMitarbeiter() throws Exception {
		Collection<Mitarbeiter> mitarbeiterEntities = this.mitarbeiterService.getAlleMitarbeiter();
		ArrayList<MitarbeiterModel> mitarbeiterModel = this.convertieren( mitarbeiterEntities );
		return mitarbeiterModel;
	}

	private ArrayList<MitarbeiterModel> convertieren( Collection<Mitarbeiter> mitarbeiterEntities ) {
		ArrayList<MitarbeiterModel> mitarbeiterModel = new ArrayList<MitarbeiterModel>();
		for ( Mitarbeiter mitarbeiterEntity : mitarbeiterEntities ) {
			mitarbeiterModel.add( new MitarbeiterModel( mitarbeiterEntity ) );
		}
		return mitarbeiterModel;
	}

	// private ArrayList<RolleEnum> convertierenRolle( Collection<RolleEnum>
	// rollenEntities ) {
	// ArrayList<RolleEnum> rolleModel = new ArrayList<RolleEnum>();
	// for ( RolleEnum rolleEntity : rollenEntities ) {
	// rolleModel.add( new RolleModel( rolleEntity ) );
	// }
	// return rolleModel;
	// }

	public String addNewMitarbeiter() {
		return "mitarbeiter-input";
	}

	public String addMitarbeiter() throws Exception {

		// if( this.mitarbeiterService.getRolleByHydroId(
		// this.mitarbeiter.getHydroid() ) == null ) {
		// this.mitarbeiter.setRolle( this.mitarbeiterService.getRolleById( 81L
		// ) );
		// } else {
		// this.mitarbeiter.setRolle( this.mitarbeiterService.getRolleByHydroId(
		// this.mitarbeiter.getHydroid() ) );
		// }
		//
		// Rolle rol = new Rolle();
		// rol.setId( 81L );
		// rol.setName( "USER" );
		// this.mitarbeiter.setRolle( this.mitarbeiterService.getRolleById( 81L
		// ) );
		// this.mitarbeiter.setRolle( rol );
		this.mitarbeiterService.addMitarbeiter( this.mitarbeiter.convertToEntity() );
		this.cachedMitarbeiterList = null;
		return "mitarbeiter";
	}

	public String removeMitarbeiter() throws Exception {
		de.hydro.gv.orgpm.data.Mitarbeiter tempEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
		tempEntity.setId( this.mitarbeiter.getId() );
		this.mitarbeiterService.deleteMitarbeiter( tempEntity );
		this.cachedMitarbeiterList = null;
		return null;
	}

	public String updateMitarbeiter() throws Exception {
		this.mitarbeiterService.updateMitarbeiter( this.mitarbeiter.convertToEntity() );
		this.cachedMitarbeiterList = null;
		return "mitarbeiter";
	}

	public Boolean isMitarbeiterAdmin() {
		if( this.mitarbeiterService.getMitarbeiterRolleByHydroId( this.mitarbeiter.getHydroid() ) == "ADMIN" ) {
			return true;
		}
		return false;
	}

	public Collection<ProjektModel> getProjekteByMitarbeiter() {
		return this
				.convertierenProjekte( this.projektService.getProjekteByMitarbeiter( this.mitarbeiter.getHydroid() ) );
	}

	private ArrayList<ProjektModel> convertierenProjekte( Collection<Projekt> projekteEntities ) {
		ArrayList<ProjektModel> projektModel = new ArrayList<ProjektModel>();
		for ( Projekt projekt : projekteEntities ) {
			projektModel.add( new ProjektModel( projekt ) );
		}

		return projektModel;
	}

	public MitarbeiterModel getMitarbeiterByHydroId() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		String hydroid = principal.getName().toUpperCase();
		this.mitarbeiterService.getMitarbeiterByHydroId( hydroid );
		MitarbeiterModel mitarbeiter = new MitarbeiterModel( this.mitarbeiterService.getMitarbeiterByHydroId( hydroid ) );
		return mitarbeiter;
	}

	public String updatePasswort() throws Exception {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		String hydroid = principal.getName().toUpperCase();
		this.mitarbeiterService.getMitarbeiterByHydroId( hydroid );
		MitarbeiterModel ma = new MitarbeiterModel( this.mitarbeiterService.getMitarbeiterByHydroId( hydroid ) );
		ma.setPasswort( this.getNeuesPasswort() );
		this.mitarbeiterService.updateMitarbeiter( ma.convertToEntity() );
		this.cachedMitarbeiterList = null;
		this.loginAction.logout();
		return "/login.xhtml?faces-redirect=true";
	}

	// public Login getLoginByHydroId() {
	// Principal principal =
	// FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	// String hydroid = principal.getName().toUpperCase();
	// return this.mitarbeiterService.getLoginByHydroId( hydroid );
	// }

	public RolleEnum[] getAlleRollen() throws Exception {
		return this.rollenActions.getAlle();
	}

	// private ArrayList<RolleEnum> readAndConvertRollen() {
	// Collection<RolleEnum> rollenEntities =
	// this.mitarbeiterService.getAlleRollen();
	// ArrayList<RolleModel> rolleModel = this.convertierenRolle( rollenEntities
	// );
	// return rolleModel;
	// }

}
