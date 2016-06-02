package de.hydro.gv.mplus.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.utils.ActivityType;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("ActivityTypeConverter")
public class ActivityTypeConverter implements Converter{
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		return id;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		

		int aType = (int) value;
		
		String type = null;
		
		switch (aType) {
        
        case 0:  type = ActivityType.NEW.getActivityType();
        break;
        
        case 1:  type = ActivityType.APPROVE_REQUEST.getActivityType();
        break;
        
        case 2:  type = ActivityType.APPROVED.getActivityType();
        break;
        
        case 3:  type = ActivityType.FINALIZED.getActivityType();
        break;
        
        case 4:  type = ActivityType.DELETE_REQUEST.getActivityType();
        break;
        
        case 5:  type = ActivityType.DELETED.getActivityType();
        break;
        
        case 6:  type = ActivityType.UNFINALIZED.getActivityType();
        break;
        
        case 7:  type = ActivityType.FILE_UPLOAD.getActivityType();
        break;
        
        case 9:  type = ActivityType.FINALIZE_REQUEST.getActivityType();
        break;
        
        case 11:  type = ActivityType.CANCEL_REQUEST.getActivityType();
        break;
        
        case 12:  type = ActivityType.CHANGED.getActivityType();
        break;
        
		}
		return type;
	}

}
