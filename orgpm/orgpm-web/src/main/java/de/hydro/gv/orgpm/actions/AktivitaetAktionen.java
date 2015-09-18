package de.hydro.gv.orgpm.actions;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;
import de.hydro.gv.orgpm.models.AktivitaetModel;
import de.hydro.gv.orgpm.models.ProjektModel;
import de.hydro.gv.orgpm.services.AktivitaetService;

@RequestScoped
@Named
public class AktivitaetAktionen {

	private ProjektModel aktProjekt = new ProjektModel( new Projekt() );
	private AktivitaetModel aktAktivitaet = new AktivitaetModel( new Aktivitaet() );

	@Inject
	private AktivitaetService aktivitaetService;

	private Collection<de.hydro.gv.orgpm.models.AktivitaetModel> cachedAktivitaetenList;

	public de.hydro.gv.orgpm.models.AktivitaetModel getAktAktivitaet() {
		return this.aktAktivitaet;
	}

	public de.hydro.gv.orgpm.models.ProjektModel getAktProjekt() {
		return this.aktProjekt;
	}

	public void setAktProjekt( ProjektModel aktProjekt ) {
		this.aktProjekt = aktProjekt;
	}

	public void setAktAktivitaet( AktivitaetModel aktAktivitaet ) {
		this.aktAktivitaet = aktAktivitaet;
	}

	public Collection<AktivitaetModel> getAlle() throws Exception {
		if( this.cachedAktivitaetenList == null ) {
			Collection<Aktivitaet> actEntities = this.aktivitaetService.getAlleAktivitaet();
			this.cachedAktivitaetenList = new ArrayList<AktivitaetModel>();
			for ( Aktivitaet entity : actEntities ) {
				AktivitaetModel model = new AktivitaetModel( entity );
				this.cachedAktivitaetenList.add( model );
			}
		}
		return this.cachedAktivitaetenList;
	}

	public String addAktivitaet() throws Exception {
		this.aktivitaetService.addAktivitaet( this.aktAktivitaet.convertToEntity() );
		this.cachedAktivitaetenList = null;
		return "aktivitaeten";
	}

	public String addNewAktivitaet() {
		return "aktivitaet-input";
	}

	public String removeAktivitaet() throws Exception {
		de.hydro.gv.orgpm.data.Aktivitaet tempEntity = new de.hydro.gv.orgpm.data.Aktivitaet();
		tempEntity.setId( this.aktAktivitaet.getId() );
		this.aktivitaetService.deleteAktivitaet( tempEntity );
		this.cachedAktivitaetenList = null;
		return null;
	}

	public String updateAktivitaet() throws Exception {
		this.aktivitaetService.updateAktivitaet( this.aktAktivitaet.convertToEntity() );
		this.cachedAktivitaetenList = null;
		return "aktivitaeten";
	}

}
