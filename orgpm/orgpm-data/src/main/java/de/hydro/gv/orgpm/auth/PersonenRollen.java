package de.hydro.gv.orgpm.auth;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "PERSONEN_ROLLEN" )
public class PersonenRollen {

	private static final long serialVersionUID = -1450574993244321862L;

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn( name = "PERSON", foreignKey = @ForeignKey( name = "FK_PERSONENROLLEN_PERSON" ) )
	private Person person;

	@ManyToOne
	@JoinColumn( name = "ROLLE", foreignKey = @ForeignKey( name = "FK_PERSONENROLLEN_ROLLE" ) )
	private Rolle rolle;

	public Person getPerson() {
		return this.person;
	}

	public void setPerson( Person person ) {
		this.person = person;
	}

	public Rolle getRolle() {
		return this.rolle;
	}

	public void setRolle( Rolle rolle ) {
		this.rolle = rolle;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

}
