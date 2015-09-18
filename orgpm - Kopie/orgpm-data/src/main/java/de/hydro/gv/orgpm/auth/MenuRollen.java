package de.hydro.gv.orgpm.auth;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "MENU_ROLLEN" )
public class MenuRollen {

	private static final long serialVersionUID = 627452241644786039L;

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn( name = "MENU", foreignKey = @ForeignKey( name = "FK_MENUROLLEN_MENU" ) )
	private Menu menueeintrag;

	@ManyToOne
	@JoinColumn( name = "ROLLE", foreignKey = @ForeignKey( name = "FK_MENUROLLEN_ROLLE" ) )
	private Rolle rolle;

	public Menu getMenueeintrag() {
		return this.menueeintrag;
	}

	public void setMenueeintrag( Menu menueeintrag ) {
		this.menueeintrag = menueeintrag;
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
