package de.hydro.gv.mplus.converters;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("customerParentConverter")
@RequestScoped
public class CustomerParentConverter implements Converter{
	
	@Inject
	private CustomerService customerService;
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( CustomerParent cp : this.customerService.getAllCustomerParents()) {
				if( cp.getId().equals( id ) ) {
					System.out.println( "Converted to CustomerParent: " + cp.getName());
					return cp;
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		CustomerParent cp = (CustomerParent) value;
		if( cp == null ) {
			return null;
		}

		if( !( cp instanceof CustomerParent ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (CustomerParent) value ).getId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
