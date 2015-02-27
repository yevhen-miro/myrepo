package de.schellsoft.seminars.ee7.ejb.tests;

public interface ProductDAO {
	
	public void storeProduct(String productName);
	
	public void removeProduct(String productName);

}
