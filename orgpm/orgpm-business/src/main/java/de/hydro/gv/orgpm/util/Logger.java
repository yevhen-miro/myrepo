package de.hydro.gv.orgpm.util;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class Logger {
		
		@Inject //delivers actual instance. EJB bean are automatically CDI beans with default scope
		private RequestId requestId;
		public void logMessage(String message) {
			System.out.println("Client with id " + requestId.getRequestId() + " logs: "+ message);
			
		}

	}


