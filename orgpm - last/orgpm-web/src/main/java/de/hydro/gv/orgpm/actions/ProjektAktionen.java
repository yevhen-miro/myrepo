package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.models.ProjektModel;
import de.hydro.gv.orgpm.services.ProjektService;

@RequestScoped
@Named
public class ProjektAktionen {

	private ProjektModel projekt = new ProjektModel( new Projekt() );

	@Inject
	private ProjektService projektService;

	private ArrayList<ProjektModel> cachedProjektList;

	public ProjektModel getProjekt() {
		return this.projekt;
	}

	public void setProjekt( ProjektModel projekt ) {
		this.projekt = projekt;
	}

	public Collection<ProjektModel> getAlleProjekte() throws Exception {
		if( this.cachedProjektList == null ) {
			this.cachedProjektList = this.readAndConvertProjekt();
		}
		return this.cachedProjektList;
	}

	public ArrayList<ProjektModel> readAndConvertProjekt() throws Exception {
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

	public String addProjekt() throws Exception {
		this.projektService.addProjekt( this.projekt.convertToEntity() );
		this.cachedProjektList = null;
		return "projekte";
	}

	public String removeProjekt() throws Exception {
		de.hydro.gv.orgpm.data.Projekt tempEntity = new de.hydro.gv.orgpm.data.Projekt();
		tempEntity.setId( this.projekt.getId() );
		this.projektService.deleteProjekt( tempEntity );
		this.cachedProjektList = null;
		return null;
	}

	public String updateProjekt() throws Exception {
		this.projektService.updateProjekt( this.projekt.convertToEntity() );
		this.cachedProjektList = null;
		return "projekte";
	}

	public void ProjektOnRowEdit( RowEditEvent event ) throws Exception {

		this.projektService.updateProjekt( this.projekt.convertToEntity() );
		FacesMessage msg = new FacesMessage( "Projekt geändert", ( (ProjektModel) event.getObject() ).getProjektname() );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public void ProjektOnRowCancel( RowEditEvent event ) {
		FacesMessage msg = new FacesMessage( "Änderung abgebrochen",
				( (ProjektModel) event.getObject() ).getProjektname() );
		FacesContext.getCurrentInstance().addMessage( null, msg );
	}

	public String addNewProjekt() {
		return "projekt-input";
	}

	public Collection<ProjektModel> getAlle() throws Exception {
		Collection<Projekt> projektEntities = this.projektService.getAlleProjekte();
		for ( Projekt projektEntity : projektEntities ) {
			this.cachedProjektList.add( new ProjektModel( projektEntity ) );
		}
		return this.cachedProjektList;
	}

}
