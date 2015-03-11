package de.hydro.gv.orgpm.dao;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;

import de.hydro.gv.orgpm.data.Mitarbeiter;

import java.util.List;

@Local
public interface MitarbeiterDaoLocal {
	
	public abstract void createMitarbeiter(
			Mitarbeiter m);

	public abstract void updateMitarbeiter(Mitarbeiter m);

	public abstract void deleteMitarbeiter(Mitarbeiter m);
	
	public abstract List<Mitarbeiter> readAllMitarbeiter();
	
	public void preDestroy();
	
	public void postConstruct();
	
	public abstract void removeAllMitarbeiter();
	
	public List<?> executeQueryWithResults (String queryName);
	
	public void executeQuery(String queryName);
	
	public void getMitarbeiterById(Long id);
	
	public Mitarbeiter getMitarbeiterByName(String _name);

}
