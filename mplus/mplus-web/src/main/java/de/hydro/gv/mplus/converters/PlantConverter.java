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
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.services.PlantService;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("plantConverter")
@RequestScoped
public class PlantConverter implements Converter {

	@Inject
	private PlantService plantService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		try {
			for ( Plant p : this.plantService.getAllPlants()) {
				if( p.getPlantId().equals( id ) ) {
					System.out.println( "Converted to Plant: " + p.getPlantName());
					return p;
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		Plant p = (Plant) value;
		if( p == null ) {
			return null;
		}

		if( !( p instanceof Plant ) ) {
			throw new ConverterException( "The value is not a valid" );
		}
		Long id = ( (Plant) value ).getPlantId();

		return ( id != null ) ? String.valueOf( id ) : null;
	}

}
