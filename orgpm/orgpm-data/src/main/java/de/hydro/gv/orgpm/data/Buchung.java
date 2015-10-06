package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

@Entity
@Table( name = "buchungen" )
@NamedQueries( {
		@NamedQuery( name = "buchung.delete.all", query = "DELETE FROM Buchung" ),
		@NamedQuery( name = "buchung.find.all", query = "SELECT b FROM Buchung AS b" ),
		@NamedQuery(
				name = "buchung.find.duration.by.mitarbeiter",
				query = "SELECT b.datum,sum(b.min) FROM Buchung AS b, Mitarbeiter m WHERE b.mitarbeiter.id = m.id AND m.hydroId  = :hydroid group by b.datum" ),
		@NamedQuery(
				name = "buchung.find.by.date",
				query = "SELECT b FROM Buchung AS b, Mitarbeiter m WHERE b.mitarbeiter.id = m.id AND m.hydroId = :hydroid and b.datum = :datum" ),
		@NamedQuery(
				name = "buchung.find.buchung.by.mitarbeiter",
				query = "SELECT b FROM Buchung b, Mitarbeiter m WHERE b.mitarbeiter.id = m.id AND m.hydroId = :hydroid and b.datum = :datum" ) } )
public class Buchung implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;

	@SequenceGenerator( name = "SEQ_BUCHUNGEN", sequenceName = "SEQ_BUCHUNGEN", allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE, generator = "SEQ_BUCHUNGEN" )
	@Column( name = "id" )
	private Long id;

	@ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE } )
	@JoinColumn( name = "PROJEKT", foreignKey = @ForeignKey( name = "FK_BUCHUNG_PROJEKT" ) )
	private Projekt projekt;

	@ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE } )
	@JoinColumn( name = "mitarbeiter", foreignKey = @ForeignKey( name = "FK_BUCHUNG_MITARBEITER" ) )
	private Mitarbeiter mitarbeiter;

	public Mitarbeiter getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( Mitarbeiter mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
	}

	@ManyToOne( fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE } )
	@JoinColumn( name = "AKTIVITAET", foreignKey = @ForeignKey( name = "FK_BUCHUNG_AKTIVITAET" ) )
	private Aktivitaet aktivitaet;

	@Temporal( TemporalType.DATE )
	@Column( name = "datum" )
	private Date datum;

	@Temporal( TemporalType.TIME )
	@Column( name = "anfang" )
	private Date anfangZeit;

	@Temporal( TemporalType.TIME )
	@Column( name = "ende" )
	private Date endeZeit;

	@Temporal( TemporalType.TIME )
	@Column( name = "pause_Von" )
	private Date pauseVon;

	@Temporal( TemporalType.TIME )
	@Column( name = "pause_Bis" )
	private Date pauseBis;

	@Column( name = "stunden" )
	private Long std;

	@Column( name = "minuten" )
	private Long min;

	@Column( name = "taetigkeiten" )
	private String taetigkeiten;

	@Column( name = "wartung_Id" )
	private int wartungId;

	// @Column( name = "HydroId" )
	// private String hydroid;

	@Transient
	private Collection<Aktivitaet> activities;

	public Aktivitaet getAktivitaet() {
		return this.aktivitaet;
	}

	public void setAktivitaet( Aktivitaet aktivitaet ) {
		this.aktivitaet = aktivitaet;
	}

	// public String getHydroid() {
	// return this.hydroid;
	// }
	//
	// public void setHydroid( String hydroid ) {
	// this.hydroid = this.getSecurityPrincipalForLoggedInUser().toUpperCase();
	// }

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Collection<Aktivitaet> getActivities() {
		return this.activities;
	}

	public void setActivities( Collection<Aktivitaet> activities ) {
		this.activities = activities;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum( Date datum ) {
		this.datum = datum;
	}

	public Date getAnfangZeit() {
		return this.anfangZeit;
	}

	public void setAnfangZeit( Date anfangZeit ) {
		this.anfangZeit = anfangZeit;
	}

	public Date getEndeZeit() {
		return this.endeZeit;
	}

	public void setEndeZeit( Date endeZeit ) {
		this.endeZeit = endeZeit;
	}

	public Date getPauseVon() {
		return this.pauseVon;
	}

	public void setPauseVon( Date pauseVon ) {
		this.pauseVon = pauseVon;
	}

	public Date getPauseBis() {
		return this.pauseBis;
	}

	public void setPauseBis( Date pauseBis ) {
		this.pauseBis = pauseBis;
	}

	public Long getStd() {
		return this.std;
	}

	public void setStd( Long std ) {
		this.std = std;
	}

	public Long getMin() {
		return this.min;
	}

	public void setMin( Long min ) {
		this.min = min;
	}

	public String getTaetigkeiten() {
		return this.taetigkeiten;
	}

	public void setTaetigkeiten( String taetigkeiten ) {
		this.taetigkeiten = taetigkeiten;
	}

	public int getWartungId() {
		return this.wartungId;
	}

	public void setWartungId( int wartungId ) {
		this.wartungId = wartungId;
	}

	public Projekt getProjekt() {
		return this.projekt;
	}

	public void setProjekt( Projekt projekt ) {
		this.projekt = projekt;
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

		return ( (Buchung) obj ).id == this.id;
		// Buchung other = (Buchung) obj;
		// if( this.id == null ) {
		// if( other.id != null ) {
		// return false;
		// }
		// } else if( !this.id.equals( other.id ) ) {
		// return false;
		// }
		// return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public String getSecurityPrincipalForLoggedInUser() {
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		Principal principal = httpServletRequest.getUserPrincipal();
		System.out.println( principal.toString() );
		return principal != null ? principal.toString() : "UNAUTHORIZED";
	}

}
