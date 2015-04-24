package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "mitarbeiter" )
@NamedQueries( {
		@NamedQuery( name = "mitarbeiter.delete.all", query = "DELETE FROM Mitarbeiter" ),
		@NamedQuery( name = "mitarbeiter.find.all", query = "SELECT m FROM Mitarbeiter AS m" ),
		@NamedQuery( name = "mitarbeiter.find.by.lastName",
				query = "SELECT m FROM Mitarbeiter AS m WHERE m.vorname= :vorname" ),
		@NamedQuery( name = "mitarbeiter.find.by.hydroid",
				query = "SELECT m FROM Mitarbeiter AS m WHERE m.hydroId= :hydroid" ) } )
public class Mitarbeiter implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;

	@SequenceGenerator( name = "SEQ_MITARBEITER", sequenceName = "SEQ_MITARBEITER", allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE, generator = "SEQ_MITARBEITER" )
	@Id
	@Column( name = "id" )
	private Long id;

	@Column( name = "vorname" )
	private String vorname;

	@Column( name = "name" )
	private String name;

	@Column( name = "gruppe" )
	private String gruppe;

	@Column( name = "kennung" )
	private String mitarbeiterkennung;

	@Column( name = "arbeitszeit" )
	private double arbeitszeit;

	@Column( name = "status" )
	private double mitarbeiterStatus;

	@Column( name = "bemerkung" )
	private String bemerkung;

	@Column( name = "personalid" )
	private int personalNum;

	@Column( name = "hydroid" )
	private String hydroId;

	@Column( name = "kartenid" )
	private int kartenNum;

	public String getGruppe() {
		return this.gruppe;
	}

	public void setGruppe( String gruppe ) {
		this.gruppe = gruppe;
	}

	public Mitarbeiter( Integer id, String vorname, String name, String gruppe, String mitarbeiterkennung ) {
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname( String vorname ) {
		this.vorname = vorname;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Mitarbeiter() {
	}

	public Mitarbeiter( String vorname, String name, String gruppe ) {
		this.vorname = vorname;
		this.name = name;
		this.gruppe = gruppe;
	}

	public String getMitarbeiterkennung() {
		return this.mitarbeiterkennung;
	}

	public void setMitarbeiterkennung( String mitarbeiterkennung ) {
		this.mitarbeiterkennung = mitarbeiterkennung;
	}

	public double getArbeitszeit() {
		return this.arbeitszeit;
	}

	public void setArbeitszeit( double arbeitszeit ) {
		this.arbeitszeit = arbeitszeit;
	}

	public double getMitarbeiterStatus() {
		return this.mitarbeiterStatus;
	}

	public void setMitarbeiterStatus( double mitarbeiterStatus ) {
		this.mitarbeiterStatus = mitarbeiterStatus;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung( String bemerkung ) {
		this.bemerkung = bemerkung;
	}

	public int getPersonalNum() {
		return this.personalNum;
	}

	public void setPersonalNum( int personalNum ) {
		this.personalNum = personalNum;
	}

	public String getHydroId() {
		return this.hydroId;
	}

	public void setHydroId( String hydroId ) {
		this.hydroId = hydroId;
	}

	public int getKartenNum() {
		return this.kartenNum;
	}

	public void setKartenNum( int kartenNum ) {
		this.kartenNum = kartenNum;
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
		Mitarbeiter other = (Mitarbeiter) obj;
		if( this.hydroId != other.hydroId ) {
			return false;
		}
		return true;
	}
}
