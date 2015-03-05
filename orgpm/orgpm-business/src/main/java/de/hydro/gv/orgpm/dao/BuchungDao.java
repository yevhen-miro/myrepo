package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@Stateless
@Interceptors(PerformanceInterceptor.class)
public class BuchungDao implements BuchungDaoLocal{
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;
	
	@Resource
	private SessionContext sessionContext;
	
	@Resource
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void postConstruct(){
		logService.logMessage("bean was created via PostConstruct"); // @PostConstruct creates a bean
	};
	
	@PreDestroy
	public void preDestroy(){
		logService.logMessage("bean was removed via preDestroy"); // kills the bean
	}

	public void createBuchung(Buchung b) {
		em.persist(b);
		
	}

	public void updateMitarbeiter(Buchung b) {
		em.merge(b);
		
	}

	public void deleteMitarbeiter(Buchung b) {
		em.remove(em.merge(b));
		
	}

	public List<Buchung> readAllBuchungen() {
		return em.createQuery("FROM Buchungen",Buchung.class).getResultList();
	}

	public void removeAlleBuchungen() {
		em.createQuery("DELETE FROM Buchungen").executeUpdate();
		
	}

	public List<?> executeQueryWithResults(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}

	public void executeQuery(String queryName) {
		em.createNamedQuery(queryName).executeUpdate();
		
	}
	
	

}
