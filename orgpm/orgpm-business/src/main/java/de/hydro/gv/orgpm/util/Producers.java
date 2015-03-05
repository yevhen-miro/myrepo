package de.hydro.gv.orgpm.util;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@Stateless
public class Producers {
	
	private static int actualId = 0;
	
	@Produces //CDI-Annotation Factory Method
	public int generateRequestId(){
		return ++actualId;
	}

}
