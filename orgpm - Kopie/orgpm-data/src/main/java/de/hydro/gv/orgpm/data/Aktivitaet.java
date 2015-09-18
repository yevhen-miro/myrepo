package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

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

@Entity
@Table( name = "aktivitaeten" )
@NamedQueries( {
		@NamedQuery( name = "aktivitaet.delete.all", query = "DELETE FROM Aktivitaet" ),
		@NamedQuery( name = "aktivitaet.find.all", query = "SELECT a FROM Aktivitaet AS a" ),
		@NamedQuery( name = "aktivitaet.find.aktivitaet.by.projektname",
				query = "SELECT a FROM Aktivitaet a INNER JOIN a.projekt p " + "WHERE p.projektId = :name" ),
		@NamedQuery( name = "aktivitaet.find.aktivitaet.by.projektid",
				query = "SELECT a FROM Aktivitaet a INNER JOIN a.projekt p " + "WHERE p.id = :id" ),
		@NamedQuery( name = "aktivitaet.find.aktivitaet.by.projekt",
				query = "SELECT a FROM Aktivitaet a INNER JOIN a.projekt p " ) } )
public class Aktivitaet implements Serializable {

	private static final long serialVersionUID = -1773699513191865605L;

	@SequenceGenerator( name = "SEQ_AKTIVITAET", sequenceName = "SEQ_AKTIVITAET", allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE, generator = "SEQ_AKTIVITAET" )
	@Id
	@Column( name = "id" )
	private Long id;

	@Column( name = "aktivitaet_nr" )
	private Integer aktivitaetNr;

	@Column( name = "text" )
	private String aktivitaetText;

	@Column( name = "status" )
	private boolean aktivitaetStatus;

	@Column( name = "aufwand" )
	private Double planaufwand;

	@Column( name = "bemerkung" )
	private String bemerkung;

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn( name = "PROJEKT", foreignKey = @ForeignKey( name = "FK_AKTIVITAET_PROJEKT" ) )
	private Projekt projekt;

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

	public boolean isAktivitaetStatus() {
		return this.aktivitaetStatus;
	}

	public void setAktivitaetStatus( boolean aktivitaetStatus ) {
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

	public Projekt getProjekt() {
		return this.projekt;
	}

	public void setProjekt( Projekt projekt ) {
		this.projekt = projekt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Aktivitaet other = (Aktivitaet) obj;
		if( this.id != other.id ) {
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
