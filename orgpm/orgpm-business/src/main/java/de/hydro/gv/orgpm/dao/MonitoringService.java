package de.hydro.gv.orgpm.dao;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.inject.Inject;


@Stateless
public class MonitoringService {

	@Inject
	private CustomerPersistanceServiceLocal persistenceService;
	
	@Inject
	@ImplByConsole
	private LogService logService;
	
	//@Schedule( second = "*/10", minute = "*", hour = "*")// annotation for the batch scheduling
	public void logDattabaseStatistics(Timer timer){
		int customerCount = persistenceService.readAllCustomers().size();
		logService.logMessage("Statistik: Anzahl der Kunden = " + customerCount);
	}
	
}
