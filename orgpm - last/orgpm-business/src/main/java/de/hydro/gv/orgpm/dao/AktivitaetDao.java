package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hydro.gv.orgpm.data.Aktivitaet;

@RequestScoped
public class AktivitaetDao {

	@Inject
	@ImplByConsole
	// CDI Annotation
	private LogService logService;

	@PersistenceContext
	private EntityManager entityManager;

	public AktivitaetDao() {

	}

	public void addAktivitaet( Aktivitaet a ) {
		this.entityManager.persist( a );
	}

	public List<Aktivitaet> readAllAktivitaeten() {
		return this.entityManager.createQuery( "FROM Aktivitaet", Aktivitaet.class ).getResultList();
	}

	public void updateAktivitaet( Aktivitaet a ) {
		this.entityManager.merge( a );

	}

	public void deleteAktivitaet( Aktivitaet a ) {
		this.entityManager.remove( this.entityManager.merge( a ) );
	}

	@SuppressWarnings( "unchecked" )
	public List<Aktivitaet> getAktivitaetenByProjektName( String _projektName ) {

		return this.entityManager.createNamedQuery( "aktivitaet.find.aktivitaet.by.projektname" )
				.setParameter( "projekt", _projektName ).getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public List<Aktivitaet> getAktivitaetenByProjektid( Long _id ) {

		return this.entityManager.createNamedQuery( "aktivitaet.find.aktivitaet.by.projektid" )
				.setParameter( "id", _id ).getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public List<Aktivitaet> getAktivitaetenByProjekt( String projektName ) {

		return this.entityManager.createNamedQuery( "aktivitaet.find.aktivitaet.by.projektname" )
				.setParameter( "name", projektName ).getResultList();
	}

}