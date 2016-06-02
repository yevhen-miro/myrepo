package de.hydro.gv.mplus.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.utils.ContractStatus;

@FacesConverter("ContractStatusConverter")
public class ContractStatusConverter implements Converter{
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = 0L;
		try {
			id = Long.parseLong( value );
		} catch ( NumberFormatException e ) {
			return null;
		}

		return id;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		

		int statusId = (int) value;
		
		String status = null;
		
		switch (statusId) {
		
        case -1:  status = ContractStatus.DELETED.getContractStatus();
        break;
        
        case 0:  status = ContractStatus.NEW.getContractStatus();
        break;
        
        case 1:  status = ContractStatus.UNAPPROVED.getContractStatus();
        break;
        
        case 2:  status = ContractStatus.APPROVALPENDING.getContractStatus();
        break;
        
        case 3:  status = ContractStatus.APPROVED.getContractStatus();
        break;
        
        case 5:  status = ContractStatus.FINALIZED.getContractStatus();
        break;
        
        case 6:  status = ContractStatus.FINALIZEPENDING.getContractStatus();
        break;
        
        case 7:  status = ContractStatus.CANCELLED.getContractStatus();
        break;
        
        case 8:  status = ContractStatus.CANCELPENDING.getContractStatus();
        break;
        
		}
		return status;
	}

}
