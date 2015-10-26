package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.data.Projekt;
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

		return this.projektService.getProjektById( id );

	}

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		Projekt projekt = (Projekt) value;
		if( projekt == null ) {
			return null;
		}

		if( !( projekt instanceof Projekt ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (Projekt) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
