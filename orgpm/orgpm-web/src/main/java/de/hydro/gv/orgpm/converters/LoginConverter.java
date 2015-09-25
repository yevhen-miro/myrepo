package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import de.hydro.gv.orgpm.auth.Login;

@FacesConverter( "loginConverter" )
public class LoginConverter implements Converter {

	@Override
	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			Login login = new Login();
			if( login.getId().equals( id ) ) {
				System.out.println( "Converted to Login: " + login.getId() );
				return login;
			}
		} catch ( Exception e ) {
			return null;
		}
		return null;
	}

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		// RolleModel rolle = (RolleModel) value;
		if( value == null ) {
			return null;
		}

		if( !( value instanceof Login ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (Login) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}

// @Override
// public String getAsString( FacesContext context, UIComponent component,
// Object value ) {
// Projekt projekt = (Projekt) value;
// if( projekt == null ) {
// return null;
// }
//
// return projekt.getId().toString();
// }

