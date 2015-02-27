package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.util.Logger;

@Stateless
public class MitarbeiterDao {

	@Inject
	private Logger logger;
	
	@Resource
	private SessionContext sessionContext;

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostConstruct
	public void postConstruct(){
		logger.logMessage("bean was created via PostConstruct"); // @PostConstruct creates a bean
	};
	
	@PreDestroy
	public void preDestroy(){
		logger.logMessage("bean was removed via preDestroy"); // kills the bean
	}
	
	
	public void createMitarbeiter(Mitarbeiter m){
		logger.logMessage("MitarbeiterPersistanceService.createMitarbeiter()");
		entityManager.persist(m);
	}
	
	public List<Mitarbeiter> readAllMitarbeiter(){
		logger.logMessage("MitarbeiterPersistanceService.readAllMitarbeiter()"); 
		return entityManager.createQuery("FROM MitarbeiterEntity",Mitarbeiter.class).getResultList();
	}
	
	public void updateMitarbeiter(Mitarbeiter m) {
		logger.logMessage("MitarbeiterPersistanceService.updateMitarbeiter()");
		entityManager.merge(m);
		
	}

	public void deleteMitarbeiter(Mitarbeiter m) {
		logger.logMessage("MitarbeiterPersistanceService.deleteMitarbeiter()");
		entityManager.remove(entityManager.merge(m));
	}
	
	public void executeQuery(String queryName){
		entityManager.createNamedQuery(queryName).executeUpdate();
	}
	
	public List<?> executeQueryWithResults (String queryName){
		return entityManager.createNamedQuery(queryName).getResultList();
	}

	public void removeAllMitarbeiter() {
		entityManager.createQuery("DELETE FROM MitarbeiterEntity").executeUpdate();
		
	}
	
}