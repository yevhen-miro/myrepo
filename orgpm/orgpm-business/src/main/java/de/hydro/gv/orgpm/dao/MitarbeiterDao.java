package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.auth.RolleEnum;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@RequestScoped
public class MitarbeiterDao {

	@Inject
	@ImplByConsole
	// CDI Annotation
	private LogService logService;

	@PersistenceContext
	private EntityManager entityManager;

	// @PostConstruct
	// public void postConstruct() {
	// this.logService.logMessage( "bean was created via PostConstruct" ); //
	// @PostConstruct
	// // creates
	// // a
	// // bean
	// };
	//
	// @PreDestroy
	// public void preDestroy() {
	// this.logService.logMessage( "bean was removed via preDestroy" ); // kills
	// // the
	// // bean
	// }

	public MitarbeiterDao() {

	}

	public void createMitarbeiter( Mitarbeiter m ) {

		this.entityManager.persist( m );
	}

	public List<Mitarbeiter> readAllMitarbeiter() {
		return this.entityManager.createQuery( "FROM Mitarbeiter", Mitarbeiter.class ).getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public List<Mitarbeiter> readAlleMitarbeiter() {
		return this.entityManager.createNamedQuery( "mitarbeiter.find.alle.mitarbeiter" ).getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public List<RolleEnum> readAllRollen() {
		return this.entityManager.createQuery( "select r FROM " + RolleEnum.class.getName() + " r" ).getResultList();
	}

	public void updateMitarbeiter( Mitarbeiter m ) {
		this.entityManager.merge( m );

	}

	public void deleteMitarbeiter( Mitarbeiter m ) {
		this.removeOldMitarbeiterProjekte( m );
		this.entityManager.clear();
		this.entityManager.remove( this.entityManager.merge( m ) );
	}

	public void executeQuery( String queryName ) {
		this.entityManager.createNamedQuery( queryName ).executeUpdate();
	}

	public List<?> executeQueryWithResults( String queryName ) {
		return this.entityManager.createNamedQuery( queryName ).getResultList();
	}

	public void removeAllMitarbeiter() {
		this.entityManager.createQuery( "DELETE FROM MitarbeiterEntity" ).executeUpdate();

	}

	public Mitarbeiter getMitarbeiterById( Long id ) {
		return this.entityManager.getReference( Mitarbeiter.class, id );

	}

	public Mitarbeiter getMitarbeiterByName( String _name ) {
		return (Mitarbeiter) this.entityManager.createNamedQuery( "mitarbeiter.find.by.lastName" )
				.setParameter( "vorname", _name ).getSingleResult();
	}

	public Mitarbeiter getMitarbeiterByHydroId( String hydroid ) {
		return (Mitarbeiter) this.entityManager.createNamedQuery( "mitarbeiter.find.by.hydroid" )
				.setParameter( "hydroid", hydroid ).getSingleResult();
	}

	public String getMitarbeiterRolleByHydroId( String hydroid ) {
		return this.entityManager.createNamedQuery( "mitarbeiter.find.rolle.by.hydroid" )
				.setParameter( "hydroid", hydroid ).getSingleResult().toString();
	}

	public Login getLoginByMitarbeiter( String hydroid ) {
		return (Login) this.entityManager.createNamedQuery( "mitarbeiter.find.login.by.mitarbeiter" )
				.setParameter( "hydroid", hydroid ).getSingleResult();
	}

	public void removeOldMitarbeiterProjekte( Mitarbeiter m ) {
		this.entityManager.createNamedQuery( "mitarbeiter.delete.projects.by.mitarbeiter" )
				.setParameter( "mitarbeiter", m ).executeUpdate();
	}

}
