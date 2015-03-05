package de.hydro.gv.orgpm.data;


import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "buchungen")
@NamedQueries({
	@NamedQuery(name="buchung.delete.all",query="DELETE FROM Buchung"),
	@NamedQuery(name="buchung.find.all",query="SELECT b FROM Buchung AS b")
})

public class Buchung implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MITARBEITER", foreignKey = @ForeignKey(name="FK_BUCHUNG_MITARBEITER"))
	private Mitarbeiter mitarbeiter;
	

	@Column(name = "projekt_Id")
	private String projektId;
	
	@Column(name = "aktivität_Id")
	private int aktivitaetId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datum")
	private Calendar datum;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "anfang")
	private Calendar anfangZeit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ende")
	private Calendar endeZeit;
	
	@Column(name = "pause_Von")
	private Calendar pauseVon;
	
	@Column(name = "pause_Bis")
	private Calendar pauseBis;
	
	@Column(name = "stunden")
	private int std;
	
	@Column(name = "minuten")
	private int min;
	
	@Column(name = "tätigkeiten")
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

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public Calendar getAnfangZeit() {
		return anfangZeit;
	}

	public void setAnfangZeit(Calendar anfangZeit) {
		this.anfangZeit = anfangZeit;
	}

	public Calendar getEndeZeit() {
		return endeZeit;
	}

	public void setEndeZeit(Calendar endeZeit) {
		this.endeZeit = endeZeit;
	}

	public Calendar getPauseVon() {
		return pauseVon;
	}

	public void setPauseVon(Calendar pauseVon) {
		this.pauseVon = pauseVon;
	}

	public Calendar getPauseBis() {
		return pauseBis;
	}

	public void setPauseBis(Calendar pauseBis) {
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
