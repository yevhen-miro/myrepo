package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( name = "MITARBEITER_PROJEKTE" )
public class MitarbeiterProjekte implements Serializable {

	private static final long serialVersionUID = -1450574994954321862L;

	@SequenceGenerator( name = "SEQ_MITARBEITER_PROJEKTE", sequenceName = "SEQ_MITARBEITER_PROJEKTE",
			allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE, generator = "SEQ_MITARBEITER_PROJEKTE" )
	@Id
	@Column( name = "id" )
	private Long id;

	@ManyToOne( cascade = { CascadeType.ALL } )
	@JoinColumn( name = "MITARBEITER", foreignKey = @ForeignKey( name = "FK_MITARBEITERPROJEKTE_MITARBEITER" ) )
	private Mitarbeiter mitarbeiter;

	@ManyToOne( cascade = { CascadeType.ALL } )
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
