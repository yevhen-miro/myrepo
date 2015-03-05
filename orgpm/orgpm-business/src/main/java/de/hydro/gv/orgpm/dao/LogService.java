package de.hydro.gv.orgpm.dao;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
@Local
public interface LogService {

	public void logMessage(String message);
}
	
	
	

