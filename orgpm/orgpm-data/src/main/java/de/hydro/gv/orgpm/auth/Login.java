package de.hydro.gv.orgpm.auth;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import de.hydro.gv.orgpm.data.Mitarbeiter;

@Entity
@Table( name = "LOGIN" )
@NamedQueries( { @NamedQuery( name = "login.find.login",
		query = "Select l FROM Login l, Mitarbeiter m WHERE m.login.id = l.id and m.hydroId = :hydroid" ) } )
public class Login implements Serializable {

	private static final long serialVersionUID = -847133214593977915L;

	@SequenceGenerator( name = "SEQ_LOGIN", sequenceName = "SEQ_LOGIN", allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_LOGIN" )
	@Id
	@Column
	private Long id;

	@OneToOne( mappedBy = "login", cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@PrimaryKeyJoinColumn
	private Mitarbeiter mitarbeiter;

	public Mitarbeiter getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( Mitarbeiter mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
	}

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
