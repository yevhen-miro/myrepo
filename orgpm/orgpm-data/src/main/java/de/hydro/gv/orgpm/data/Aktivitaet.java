package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "aktivitaeten")
@NamedQueries({
	@NamedQuery(name="aktivitaet.delete.all",query="DELETE FROM Aktivitaet"),
	@NamedQuery(name="aktivitaet.find.all",query="SELECT a FROM Aktivitaet AS a")
})
public class Aktivitaet implements Serializable{

	private static final long serialVersionUID = -1773699513191865605L;
	
	@SequenceGenerator(name = "SEQ_AKTIVITAET", sequenceName = "SEQ_AKTIVITAET", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_AKTIVITAET")
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="aktivitaet_nr")
	private Integer aktivitaetNr;
	
	@Column(name="text")
	private String aktivitaetText;
	
	@Column(name="status")
	private boolean aktivitaetStatus;
	
	@Column(name="aufwand")
	private Double planaufwand;
	
	@Column(name="bemerkung")
	private String bemerkung;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROJEKT", foreignKey = @ForeignKey(name="FK_AKTIVITAET_PROJEKT"))
	private Projekt projekt;
}
