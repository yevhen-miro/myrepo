package de.hydro.gv.mplus.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import de.hydro.gv.mplus.data.Contract;

public class LazyContractDataModel extends LazyDataModel<Contract> {

	private static final long serialVersionUID = -4060640728459295637L;

	private List<Contract> datasource;

	public LazyContractDataModel(List<Contract> datasource) {
		this.datasource = datasource;
	}

	@Override
    public Contract getRowData(String rowKey) {
        for(Contract c : datasource) {
            if(c.getId().equals(rowKey))
                return c;
        }
        
        return null;
    }
        
   @Override
   public Object getRowKey(Contract contract) {
   return contract.getId();
        }
 
   @Override
   public List<Contract> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
       List<Contract> data = new ArrayList<Contract>();

       //filter
       for(Contract c : datasource) {
           boolean match = true;

           if (filters != null) {
               for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                   try {
                       String filterProperty = it.next();
                       Object filterValue = filters.get(filterProperty);
                       String fieldValue = String.valueOf(c.getClass().getField(filterProperty).get(c));

                       if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                           match = true;
                   }
                   else {
                           match = false;
                           break;
                       }
                   } catch(Exception e) {
                       match = false;
                   }
               }
           }

           if(match) {
               data.add(c);
           }
       }

       //sort
       if(sortField != null) {
           Collections.sort(data, new LazySorter (sortField, sortOrder));
       }

       //rowCount
       int dataSize = data.size();
       this.setRowCount(dataSize);

       //paginate
       if(dataSize > pageSize) {
           try {
               return data.subList(first, first + pageSize);
           }
           catch(IndexOutOfBoundsException e) {
               return data.subList(first, first + (dataSize % pageSize));
           }
       }
       else {
           return data;
       }
   }
}