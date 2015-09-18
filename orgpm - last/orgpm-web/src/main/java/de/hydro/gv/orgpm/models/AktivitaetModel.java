package de.hydro.gv.orgpm.models;

import java.io.Serializable;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Projekt;

public class AktivitaetModel extends Model<Aktivitaet, AktivitaetModel> implements Serializable {

	private static final long serialVersionUID = 8068106284254331774L;

	private Long id;
	private Integer aktivitaetNr;
	private String aktivitaetText;
	private Boolean aktivitaetStatus;
	private Double planaufwand;
	private String bemerkung;

	private ProjektModel projekt;

	public AktivitaetModel( Aktivitaet aktivitaet ) {
		super( aktivitaet );
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Integer getAktivitaetNr() {
		return this.aktivitaetNr;
	}

	public void setAktivitaetNr( Integer aktivitaetNr ) {
		this.aktivitaetNr = aktivitaetNr;
	}

	public String getAktivitaetText() {
		return this.aktivitaetText;
	}

	public void setAktivitaetText( String aktivitaetText ) {
		this.aktivitaetText = aktivitaetText;
	}

	public Boolean isAktivitaetStatus() {
		return this.aktivitaetStatus;
	}

	public void setAktivitaetStatus( Boolean aktivitaetStatus ) {
		this.aktivitaetStatus = aktivitaetStatus;
	}

	public Double getPlanaufwand() {
		return this.planaufwand;
	}

	public void setPlanaufwand( Double planaufwand ) {
		this.planaufwand = planaufwand;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung( String bemerkung ) {
		this.bemerkung = bemerkung;
	}

	public ProjektModel getProjekt() {
		return this.projekt;
	}

	public void setProjekt( ProjektModel projekt ) {
		this.projekt = projekt;
	}

	public Boolean getAktivitaetStatus() {
		return this.aktivitaetStatus;
	}

	@Override
	public AktivitaetModel copyToModel() {
		this.setId( this.entity.getId() );
		this.setProjekt( new ProjektModel( this.entity.getProjekt() != null ? this.entity.getProjekt() : new Projekt() ) );
		this.setAktivitaetNr( this.entity.getAktivitaetNr() );
		this.setAktivitaetText( this.entity.getAktivitaetText() );
		this.setPlanaufwand( this.entity.getPlanaufwand() );
		this.setAktivitaetStatus( this.entity.isAktivitaetStatus() );
		this.setBemerkung( this.entity.getBemerkung() );
		return this.model;
	}

	@Override
	public Aktivitaet copyToEntity() {
		this.entity.setId( this.getId() );
		this.entity.setProjekt( this.getProjekt() != null ? this.getProjekt().convertToEntity() : null );
		this.entity.setAktivitaetNr( this.getAktivitaetNr() );
		this.entity.setAktivitaetText( this.getAktivitaetText() );
		this.entity.setPlanaufwand( this.getPlanaufwand() );
		this.entity.setAktivitaetStatus( this.isAktivitaetStatus() );
		this.entity.setBemerkung( this.getBemerkung() );
		return this.entity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) {
			return true;
		}
		if( obj == null ) {
			return false;
		}
		if( this.getClass() != obj.getClass() ) {
			return false;
		}
		AktivitaetModel other = (AktivitaetModel) obj;
		if( this.id == null ) {
			if( other.id != null ) {
				return false;
			}
		} else if( !this.id.equals( other.id ) ) {
			return false;
		}
		return true;
	}
}
