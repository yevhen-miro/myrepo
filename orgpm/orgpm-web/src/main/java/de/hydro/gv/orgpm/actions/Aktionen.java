package de.hydro.gv.orgpm.aktionen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.util.Logger;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.model.MitarbeiterModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

//have to be CDI
//@ConversationScoped
// Conversation scope for the conversation similar to request scope
@RequestScoped
@Named
public class Aktionen implements Serializable {

	private static final long serialVersionUID = 2348235239482394238L;

	@Inject
	private Logger logger;

	private MitarbeiterModel mitarbeiterModel = new MitarbeiterModel();
	
	@Inject
	private MitarbeiterDao mitarbeiterDao;

	private ArrayList<MitarbeiterModel> cachedMitarbeiterList;

	public String saveMitarbeiter() {
		mitarbeiterDao.createMitarbeiter(convertToEntity(mitarbeiterModel));
		return "mitarbeiter-overview.xhtml";
	}

	private de.hydro.gv.orgpm.Entities.Mitarbeiter convertToEntity(
			MitarbeiterModel mitToConvert) {
		de.hydro.gv.orgpm.Entities.Mitarbeiter mitarbeiterEntity = new de.hydro.gv.orgpm.Entities.Mitarbeiter();
		mitarbeiterEntity.setVorname(mitToConvert.getVorname());
		mitarbeiterEntity.setName(mitToConvert.getName());
		mitarbeiterEntity.setArbeitszeit(mitToConvert.getArbeitszeit());
		mitarbeiterEntity.setBemerkung(mitToConvert.getBemerkung());
		mitarbeiterEntity.setgruppe(mitToConvert.getGruppe());
		mitarbeiterEntity.setHydroId(mitToConvert.getHydroid());
		mitarbeiterEntity.setKartenNum(mitToConvert.getKartenid());
		mitarbeiterEntity.setMitarbeiterkennung(mitToConvert.getKennung());
		mitarbeiterEntity.setMitarbeiterStatus(mitToConvert.getStatus());
		mitarbeiterEntity.setPersonalNum(mitToConvert.getPersonalid());
		return mitarbeiterEntity;
	}

	public String removeMitarbeiter() { // have to be of String type, without
		// parameters and return result
		de.hydro.gv.orgpm.Entities.Mitarbeiter tempEntity = new de.hydro.gv.orgpm.Entities.Mitarbeiter();
		tempEntity.setId(mitarbeiterModel.getId());
		mitarbeiterDao.deleteMitarbeiter(tempEntity);
		cachedMitarbeiterList = null;
		logger.logMessage("remove mitarbeiter message");
		return null;
	}

	public String updateMitarbeiter() {
		//de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter tempMitarbeiter = new de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter();
		//tempMitarbeiter.setId(mitarbeiter.getId());
		//tempMitarbeiter.setVorname(mitarbeiter.getVorname());
		mitarbeiterDao.updateMitarbeiter(convertToEntity(mitarbeiterModel));
		cachedMitarbeiterList = null;
		return null;
	}

	public List<MitarbeiterModel> getAllMitarbeiter() {
		if (cachedMitarbeiterList == null)
			cachedMitarbeiterList = readAndConvertMitarbeiter();
		return cachedMitarbeiterList;
	}

	private ArrayList<MitarbeiterModel> readAndConvertMitarbeiter() {
		@SuppressWarnings("unchecked")
		List<de.hydro.gv.orgpm.Entities.Mitarbeiter> results = (List<de.hydro.gv.orgpm.Entities.Mitarbeiter>) mitarbeiterDao
				.executeQueryWithResults("mitarbeiter.find.all");
		ArrayList<MitarbeiterModel> arrayList = new ArrayList<MitarbeiterModel>();
		for (de.hydro.gv.orgpm.Entities.Mitarbeiter mitarbeiter : results)
			arrayList.add(convToModel(mitarbeiter));
		return arrayList;
	}

	private MitarbeiterModel convToModel(
			de.hydro.gv.orgpm.Entities.Mitarbeiter mitarbeiter) {
		MitarbeiterModel mitarbeiterModel = new MitarbeiterModel();
		mitarbeiterModel.setId(mitarbeiter.getId());
		mitarbeiterModel.setName(mitarbeiter.getName());
		mitarbeiterModel.setVorname(mitarbeiter.getVorname());
		mitarbeiterModel.setArbeitszeit(mitarbeiter.getArbeitszeit());
		mitarbeiterModel.setBemerkung(mitarbeiter.getBemerkung());
		mitarbeiterModel.setGruppe(mitarbeiter.getgruppe());
		mitarbeiterModel.setHydroid(mitarbeiter.getHydroId());
		mitarbeiterModel.setKartenid(mitarbeiter.getKartenNum());
		mitarbeiterModel.setKennung(mitarbeiter.getMitarbeiterkennung());
		mitarbeiterModel.setStatus(mitarbeiter.getMitarbeiterStatus());
		mitarbeiterModel.setPersonalid(mitarbeiter.getPersonalNum());

		return mitarbeiterModel;
	}

	public MitarbeiterModel getMitarbeiter() {
		return mitarbeiterModel;
	}
	public void setMitarbeiter(MitarbeiterModel mitarbeiter) {
		this.mitarbeiterModel = mitarbeiter;
	}
	
	
    public void MitarbeiterOnRowEdit(RowEditEvent event) {
    	updateMitarbeiter();
        FacesMessage msg = new FacesMessage("Mitarbeiter geändert", ((MitarbeiterModel) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void MitarbeiterOnRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Änderung abgebrochen", ((MitarbeiterModel) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String addNewMitarbeiter() {
		return "mitarbeiter-input.xhtml";
	}

}
