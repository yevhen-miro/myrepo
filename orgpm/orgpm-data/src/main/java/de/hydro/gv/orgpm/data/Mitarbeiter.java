package de.hydro.gv.orgpm.data;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "mitarbeiter")
@NamedQueries({
	@NamedQuery(name="mitarbeiter.delete.all",query="DELETE FROM Mitarbeiter"),
	@NamedQuery(name="mitarbeiter.find.all",query="SELECT m FROM Mitarbeiter AS m"),
	@NamedQuery(name="mitarbeiter.find.by.lastName",query="SELECT m FROM Mitarbeiter AS m WHERE m.vorname= :vorname")
})
public class Mitarbeiter implements Serializable {

	private static final long serialVersionUID = 7859236877492083050L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name = "vorname")
	private String vorname;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gruppe")
	private String gruppe;
	
	@Column(name = "kennung")
	private String mitarbeiterkennung;
	
	@Column(name = "arbeitszeit")
	private double arbeitszeit;
	
	@Column(name = "status")
	private double mitarbeiterStatus;
	
	@Column(name="bemerkung")
	private String bemerkung;
	
	@Column(name = "personalid")
	private int personalNum;
	
	@Column(name = "hydroid")
	private int hydroId;
	
	@Column(name = "kartenid")
	private int kartenNum;
	
	@OneToMany(mappedBy = "mitarbeiter")
	private List<Buchung> buchungen;



	public Mitarbeiter(Integer id,String vorname, String name, String gruppe,String mitarbeiterkennung) {
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
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
	public String getgruppe() {
		return gruppe;
	}
	public void setgruppe(String gruppe) {
		this.gruppe = gruppe;
	}
	
	public Mitarbeiter() {
	}
	
	public Mitarbeiter(String vorname,String name, String gruppe){
		this.vorname = vorname;
		this.name = name;
		this.gruppe = gruppe;
	}
	public String getMitarbeiterkennung() {
		return mitarbeiterkennung;
	}
	public void setMitarbeiterkennung(String mitarbeiterkennung) {
		this.mitarbeiterkennung = mitarbeiterkennung;
	}
	public double getArbeitszeit() {
		return arbeitszeit;
	}
	public void setArbeitszeit(double arbeitszeit) {
		this.arbeitszeit = arbeitszeit;
	}
	public double getMitarbeiterStatus() {
		return mitarbeiterStatus;
	}
	public void setMitarbeiterStatus(double mitarbeiterStatus) {
		this.mitarbeiterStatus = mitarbeiterStatus;
	}
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	public int getPersonalNum() {
		return personalNum;
	}
	public void setPersonalNum(int personalNum) {
		this.personalNum = personalNum;
	}
	public int getHydroId() {
		return hydroId;
	}
	public void setHydroId(int hydroId) {
		this.hydroId = hydroId;
	}
	public int getKartenNum() {
		return kartenNum;
	}
	public void setKartenNum(int kartenNum) {
		this.kartenNum = kartenNum;
	}

	public List<Buchung> getBuchungen() {
		return buchungen;
	}

	public void setBuchungen(List<Buchung> buchungen) {
		this.buchungen = buchungen;
	}
}
