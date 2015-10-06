package de.hydro.gv.orgpm.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.orgpm.actions.SecurityActions;
import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.services.AktivitaetService;

@FacesConverter( "aktionConverter" )
public class AktionConverter implements Converter {

	@Inject
	private AktivitaetService aktivitaetService;

	@Inject
	private SecurityActions securityActions;

	@Override
	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( Aktivitaet aktivitaet : this.aktivitaetService.getAlleAktivitaet() ) {
				if( aktivitaet.getId().equals( id ) ) {
					System.out.println( "Converted to Aktivitaet: " + aktivitaet.getAktivitaetText() + " "
							+ this.securityActions.getSecurityPrincipalForLoggedInUser() );
					// Aktivitaet a = new Aktivitaet();
					return aktivitaet;
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAsString( FacesContext context, UIComponent component, Object value ) {
		Aktivitaet aktivitaet = (Aktivitaet) value;
		if( value == null ) {
			return null;
		}

		if( !( value instanceof Aktivitaet ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (Aktivitaet) value ).getId();

		// return ( id != null ) ? String.valueOf( id ) : null;
		return aktivitaet.getId().toString();
	}

}
