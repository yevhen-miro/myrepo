package de.hydro.gv.orgpm.models;

import java.util.Date;

public class Projekt {
	
private long id;
private String begriff;
private String bemerkung;
private String hauptprojekt;
private Double planaufwand;
private String projektGruppe;
private String projektId;
private String projektLeiter;
private String projektName;
private Integer status;
private Date start;
private Date ende;
private Boolean storno;
private Boolean wartung;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getBegriff() {
	return begriff;
}

public void setBegriff(String begriff) {
	this.begriff = begriff;
}

public String getBemerkung() {
	return bemerkung;
}

public void setBemerkung(String bemerkung) {
	this.bemerkung = bemerkung;
}

public String getHauptprojekt() {
	return hauptprojekt;
}

public void setHauptprojekt(String hauptprojekt) {
	this.hauptprojekt = hauptprojekt;
}

public Double getAufwand() {
	return planaufwand;
}

public void setAufwand(Double aufwand) {
	this.planaufwand = aufwand;
}

public String getProjektgruppe() {
	return projektGruppe;
}

public void setProjektgruppe(String projektgruppe) {
	this.projektGruppe = projektgruppe;
}

public String getProjektid() {
	return projektId;
}

public void setProjektid(String projektid) {
	this.projektId = projektid;
}

public String getProjektleiter() {
	return projektLeiter;
}

public void setProjektleiter(String projektleiter) {
	this.projektLeiter = projektleiter;
}

public String getProjektname() {
	return projektName;
}

public void setProjektname(String projektname) {
	this.projektName = projektname;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public Date getStart() {
	return start;
}

public void setStart(Date start) {
	this.start = start;
}

public Date getEnde() {
	return ende;
}

public void setEnde(Date ende) {
	this.ende = ende;
}

public Boolean getStorno() {
	return storno;
}

public void setStorno(Boolean storno) {
	this.storno = storno;
}

public Boolean getWartung() {
	return wartung;
}

public void setWartung(Boolean wartung) {
	this.wartung = wartung;
}

public de.hydro.gv.orgpm.data.Projekt convertToEntity(Projekt projektToConvert) {
	
	de.hydro.gv.orgpm.data.Projekt projektEntity = new de.hydro.gv.orgpm.data.Projekt();
	projektEntity.setId(projektToConvert.getId());
    projektEntity.setBegriff(projektToConvert.getBegriff());
    projektEntity.setBemerkung(projektToConvert.getBemerkung());
    projektEntity.setPlanaufwand(projektToConvert.getAufwand());
    projektEntity.setHauptprojekt(projektToConvert.getHauptprojekt());
    projektEntity.setProjektGruppe(projektToConvert.getProjektgruppe());
    projektEntity.setProjektId(projektToConvert.getProjektid());
    projektEntity.setProjektLeiter(projektToConvert.getProjektleiter());
    projektEntity.setProjektName(projektToConvert.getProjektname());
    projektEntity.setProjektstart(projektToConvert.getStart());
    projektEntity.setProjektende(projektToConvert.getEnde());
    projektEntity.setProjektStatus(projektToConvert.getStatus());
    projektEntity.setWartungsprojekt(projektToConvert.getWartung());
    projektEntity.setStorniertesProjekt(projektToConvert.getStorno());
    
	return projektEntity;
}

public Projekt convertToModel(de.hydro.gv.orgpm.data.Projekt projekt) {

	this.setId(projekt.getId());
	this.setBegriff(projekt.getBegriff());
	this.setBemerkung(projekt.getBemerkung());
	this.setAufwand(projekt.getPlanaufwand());
	this.setHauptprojekt(projekt.getHauptprojekt());
	this.setProjektgruppe(projekt.getProjektGruppe());
	this.setProjektid(projekt.getProjektId());
	this.setProjektleiter(projekt.getProjektLeiter());
	this.setProjektname(projekt.getProjektName());
	this.setStart(projekt.getProjektstart());
	this.setEnde(projekt.getProjektende());
	this.setStatus(projekt.getProjektStatus());
	this.setWartung(projekt.isWartungsprojekt());
	this.setStorno(projekt.isStorniertesProjekt());

	return this;
}


}
