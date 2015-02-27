package de.schellsoft.seminars.ee7.ejb.tests;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Jdbc
@Named("myWonderfulJdbcImplClass")
public class ProductDaoImplByJDBC implements ProductDAO {

	public void storeProduct(String productName) {
		System.out.println("ProductDaoImpByJdbc.storeProduct()");
		
	}

	public void removeProduct(String productName) {
		System.out.println("ProductDaoImpByJdbc.removeProduct()");
		
	}

	
}
