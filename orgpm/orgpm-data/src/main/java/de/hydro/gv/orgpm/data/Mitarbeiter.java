package de.hydro.gv.orgpm.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.auth.RolleEnum;

@Entity
@Table( name = "MITARBEITER" )
@NamedQueries( {
		@NamedQuery( name = "mitarbeiter.delete.all", query = "DELETE FROM Mitarbeiter" ),
		@NamedQuery( name = "mitarbeiter.find.all", query = "SELECT m FROM Mitarbeiter AS m" ),
		@NamedQuery( name = "mitarbeiter.find.login.by.mitarbeiter",
				query = "SELECT l FROM Login l, Mitarbeiter m WHERE l.mitarbeiter.id = m.id and m.hydroId = :hydroid" ),
		@NamedQuery( name = "mitarbeiter.find.by.lastName",
				query = "SELECT m FROM Mitarbeiter AS m WHERE m.vorname= :vorname" ),
		@NamedQuery( name = "mitarbeiter.delete.projects.by.mitarbeiter",
				query = "DELETE FROM MitarbeiterProjekte AS mp WHERE mp.mitarbeiter = :mitarbeiter" ),
		@NamedQuery( name = "mitarbeiter.find.by.hydroid",
				query = "SELECT m FROM Mitarbeiter AS m WHERE m.hydroId= :hydroid" ) } )
public class Mitarbeiter implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;

	@SequenceGenerator( name = "SEQ_MITARBEITER", sequenceName = "SEQ_MITARBEITER", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_MITARBEITER" )
	@Id
	@Column( name = "id" )
	private Long id;

	@Column( name = "VORNAME", length = 30 )
	@NotNull
	private String vorname;

	@Column( name = "NACHNAME", length = 50 )
	@NotNull
	private String nachname;

	@Column( name = "GEBURTSDATUM" )
	@Temporal( TemporalType.DATE )
	private Date geburtsdatum;

	@Column( name = "EINSTELLUNGSDATUM" )
	@Temporal( TemporalType.DATE )
	private Date einstellungsdatum;

	@Column( name = "KUENDIGUNGSDATUM" )
	@Temporal( TemporalType.DATE )
	private Date kuendigungsdatum;

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

	@Column( name = "hydroid", length = 10, unique = true )
	private String hydroId;

	@Column( name = "kartenid" )
	private int kartenNum;

	@Enumerated( EnumType.STRING )
	private RolleEnum rolle;

	@OneToMany( mappedBy = "mitarbeiter", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private List<MitarbeiterProjekte> projekte;
	// @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	// @JoinColumn( name = "M_PROJEKT" )
	// @JoinTable( name = "MITARBEITER_PROJEKTE", joinColumns = @JoinColumn(
	// name = "mitarbeiter_id" ),
	// inverseJoinColumns = @JoinColumn( name = "projekt_id" ) )
	// private List<Projekt> projekte;
	// public List<Projekt> getProjekte() {
	// return this.projekte;
	// }
	//
	// public void setProjekte( List<Projekt> projekte ) {
	// this.projekte = projekte;
	// }
	@OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
	@PrimaryKeyJoinColumn
	private Login login;

	public Mitarbeiter( Integer id, String vorname, String name, String gruppe, String mitarbeiterkennung ) {
	}

	public Mitarbeiter( String vorname, String name, String gruppe ) {
		this.vorname = vorname;
		this.nachname = name;
		this.gruppe = gruppe;
	}

	public Mitarbeiter() {
	}

	public String getGruppe() {
		return this.gruppe;
	}

	public void setGruppe( String gruppe ) {
		this.gruppe = gruppe;
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
		return this.nachname;
	}

	public void setName( String name ) {
		this.nachname = name;
	}

	public Date getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum( Date geburtsdatum ) {
		this.geburtsdatum = geburtsdatum;
	}

	public Date getEinstellungsdatum() {
		return this.einstellungsdatum;
	}

	public void setEinstellungsdatum( Date einstellungsdatum ) {
		this.einstellungsdatum = einstellungsdatum;
	}

	public Date getKuendigungsdatum() {
		return this.kuendigungsdatum;
	}

	public void setKuendigungsdatum( Date kuendigungsdatum ) {
		this.kuendigungsdatum = kuendigungsdatum;
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

	public RolleEnum getRolle() {
		return this.rolle;
	}

	public void setRolle( RolleEnum rolle ) {
		this.rolle = rolle;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin( Login login ) {
		this.login = login;
	}

	public List<MitarbeiterProjekte> getProjekte() {
		return this.projekte;
	}

	public void setProjekte( List<MitarbeiterProjekte> projekte ) {
		this.projekte = projekte;
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
		if( this.hydroId == null ) {
			if( other.hydroId != null ) {
				return false;
			}
		} else if( !this.hydroId.equals( other.hydroId ) ) {
			return false;
		}
		if( this.id == null ) {
			if( other.id != null ) {
				return false;
			}
		} else if( !this.id.equals( other.id ) ) {
			return false;
		}
		return true;
	}

	// public void setPasswort( String passwort ) {
	// this.passwort = passwort;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.hydroId == null ) ? 0 : this.hydroId.hashCode() );
		result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		return result;
	}

	@Override
	public String toString() {
		return this.hydroId;
	}

}
