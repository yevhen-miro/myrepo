package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import de.hydro.gv.orgpm.models.Projekt;
import de.hydro.gv.orgpm.services.ProjektService;

@RequestScoped
@Named
public class ProjektAktionen {
	
	private Projekt projekt = new Projekt();
	
	@Inject
	private ProjektService projektService;
	
	private ArrayList<Projekt> cachedProjektList;
	

	public Projekt getProjekt() {
		return projekt;
	}
	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}
	
	public Collection<Projekt> getAlleProjekte() throws Exception {
		if (this.cachedProjektList == null)
			this.cachedProjektList = this.readAndConvertProjekt();
		return cachedProjektList;
	}
	

	public ArrayList<Projekt> readAndConvertProjekt() throws Exception {
		Collection<de.hydro.gv.orgpm.data.Projekt> ProjektEntities = this.projektService.getAlleProjekte();
		ArrayList<Projekt> ProjektModel = new ArrayList<Projekt>();
		for ( de.hydro.gv.orgpm.data.Projekt ProjektEntity : ProjektEntities )
			ProjektModel.add( new Projekt().convertToModel( ProjektEntity ) );
		return ProjektModel;

	}
	
	public String addProjekt() throws Exception {
		projektService.addProjekt(projekt.convertToEntity(this.projekt));
		cachedProjektList = null;
		return "projekte";
	}
	
	public String removeProjekt() throws Exception { 
		de.hydro.gv.orgpm.data.Projekt tempEntity = new de.hydro.gv.orgpm.data.Projekt();
		tempEntity.setId(projekt.getId());
		projektService.deleteProjekt(tempEntity);
		cachedProjektList = null;
		return null;
	}

	public String updateProjekt() throws Exception {
		projektService.updateProjekt(projekt.convertToEntity(projekt));
		cachedProjektList = null;
		return "projekte";
	}


    public void ProjektOnRowEdit(RowEditEvent event) throws Exception {
    	
    	projektService.updateProjekt(projekt.convertToEntity(projekt));
        FacesMessage msg = new FacesMessage("Projekt geändert", ((Projekt) event.getObject()).getProjektname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void ProjektOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((Projekt) event.getObject()).getProjektname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
	public String addNewProjekt() {
		return "projekt-input";
	}

}
