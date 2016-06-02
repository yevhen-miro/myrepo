package de.hydro.gv.mplus.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.services.BUService;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.services.PlantService;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("BUConverter")
@RequestScoped
public class BUConverter implements Converter {

	@Inject
	private BUService buService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( BU b : this.buService.getAllBUs()) {
				if( b.getId().equals( id ) ) {
					System.out.println( "Converted to BU: " + b.getName());
					return b;
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		BU b = (BU) value;
		if( b == null ) {
			return null;
		}

		if( !( b instanceof BU ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (BU) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
