package de.hydro.gv.orgpm.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class Mitarbeiter {//Model View for JSF
	
private Long id;

@Size(min=2,max=30, message="{first.name.min.max}")
@Pattern(regexp="[a-zA-Z]*")
private String vorname;

@Size(min=2,max=30, message="{last.name.min.max}")
@Pattern(regexp="[a-zA-Z]*")
private String name;

private String gruppe;

private String kennung;

private double arbeitszeit;

private double status;

private String bemerkung;

private int personalid;

private int hydroid;

private int kartenid;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getVorname() {
	return vorname;
}

public void setVorname(String vorname) {
	this.vorname = vorname;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getGruppe() {
	return gruppe;
}

public void setGruppe(String gruppe) {
	this.gruppe = gruppe;
}

public String getKennung() {
	return kennung;
}

public void setKennung(String kennung) {
	this.kennung = kennung;
}

public double getArbeitszeit() {
	return arbeitszeit;
}

public void setArbeitszeit(double arbeitszeit) {
	this.arbeitszeit = arbeitszeit;
}

public double getStatus() {
	return status;
}

public void setStatus(double status) {
	this.status = status;
}

public String getBemerkung() {
	return bemerkung;
}

public void setBemerkung(String bemerkung) {
	this.bemerkung = bemerkung;
}

public int getPersonalid() {
	return personalid;
}

public void setPersonalid(int personalid) {
	this.personalid = personalid;
}

public int getHydroid() {
	return hydroid;
}

public void setHydroid(int hydroid) {
	this.hydroid = hydroid;
}

public int getKartenid() {
	return kartenid;
}

public void setKartenid(int kartenid) {
	this.kartenid = kartenid;
}

public de.hydro.gv.orgpm.data.Mitarbeiter convertToEntity(Mitarbeiter mitToConvert) {
	
	de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiterEntity = new de.hydro.gv.orgpm.data.Mitarbeiter();
	mitarbeiterEntity.setId(mitToConvert.getId());
	mitarbeiterEntity.setVorname(mitToConvert.getVorname());
	mitarbeiterEntity.setName(mitToConvert.getName());
	mitarbeiterEntity.setArbeitszeit(mitToConvert.getArbeitszeit());
	mitarbeiterEntity.setBemerkung(mitToConvert.getBemerkung());
	mitarbeiterEntity.setgruppe(mitToConvert.getGruppe());
	mitarbeiterEntity.setHydroId(mitToConvert.getHydroid());
	mitarbeiterEntity.setKartenNum(mitToConvert.getKartenid());
	mitarbeiterEntity.setMitarbeiterkennung(mitToConvert.getKennung());
	mitarbeiterEntity.setMitarbeiterStatus(mitToConvert.getStatus());
	mitarbeiterEntity.setPersonalNum(mitToConvert.getPersonalid());
	return mitarbeiterEntity;
}

public Mitarbeiter convertToModel(de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter) {

	this.setId(mitarbeiter.getId());
	this.setName(mitarbeiter.getName());
	this.setVorname(mitarbeiter.getVorname());
	this.setArbeitszeit(mitarbeiter.getArbeitszeit());
	this.setBemerkung(mitarbeiter.getBemerkung());
	this.setGruppe(mitarbeiter.getgruppe());
	this.setHydroid(mitarbeiter.getHydroId());
	this.setKartenid(mitarbeiter.getKartenNum());
	this.setKennung(mitarbeiter.getMitarbeiterkennung());
	this.setStatus(mitarbeiter.getMitarbeiterStatus());
	this.setPersonalid(mitarbeiter.getPersonalNum());

	return this;
}


}
