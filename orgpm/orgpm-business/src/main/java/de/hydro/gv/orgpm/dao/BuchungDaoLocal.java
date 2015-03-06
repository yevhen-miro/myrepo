package de.hydro.gv.orgpm.dao;

import java.util.List;

import de.hydro.gv.orgpm.data.Buchung;

public interface BuchungDaoLocal {

	public abstract void createBuchung(Buchung b);

	public abstract void updateBuchung(Buchung b);

	public abstract void deleteBuchung(Buchung b);

	public abstract List<Buchung> readAllBuchungen();

	public void preDestroy();

	public void postConstruct();

	public abstract void removeAlleBuchungen();

	public List<?> executeQueryWithResults(String queryName);

	public void executeQuery(String queryName);
	
	public List<?> getBuchungByMitarbeiter(Integer _hydroid);
	
	public Buchung getBuchungById(Long _id);

}
