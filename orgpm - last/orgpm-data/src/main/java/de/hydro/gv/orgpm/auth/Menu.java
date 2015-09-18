package de.hydro.gv.orgpm.auth;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "MENU" )
public class Menu {

	private static final long serialVersionUID = -2547780072961319666L;

	@Id
	private Long id;

	@Column( name = "NAME", length = 30 )
	@NotNull
	private String name;

	@Column( name = "URL", length = 50 )
	@NotNull
	private String url;

	@Column( name = "BESCHREIBUNG", length = 300 )
	private String beschreibung;

	@OneToOne
	@JoinColumn( name = "VORGAENGER", foreignKey = @ForeignKey( name = "FK_MENU_VORGAENGER" ) )
	private Menu vorgaenger;

	@OneToMany( mappedBy = "menueeintrag" )
	private List<MenuRollen> rollen;

	@Column( name = "POSITION", precision = 3 )
	@NotNull
	private int position;

	public int getPosition() {
		return this.position;
	}

	public void setPosition( int position ) {
		this.position = position;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl( String url ) {
		this.url = url;
	}

	public String getBeschreibung() {
		return this.beschreibung;
	}

	public void setBeschreibung( String beschreibung ) {
		this.beschreibung = beschreibung;
	}

	public List<MenuRollen> getRollen() {
		return this.rollen;
	}

	public void setRollen( List<MenuRollen> rollen ) {
		this.rollen = rollen;
	}

	public Menu getVorgaenger() {
		return this.vorgaenger;
	}

	public void setVorgaenger( Menu vorgaenger ) {
		this.vorgaenger = vorgaenger;
	}

	@Transient
	public List<Rolle> allMenuRoles() {
		LinkedList<Rolle> result = new LinkedList<Rolle>();

		List<MenuRollen> mr = this.getRollen();
		for ( MenuRollen m : mr ) {
			result.add( m.getRolle() );
		}

		return result;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

}
