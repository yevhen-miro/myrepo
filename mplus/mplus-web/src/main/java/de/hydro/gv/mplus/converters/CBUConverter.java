package de.hydro.gv.mplus.converters;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.services.CBUService;


@FacesConverter("CBUConverter")
@RequestScoped
public class CBUConverter implements Converter {

	@Inject
	private CBUService cbuService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}
try {
	System.out.println(this.cbuService.getAllCBUs().size());
} catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
		try {
			for ( CBU c : this.cbuService.getAllCBUs()) {
				if( c.getId().equals( id ) ) {
					System.out.println( "Converted to CBU: " + c.getName());
					return c;
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		CBU c = (CBU) value;
		if( c == null ) {
			return null;
		}

		if( !( c instanceof CBU ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (CBU) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
