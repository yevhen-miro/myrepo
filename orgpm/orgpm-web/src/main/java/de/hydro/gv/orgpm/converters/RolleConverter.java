//package de.hydro.gv.orgpm.converters;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//
//import de.hydro.gv.orgpm.auth.Rolle;
//import de.hydro.gv.orgpm.models.RolleModel;
//import de.hydro.gv.orgpm.services.MitarbeiterService;
//
//@FacesConverter( "rolleConverter" )
//public class RolleConverter implements Converter {
//
//	@Inject
//	private MitarbeiterService mitarbeiterService;
//
//	@Override
//	public Object getAsObject( FacesContext context, UIComponent component, String value ) {
//		Long id = 0L;
//		try {
//			id = Long.parseLong( value );
//		} catch ( NumberFormatException e ) {
//			return null;
//		}
//
//		try {
//			for ( Rolle rolle : this.mitarbeiterService.getAlleRollen() ) {
//				if( rolle.getId().equals( id ) ) {
//					System.out.println( "Converted to Rolle: " + rolle.getId() );
//					return new RolleModel( rolle );
//				}
//			}
//		} catch ( Exception e ) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	@Override
//	public String getAsString( FacesContext context, UIComponent component, Object value ) {
//		// RolleModel rolle = (RolleModel) value;
//		if( value == null ) {
//			return null;
//		}
//
//		if( !( value instanceof RolleModel ) ) {
//			throw new ConverterException( "The value is not a valid" );
//		}
//		Long id = ( (RolleModel) value ).getId();
//
//		return ( id != null ) ? String.valueOf( id ) : null;
//	}
//
//}
//
//// @Override
//// public String getAsString( FacesContext context, UIComponent component,
//// Object value ) {
//// Projekt projekt = (Projekt) value;
//// if( projekt == null ) {
//// return null;
//// }
////
//// return projekt.getId().toString();
//// }
//
