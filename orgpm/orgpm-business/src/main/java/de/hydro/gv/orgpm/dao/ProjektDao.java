package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@RequestScoped
public class ProjektDao {
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;
	
	private SessionContext sessionContext;


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
	
	
	public ProjektDao() {
		
	}

	public void createProjekt(Projekt p){
		entityManager.persist(p);
	}
	
	public List<Projekt> readAlleProjekte(){
		return entityManager.createQuery("FROM Projekt",Projekt.class).getResultList();
	}
	
	public void updateProjekt(Projekt p) {
		 entityManager.merge(p);
		
	}

	public void deleteProjekt(Projekt p) {
		entityManager.remove(entityManager.merge(p));
	}
	
	public void executeQuery(String queryName){
		entityManager.createNamedQuery(queryName).executeUpdate();
	}
	
	public List<?> executeQueryWithResults (String queryName){
		return entityManager.createNamedQuery(queryName).getResultList();
	}

	public void getProjektById(Long id) {
		entityManager.getReference(Projekt.class, id);
		
	}
	


	
}