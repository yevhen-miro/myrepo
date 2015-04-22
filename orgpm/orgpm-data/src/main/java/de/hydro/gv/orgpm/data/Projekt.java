package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "projekte" )
@NamedQueries( {

		@NamedQuery( name = "projekt.alle", query = "SELECT p FROM Projekt AS p" ),
		@NamedQuery( name = "proejekt.delete.all", query = "DELETE FROM Projekt" ),
		@NamedQuery( name = "projekt.find.all", query = "SELECT p FROM Projekt AS p" ),
		@NamedQuery( name = "projekt.find.by.name", query = "SELECT p FROM Projekt AS p WHERE p.projektId= :proejektId" ) } )
public class Projekt implements Serializable {

	private static final long serialVersionUID = 3433657128651489265L;

	@SequenceGenerator( name = "SEQ_PROJEKT", sequenceName = "SEQ_PROJEKT", allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE, generator = "SEQ_PROJEKT" )
	@Id
	@Column( name = "id" )
	private Long id;

	@Column( name = "projekt_id" )
	private String projektId;

	@Column( name = "projekt_name" )
	private String projektName;

	@Column( name = "hauptprojekt" )
	private String hauptprojekt;

	@Column( name = "begriff" )
	private String Begriff;

	@Column( name = "projektgruppe" )
	private String projektGruppe;

	@Column( name = "projektleiter" )
	private String projektLeiter;

	@Column( name = "aufwand" )
	private double planaufwand;

	@Column( name = "start" )
	private Date projektstart;

	@Column( name = "ende" )
	private Date projektende;

	@Column( name = "storno" )
	private boolean storniertesProjekt;

	@Column( name = "status" )
	private Integer projektStatus;

	@Column( name = "bemerkung" )
	private String bemerkung;

	@Column( name = "wartung" )
	private boolean wartungsprojekt;

	public Projekt() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getProjektId() {
		return this.projektId;
	}

	public void setProjektId( String projektId ) {
		this.projektId = projektId;
	}

	public String getProjektName() {
		return this.projektName;
	}

	public void setProjektName( String projektName ) {
		this.projektName = projektName;
	}

	public String getHauptprojekt() {
		return this.hauptprojekt;
	}

	public void setHauptprojekt( String hauptprojekt ) {
		this.hauptprojekt = hauptprojekt;
	}

	public String getBegriff() {
		return this.Begriff;
	}

	public void setBegriff( String begriff ) {
		this.Begriff = begriff;
	}

	public String getProjektGruppe() {
		return this.projektGruppe;
	}

	public void setProjektGruppe( String projektGruppe ) {
		this.projektGruppe = projektGruppe;
	}

	public String getProjektLeiter() {
		return this.projektLeiter;
	}

	public void setProjektLeiter( String projektLeiter ) {
		this.projektLeiter = projektLeiter;
	}

	public double getPlanaufwand() {
		return this.planaufwand;
	}

	public void setPlanaufwand( double planaufwand ) {
		this.planaufwand = planaufwand;
	}

	public Date getProjektstart() {
		return this.projektstart;
	}

	public void setProjektstart( Date projektstart ) {
		this.projektstart = projektstart;
	}

	public Date getProjektende() {
		return this.projektende;
	}

	public void setProjektende( Date projektende ) {
		this.projektende = projektende;
	}

	public boolean isStorniertesProjekt() {
		return this.storniertesProjekt;
	}

	public void setStorniertesProjekt( boolean storniertesProjekt ) {
		this.storniertesProjekt = storniertesProjekt;
	}

	public Integer getProjektStatus() {
		return this.projektStatus;
	}

	public void setProjektStatus( Integer projektStatus ) {
		this.projektStatus = projektStatus;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung( String bemerkung ) {
		this.bemerkung = bemerkung;
	}

	public boolean isWartungsprojekt() {
		return this.wartungsprojekt;
	}

	public void setWartungsprojekt( boolean wartungsprojekt ) {
		this.wartungsprojekt = wartungsprojekt;
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
		Projekt other = (Projekt) obj;
		if( this.projektId != other.projektId ) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) ( prime * result + this.id );
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
