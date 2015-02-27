package de.hydro.gv.orgpm.aktionen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.util.Logger;
import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.model.Mitarbeiter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

//have to be CDI
//@ConversationScoped
// Conversation scope for the conversation similar to request scope
@RequestScoped
@Named
public class Actions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4333661147148923437L;

	@Inject
	private Logger logger;

	@Inject
	private Conversation conversation; // Interface from JEE Context and have to
										// be injected

	private Mitarbeiter mitarbeiter = new Mitarbeiter();
	
	@Inject
	private de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiterE = new de.hydro.gv.orgpm.data.Mitarbeiter();

	@Inject
	private MitarbeiterDao mitarbeiterDao;

	private ArrayList<Mitarbeiter> cachedMitarbeiterList;

	public String saveMitarbeiter() {
		mitarbeiterDao.createMitarbeiter(convertToEntity(mitarbeiter));
		return "mitarbeiter-overview.xhtml";
	}

	private de.hydro.gv.orgpm.data.Mitarbeiter convertToEntity(
			Mitarbeiter mitToConvert) {
		de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiterEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
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
		de.hydro.gv.orgpm.data.Mitarbeiter tempEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
		tempEntity.setId(mitarbeiter.getId());
		mitarbeiterDao.deleteMitarbeiter(tempEntity);
		cachedMitarbeiterList = null;
		logger.logMessage("remove mitarbeiter message");
		return null;
	}

	public String updateMitarbeiter() {
		// de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter
		// tempMitarbeiter = new
		// de.schellsoft.seminars.ee7.custimercare.ejb.Mitarbeiter();
		// tempMitarbeiter.setId(mitarbeiter.getId());
		// tempMitarbeiter.setVorname(mitarbeiter.getVorname());
		mitarbeiterDao.updateMitarbeiter(convertToEntity(mitarbeiter));
		cachedMitarbeiterList = null;
		return null;
	}

	public List<Mitarbeiter> getAllMitarbeiter() {
		if (cachedMitarbeiterList == null)
			cachedMitarbeiterList = readAndConvertMitarbeiter();
		return cachedMitarbeiterList;
	}

	private ArrayList<Mitarbeiter> readAndConvertMitarbeiter() {
		@SuppressWarnings("unchecked")
		List<de.hydro.gv.orgpm.data.Mitarbeiter> results = (List<de.hydro.gv.orgpm.data.Mitarbeiter>) mitarbeiterDao
				.executeQueryWithResults("mitarbeiter.find.all");
		ArrayList<Mitarbeiter> arrayList = new ArrayList<Mitarbeiter>();
		for (de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter : results)
			arrayList.add(convToModel(mitarbeiter));
		return arrayList;
	}

	private Mitarbeiter convToModel(
			de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter) {
		Mitarbeiter mitarbeiterModel = new Mitarbeiter();
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

	public String startConversation() { // Active methods must be of String type
										// and without parameters
		if (conversation.isTransient()) {
			conversation.begin();
		}
		; // begins your conversation
		return "customer-input-step2.xhtml";
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public void MitarbeiterOnRowEdit(RowEditEvent event) {
		updateMitarbeiter();
		FacesMessage msg = new FacesMessage("Mitarbeiter geändert",
				((Mitarbeiter) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void MitarbeiterOnRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Änderung abgebrochen",
				((Mitarbeiter) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String addNewMitarbeiter() {
		return "mitarbeiter-input.xhtml";
	}

}
