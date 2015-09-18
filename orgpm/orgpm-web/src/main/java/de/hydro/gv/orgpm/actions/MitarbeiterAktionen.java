package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.models.MitarbeiterModel;
import de.hydro.gv.orgpm.services.MitarbeiterService;

@RequestScoped
@Named
public class MitarbeiterAktionen {

	private MitarbeiterModel mitarbeiter = new MitarbeiterModel( new Mitarbeiter() );;

	@Inject
	private MitarbeiterService mitarbeiterService;

	private ArrayList<MitarbeiterModel> cachedMitarbeiterList;

	public MitarbeiterModel getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( MitarbeiterModel mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
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

	public String addNewMitarbeiter() {
		return "mitarbeiter-input";
	}

	public String addMitarbeiter() throws Exception {
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

}
