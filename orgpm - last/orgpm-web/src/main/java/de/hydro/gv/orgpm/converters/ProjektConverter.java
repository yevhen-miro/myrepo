package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.models.ProjektModel;
import de.hydro.gv.orgpm.services.ProjektService;

@FacesConverter( "projektConverter" )
public class ProjektConverter implements Converter {

	@Inject
	private ProjektService projektService;

	@Override
	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( de.hydro.gv.orgpm.data.Projekt projekt : this.projektService.getAlleProjekte() ) {
				if( projekt.getId().equals( id ) ) {
					System.out.println( "Converted to Projekt: " + projekt.getProjektId() );
					return new ProjektModel( projekt );
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		ProjektModel projekt = (ProjektModel) value;
		if( value == null ) {
			return null;
		}

		if( !( value instanceof ProjektModel ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (ProjektModel) value ).getId();

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

