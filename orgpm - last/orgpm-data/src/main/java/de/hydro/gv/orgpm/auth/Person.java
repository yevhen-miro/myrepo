package de.hydro.gv.orgpm.auth;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "PERSONEN" )
public class Person {

	private static final long serialVersionUID = -3918375508069364423L;

	@Id
	@Column
	private Long Id;

	@Column( name = "VORNAME", length = 30 )
	@NotNull
	private String vorname;

	@Column( name = "NACHNAME", length = 50 )
	@NotNull
	private String nachname;

	@Column( name = "PASSWORT", length = 300 )
	@NotNull
	private String passwort;

	@Column( name = "GEBURTSDATUM" )
	@Temporal( TemporalType.DATE )
	private Calendar geburtsdatum;

	@Column( name = "EINSTELLUNGSDATUM" )
	@Temporal( TemporalType.DATE )
	private Calendar einstellungsdatum;

	@Column( name = "KUENDIGUNGSDATUM" )
	@Temporal( TemporalType.DATE )
	private Calendar kuendigungsdatum;

	@Column( name = "HYDROID", length = 10, unique = true )
	@NotNull
	private String hydroid;

	@Column( name = "PERSONALNUMMER", length = 10 )
	@NotNull
	private String personalnummer;

	@OneToMany( mappedBy = "person", fetch = FetchType.EAGER )
	private List<PersonenRollen> rollen;

	public Long getId() {
		return this.Id;
	}

	public void setId( Long id ) {
		this.Id = id;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname( String vorname ) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname( String nachname ) {
		this.nachname = nachname;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort( String passwort ) {
		this.passwort = passwort;
	}

	public Calendar getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum( Calendar geburtsdatum ) {
		this.geburtsdatum = geburtsdatum;
	}

	public Calendar getEinstellungsdatum() {
		return this.einstellungsdatum;
	}

	public void setEinstellungsdatum( Calendar einstellungsdatum ) {
		this.einstellungsdatum = einstellungsdatum;
	}

	public Calendar getKuendigungsdatum() {
		return this.kuendigungsdatum;
	}

	public void setKuendigungsdatum( Calendar kuendigungsdatum ) {
		this.kuendigungsdatum = kuendigungsdatum;
	}

	public String getHydroid() {
		return this.hydroid;
	}

	public void setHydroid( String hydroid ) {
		this.hydroid = hydroid;
	}

	public String getPersonalnummer() {
		return this.personalnummer;
	}

	public void setPersonalnummer( String personalnummer ) {
		this.personalnummer = personalnummer;
	}

	public List<PersonenRollen> getRollen() {
		return this.rollen;
	}

	public void setRollen( List<PersonenRollen> rollen ) {
		this.rollen = rollen;
	}

	@Transient
	public List<Rolle> allUserRoles() {
		LinkedList<Rolle> result = new LinkedList<Rolle>();

		List<PersonenRollen> pr = this.getRollen();
		for ( PersonenRollen p : pr ) {
			result.add( p.getRolle() );
		}

		return result;
	}
}
