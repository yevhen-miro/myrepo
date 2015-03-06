package de.hydro.gv.orgpm.model;

import java.util.Calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.hydro.gv.orgpm.data.Mitarbeiter;

@RequestScoped
@Named //Name reference in JSF will be the class name by default
//@Model // Alternative to RequestScoped and Named.
public class Buchung {//Model View for JSF
	
	private Long id;
	
	private Mitarbeiter mitarbeiter;
	
	private String projektId;
	
	private int aktivitaetId;
	
	private Calendar datum;
	
	private Calendar anfangZeit;
	
	private Calendar endeZeit;
	
	private Calendar pauseVon;
	
	private Calendar pauseBis;
	
	private int std;
	
	private int min;
	
	private String taetigkeiten;
	
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
