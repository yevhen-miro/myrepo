package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@Stateless
@Interceptors(PerformanceInterceptor.class)
public class MitarbeiterDao implements MitarbeiterDaoLocal{
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;
	
	@Resource
	private SessionContext sessionContext;

	@Resource
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostConstruct
	public void postConstruct(){
		logService.logMessage("bean was created via PostConstruct"); // @PostConstruct creates a bean
	};
	
	@PreDestroy
	public void preDestroy(){
		logService.logMessage("bean was removed via preDestroy"); // kills the bean
	}
	
	

	

	
	
	public void createMitarbeiter(Mitarbeiter m){
		entityManager.persist(m);
	}
	
	public List<Mitarbeiter> readAllMitarbeiter(){
		return entityManager.createQuery("FROM Mitarbeiter",Mitarbeiter.class).getResultList();
	}
	
	public void updateMitarbeiter(Mitarbeiter m) {
		entityManager.merge(m);
		
	}

	public void deleteMitarbeiter(Mitarbeiter m) {
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