package de.hydro.gv.orgpm.models;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.hydro.gv.orgpm.data.Mitarbeiter;

public class Buchung {
	
	private Long id;
	
	private Mitarbeiter mitarbeiter;
	
	private String projektId;
	
	private int aktivitaetId;
	
	private Date datum;
	
	private Date anfangZeit;
	
	private Date endeZeit;
	
	private Date pauseVon;
	
	private Date pauseBis;
	
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

	public de.hydro.gv.orgpm.data.Buchung convertToEntity(Buchung buchungToConvert) {
		
		de.hydro.gv.orgpm.data.Buchung buchungEntity = new de.hydro.gv.orgpm.data.Buchung();
		buchungEntity.setId(buchungToConvert.getId());
		buchungEntity.setAktivitaetId(buchungToConvert.getAktivitaetId());
		buchungEntity.setAnfangZeit(buchungToConvert.getAnfangZeit());
		buchungEntity.setDatum(buchungToConvert.getDatum());
		buchungEntity.setEndeZeit(buchungToConvert.getEndeZeit());
		buchungEntity.setMin(buchungToConvert.getMin());
		buchungEntity.setPauseBis(buchungToConvert.getPauseBis());
		buchungEntity.setPauseVon(buchungToConvert.getPauseVon());
		buchungEntity.setProjektId(buchungToConvert.getProjektId());
		buchungEntity.setStd(buchungToConvert.getStd());
		buchungEntity.setTaetigkeiten(buchungToConvert.getTaetigkeiten());
		buchungEntity.setWartungId(buchungToConvert.getWartungId());
		buchungEntity.setMitarbeiter(buchungToConvert.getMitarbeiter());

		return buchungEntity;
	}
	
	public Buchung convertToModel(de.hydro.gv.orgpm.data.Buchung buchung) {
		
		this.setId(buchung.getId());
		this.setAktivitaetId(buchung.getAktivitaetId());
		this.setAnfangZeit(buchung.getAnfangZeit());
		this.setDatum(buchung.getDatum());
		this.setEndeZeit(buchung.getEndeZeit());
		this.setMin(buchung.getMin());
		this.setPauseBis(buchung.getPauseBis());
		this.setPauseVon(buchung.getPauseVon());
		this.setProjektId(buchung.getProjektId());
		this.setStd(buchung.getStd());
		this.setTaetigkeiten(buchung.getTaetigkeiten());
		this.setWartungId(buchung.getWartungId());

		return this;
	}
}
