package de.hydro.gv.orgpm.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "LOGIN" )
@NamedQueries( { @NamedQuery( name = "login.find.login",
		query = "Select l FROM Login l, Mitarbeiter m WHERE m.login.id = l.id and m.hydroId = :hydroid" ) } )
public class Login implements Serializable {

	private static final long serialVersionUID = -847133214593977915L;

	@Id
	@Column
	private Long id;

	@NotNull
	@Column( name = "PASSWORD" )
	private String password;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

}
