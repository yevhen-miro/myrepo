package de.schellsoft.seminars.ee7.ejb.tests;

import javax.enterprise.context.RequestScoped;


@RequestScoped
public class ProductDaoImplByJPA implements ProductDAO {

	public void storeProduct(String productName) {
		System.out.println("ProductDaoImpByJPA.storeProduct()");
		
	}

	public void removeProduct(String productName) {
		System.out.println("ProductDaoImpByJPA.removeProduct()");
		
	}

	
}
