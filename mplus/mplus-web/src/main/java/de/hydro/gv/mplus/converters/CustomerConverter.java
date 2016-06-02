package de.hydro.gv.mplus.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("customerConverter")
@RequestScoped
public class CustomerConverter implements Converter {

	@Inject
	private CustomerService customerService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String cname = "";
		if(cname.equals(value)) {
				return null;
		}
		else {	
		
		try {
			Customer c = (Customer) this.customerService.findCustomersByExactName(value);
			return c;
			/*
			 * for ( Customer c : this.customerService.getAllCustomers()) { if(
			 * c.getCustomer_id().equals( id ) ) { System.out.println(
			 * "Converted to Customer: " + c.getCustomer_fullname()); return c;
			 * } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		Customer c = (Customer) value;
		if (c == null) {
			return null;
		}

		if (!(c instanceof Customer)) {
			throw new ConverterException("The value is not a valid");
		}
		// Long id = ( (Customer) value ).getCustomer_id();
		String cname = ((Customer) value).getCustomer_fullname();

		// return ( id != null ) ? String.valueOf( id ) : null;
		return cname;
	}

}
