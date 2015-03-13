package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@FacesConverter( "mitarbeiterTypStringConverter" )
public class MitarbeiterTypStringConverter implements Converter{
	
	@Inject
	private MitarbeiterDao mitarbeiterDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Mitarbeiter mitarbeiter = (Mitarbeiter) value;
		return mitarbeiter.getMitarbeiterkennung();
	}
	
	

}
