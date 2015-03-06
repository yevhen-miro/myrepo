package de.hydro.gv.orgpm.dao;



import javax.ejb.Stateless;

@Stateless
@ImplByConsole
public interface LogService {

	public void logMessage(String message);
}
	
	
	

