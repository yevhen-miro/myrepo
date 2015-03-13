package de.hydro.gv.orgpm.data;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table (name = "buchungen")
@NamedQueries({
	@NamedQuery(name="buchung.delete.all",query="DELETE FROM Buchung"),
	@NamedQuery(name="buchung.find.all",query="SELECT b FROM Buchung AS b"),
	@NamedQuery(name="buchung.find.buchung.by.mitarbeiter",query="SELECT b FROM Buchung b INNER JOIN b.mitarbeiter m "+
																 "WHERE m.hydroId = :hydroid")
})

public class Buchung implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;
	
	@SequenceGenerator(name = "SEQ_BUCHUNGEN", sequenceName = "SEQ_BUCHUNGEN", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_BUCHUNGEN")
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MITARBEITER", foreignKey = @ForeignKey(name="FK_BUCHUNG_MITARBEITER"))
	private Mitarbeiter mitarbeiter;
	

	@Column(name = "projekt_Id")
	private String projektId;
	
	@Column(name = "aktivitaet_Id")
	private int aktivitaetId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datum")
	private Date datum;
	
	
	@Temporal(TemporalType.TIME)
	@Column(name = "anfang")
	private Date anfangZeit;

	@Temporal(TemporalType.TIME)
	@Column(name = "ende")
	private Date endeZeit;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "pause_Von")
	private Date pauseVon;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "pause_Bis")
	private Date pauseBis;
	
	@Column(name = "stunden")
	private int std;
	
	@Column(name = "minuten")
	private int min;
	
	@Column(name = "taetigkeiten")
	private String taetigkeiten;
	
	@Column(name = "wartung_Id")
	private int wartungId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public String getProjektId() {
		return projektId;
	}

	public void setProjektId(String projektId) {
		this.projektId = projektId;
	}

	public int getAktivitaetId() {
		return aktivitaetId;
	}

	public void setAktivitaetId(int aktivitaetId) {
		this.aktivitaetId = aktivitaetId;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getAnfangZeit() {
		return anfangZeit;
	}

	public void setAnfangZeit(Date anfangZeit) {
		this.anfangZeit = anfangZeit;
	}

	public Date getEndeZeit() {
		return endeZeit;
	}

	public void setEndeZeit(Date endeZeit) {
		this.endeZeit = endeZeit;
	}

	public Date getPauseVon() {
		return pauseVon;
	}

	public void setPauseVon(Date pauseVon) {
		this.pauseVon = pauseVon;
	}

	public Date getPauseBis() {
		return pauseBis;
	}

	public void setPauseBis(Date pauseBis) {
		this.pauseBis = pauseBis;
	}

	public int getStd() {
		return std;
	}

	public void setStd(int std) {
		this.std = std;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getTaetigkeiten() {
		return taetigkeiten;
	}

	public void setTaetigkeiten(String taetigkeiten) {
		this.taetigkeiten = taetigkeiten;
	}

	public int getWartungId() {
		return wartungId;
	}

	public void setWartungId(int wartungId) {
		this.wartungId = wartungId;
	}
	
	
	
}
