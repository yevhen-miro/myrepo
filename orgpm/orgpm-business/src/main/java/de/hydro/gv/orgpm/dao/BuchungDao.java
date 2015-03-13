package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Mitarbeiter;
import de.hydro.gv.orgpm.util.PerformanceInterceptor;

@RequestScoped
public class BuchungDao implements BuchungDaoLocal{
	
	@Inject
	@ImplByConsole//CDI Annotation
	private LogService logService;
	
	
	private SessionContext sessionContext;
	

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

	public void updateBuchung(Buchung b) {
		em.merge(b);
		
	}

	public void deleteBuchung(Buchung b) {
		em.remove(em.merge(b));
		
	}

	public List<Buchung> readAllBuchungen() {
		return em.createQuery("SELECT b FROM Buchung b",Buchung.class).getResultList();
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

	public List<?> getBuchungByMitarbeiter(Integer _hydroid) {
		
		return em.createNamedQuery("buchung.find.buchung.by.mitarbeiter")
				 .setParameter("hydroid", _hydroid).getResultList();
	}
	

	public Buchung getBuchungById(Long _id){
		return em.find(Buchung.class, _id);
	}
	
	

}
