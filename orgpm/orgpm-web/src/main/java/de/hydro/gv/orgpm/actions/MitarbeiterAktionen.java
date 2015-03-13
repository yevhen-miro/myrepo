package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import de.hydro.gv.orgpm.models.Mitarbeiter;
import de.hydro.gv.orgpm.services.MitarbeiterService;

@RequestScoped
@Named
public class MitarbeiterAktionen {
	
	private Mitarbeiter mitarbeiter = new Mitarbeiter();
	
	@Inject
	private MitarbeiterService mitarbeiterService;
	
	private ArrayList<Mitarbeiter> cachedMitarbeiterList;
	

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	public Collection<Mitarbeiter> getAlleMitarbeiter() throws Exception {
		if (this.cachedMitarbeiterList == null)
			this.cachedMitarbeiterList = this.readAndConvertMitarbeiter();
		return cachedMitarbeiterList;
	}
	

	public ArrayList<Mitarbeiter> readAndConvertMitarbeiter() throws Exception {
		Collection<de.hydro.gv.orgpm.data.Mitarbeiter> mitarbeiterEntities = this.mitarbeiterService.getAlleMitarbeiter();
		ArrayList<Mitarbeiter> mitarbeiterModel = new ArrayList<Mitarbeiter>();
		for ( de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiterEntity : mitarbeiterEntities )
			mitarbeiterModel.add( new Mitarbeiter().convertToModel( mitarbeiterEntity ) );
		return mitarbeiterModel;

	}
	
	public String addMitarbeiter() throws Exception {
		mitarbeiterService.addMitarbeiter(mitarbeiter.convertToEntity(this.mitarbeiter));
		cachedMitarbeiterList = null;
		return "mitarbeiter";
	}
	
	public String removeMitarbeiter() throws Exception { 
		de.hydro.gv.orgpm.data.Mitarbeiter tempEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
		tempEntity.setId(mitarbeiter.getId());
		mitarbeiterService.deleteMitarbeiter(tempEntity);
		cachedMitarbeiterList = null;
		return null;
	}

	public String updateMitarbeiter() throws Exception {
		mitarbeiterService.updateMitarbeiter(mitarbeiter.convertToEntity(mitarbeiter));
		cachedMitarbeiterList = null;
		return "mitarbeiter";
	}


    public void MitarbeiterOnRowEdit(RowEditEvent event) throws Exception {
    	
    	mitarbeiterService.updateMitarbeiter(mitarbeiter.convertToEntity(mitarbeiter));
        FacesMessage msg = new FacesMessage("Mitarbeiter geändert", ((Mitarbeiter) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void MitarbeiterOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((Mitarbeiter) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
	public String addNewMitarbeiter() {
		return "mitarbeiter-input";
	}

}
