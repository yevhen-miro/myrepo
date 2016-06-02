package de.hydro.gv.mplus.converters;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.mplus.actions.ContractActions;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.services.CBUService;
import de.hydro.gv.mplus.services.SystemUserService;


@FacesConverter("UserConverter")
@RequestScoped
public class UserConverter implements Converter {

	@Inject
	private SystemUserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String id = "";
		try {
			//id = Long.parseLong( value );
			id = value;
		} catch ( NumberFormatException e ) {
			return null;
		}

	SystemUser u = this.userService.findUserById(id);
	System.out.println(u);

		return u;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		SystemUser u = (SystemUser) value;
		if( u == null ) {
			return null;
		}

		if( !( u instanceof SystemUser ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		String id = ( (SystemUser) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
