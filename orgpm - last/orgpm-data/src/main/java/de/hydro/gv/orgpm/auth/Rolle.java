package de.hydro.gv.orgpm.auth;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "ROLLEN" )
public class Rolle {

	private static final long serialVersionUID = -847133214593977915L;

	@Id
	@Column
	private Long id;

	@Column( name = "NAME", length = 50 )
	private String name;

	@OneToMany( mappedBy = "rolle", fetch = FetchType.EAGER )
	private List<PersonenRollen> personen;

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public List<PersonenRollen> getPersonen() {
		return this.personen;
	}

	public void setPersonen( List<PersonenRollen> personen ) {
		this.personen = personen;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

}
