package de.schellsoft.seminars.ee7.ejb.tests;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@RequestScoped
public class Factories {
	
	
	@Produces
	
	@Jdbc
	@Named("myWonderfulJdbcImpl")
	public ProductDAO createDao(){
		ProductDaoImplByJDBC impl = new ProductDaoImplByJDBC();
		//implt.set...
		return impl;
	}

}
