package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.services.MitarbeiterService;

@FacesConverter( "mitarbeiterConverter" )
public class MitarbeiterConverter implements Converter {

	@Inject
	private MitarbeiterService mitarbeiterService;

	@Override
	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( Mitarbeiter mitarbeiter : this.mitarbeiterService.getAlleMitarbeiter() ) {
				if( mitarbeiter.getId().equals( id ) ) {
					System.out.println( "Converted to Mitarbeiter: " + mitarbeiter.getHydroId() );
					return new Mitarbeiter();
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	// @Override
	// public String getAsString( FacesContext context, UIComponent component,
	// Object value ) {
	// MitarbeiterModel mitarbeiter = (MitarbeiterModel) value;
	// if( mitarbeiter == null ) {
	// return null;
	// }
	//
	// return mitarbeiter.getId().toString();
	// }

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		Mitarbeiter ma = (Mitarbeiter) value;
		if( value == null ) {
			return null;
		}

		if( !( value instanceof Mitarbeiter ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (Mitarbeiter) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
