package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import de.hydro.gv.orgpm.dao.BuchungDaoLocal;
import de.hydro.gv.orgpm.models.Buchung;
import de.hydro.gv.orgpm.models.Mitarbeiter;
import de.hydro.gv.orgpm.services.BuchungService;

@RequestScoped
@Named
public class BuchungAktionen {
	
	private Buchung buchung = new Buchung();
	
	private ArrayList<Buchung> cachedBuchungList;
	
	@Inject
	private BuchungService buchungService;
	

	
	public Buchung getBuchung() {
		return buchung;
	}
	
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}
	
	public Collection <Buchung> getAlleBuchungen() throws Exception {
		if (cachedBuchungList == null)
			cachedBuchungList = readAndConvertBuchungen();
		return cachedBuchungList;
	}
	
	private ArrayList <Buchung> readAndConvertBuchungen() throws Exception {
		Collection <de.hydro.gv.orgpm.data.Buchung> buchungEntities = this.buchungService.getAlleBuchungen();
		ArrayList<Buchung> buchungModel = new ArrayList<Buchung>();
		for (de.hydro.gv.orgpm.data.Buchung buchungEntity : buchungEntities)
			buchungModel.add(new Buchung ().convertToModel(buchungEntity));
		return buchungModel;
	}
		
	public String saveBuchung() throws Exception {
		buchungService.addBuchung(buchung.convertToEntity(buchung));
		cachedBuchungList = null;
		return "buchungen";
	}
	
	public String updateBuchung() throws Exception {
		buchungService.updateBuchung(buchung.convertToEntity(buchung));
		cachedBuchungList = null;
		return "buchungen";
	}
	
	public String removeBuchung() throws Exception {
		de.hydro.gv.orgpm.data.Buchung tempBuchung = new de.hydro.gv.orgpm.data.Buchung();
		tempBuchung.setId(buchung.getId());
		buchungService.deleteBuchung(tempBuchung);
		cachedBuchungList = null;
		return null;
	}
	

	

	
	

	
    public void BuchungOnRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Buchung geändert", ((Buchung) event.getObject()).getProjektId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void BuchungOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((Buchung) event.getObject()).getProjektId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
	public String addNewBuchung() {
		return "buchungen-input.xhtml";
	}

}
