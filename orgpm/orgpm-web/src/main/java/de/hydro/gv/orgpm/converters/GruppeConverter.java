package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.aktionen.Actions;
import de.hydro.gv.orgpm.model.Mitarbeiter;

@FacesConverter(value = "GruppeConverter")
public class GruppeConverter implements Converter {
	
	@Inject
	private Actions actions;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Integer id = Integer.parseInt(arg2);
		for (Mitarbeiter tempMA : actions.getAllMitarbeiter())
			if(tempMA.getId().equals(id)) return tempMA;
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Mitarbeiter mitarbeiter = (Mitarbeiter) arg2;
		
		return mitarbeiter.getId().toString();
	}


}
