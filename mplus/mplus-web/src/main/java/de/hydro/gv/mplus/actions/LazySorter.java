package de.hydro.gv.mplus.actions;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import de.hydro.gv.mplus.data.Contract;

public class LazySorter implements Comparator<Contract>{
	
	private String sortField;
    
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Contract c1, Contract c2) {
        try {
            Object value1 = Contract.class.getField(this.sortField).get(c1);
            Object value2 = Contract.class.getField(this.sortField).get(c2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}