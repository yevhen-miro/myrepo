package de.hydro.gv.orgpm.model;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RequestScoped
@Named //Name reference in JSF will be the class name by default
//@Model // Alternative to RequestScoped and Named.
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



}
