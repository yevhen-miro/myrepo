package de.hydro.gv.orgpm.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "MITARBEITER_PROJEKTE" )
public class MitarbeiterProjekte implements Serializable {

	private static final long serialVersionUID = -1450574994954321862L;

	@Id
	private Long id;

	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinColumn( name = "MITARBEITER", foreignKey = @ForeignKey( name = "FK_MITARBEITERPROJEKTE_MITARBEITER" ) )
	private Mitarbeiter mitarbeiter;

	@ManyToOne
	@JoinColumn( name = "PROJEKT", foreignKey = @ForeignKey( name = "FK_MITARBEITERPROJEKTE_PROJEKT" ) )
	private Projekt projekt;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Mitarbeiter getMitarbeiter() {
		return this.mitarbeiter;
	}

	public void setMitarbeiter( Mitarbeiter mitarbeiter ) {
		this.mitarbeiter = mitarbeiter;
	}

	public Projekt getProjekt() {
		return this.projekt;
	}

	public void setProjekt( Projekt projekt ) {
		this.projekt = projekt;
	}

}
