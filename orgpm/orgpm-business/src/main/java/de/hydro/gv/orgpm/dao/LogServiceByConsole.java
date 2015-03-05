package de.hydro.gv.orgpm.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;



@Stateless
@ImplByConsole

public class LogServiceByConsole implements LogService{
	
	public void logMessage(String message) {
		System.out.println("Client with id  logs: "+ message);
		
	}

}
