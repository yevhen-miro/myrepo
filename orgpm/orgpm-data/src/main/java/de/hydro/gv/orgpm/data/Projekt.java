package de.hydro.gv.orgpm.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "projekte")
@NamedQueries({
	@NamedQuery(name="proejekt.delete.all",query="DELETE FROM Projekt"),
	@NamedQuery(name="projekt.find.all",query="SELECT p FROM Projekt AS p"),
	@NamedQuery(name="projekt.find.by.name",query="SELECT p FROM Projekt AS p WHERE p.projektId= :proejektId")
})
public class Projekt implements Serializable{


	private static final long serialVersionUID = 3433657128651489265L;
	
	@SequenceGenerator(name = "SEQ_PROJEKT", sequenceName = "SEQ_PROJEKT", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_PROJEKT")
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name = "projekt_id")
	private String projektId;
	
	@Column(name = "projekt_name")
	private String projektName;
	
	@Column(name = "hauptprojekt")
	private String hauptprojekt;
	
	@Column(name = "begriff")
	private String Begriff;
	
	@Column(name = "projektgruppe")
	private String projektGruppe;
	
	@Column(name = "projektleiter")
	private String projektLeiter;
	
	@Column(name = "aufwand")
	private double planaufwand;
	
	@Column(name = "start")
	private Date projektstart;
	
	@Column(name = "ende")
	private Date projektende;
	
	@Column(name = "storno")
	private boolean storniertesProjekt;
	
	@Column(name = "status")
	private Integer projektStatus;
	
	@Column(name = "bemerkung")
	private String bemerkung;
	
	@Column(name = "wartung")
	private boolean wartungsprojekt;
	
	@OneToMany(mappedBy = "projekt")
	private List<Aktivitaet> aktivitaeten;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjektId() {
		return projektId;
	}

	public void setProjektId(String projektId) {
		this.projektId = projektId;
	}

	public String getProjektName() {
		return projektName;
	}

	public void setProjektName(String projektName) {
		this.projektName = projektName;
	}

	public String getHauptprojekt() {
		return hauptprojekt;
	}

	public void setHauptprojekt(String hauptprojekt) {
		this.hauptprojekt = hauptprojekt;
	}

	public String getBegriff() {
		return Begriff;
	}

	public void setBegriff(String begriff) {
		Begriff = begriff;
	}

	public String getProjektGruppe() {
		return projektGruppe;
	}

	public void setProjektGruppe(String projektGruppe) {
		this.projektGruppe = projektGruppe;
	}

	public String getProjektLeiter() {
		return projektLeiter;
	}

	public void setProjektLeiter(String projektLeiter) {
		this.projektLeiter = projektLeiter;
	}

	public double getPlanaufwand() {
		return planaufwand;
	}

	public void setPlanaufwand(double planaufwand) {
		this.planaufwand = planaufwand;
	}

	public Date getProjektstart() {
		return projektstart;
	}

	public void setProjektstart(Date projektstart) {
		this.projektstart = projektstart;
	}

	public Date getProjektende() {
		return projektende;
	}

	public void setProjektende(Date projektende) {
		this.projektende = projektende;
	}

	public boolean isStorniertesProjekt() {
		return storniertesProjekt;
	}

	public void setStorniertesProjekt(boolean storniertesProjekt) {
		this.storniertesProjekt = storniertesProjekt;
	}

	public double getProjektStatus() {
		return projektStatus;
	}

	public void setProjektStatus(Integer projektStatus) {
		this.projektStatus = projektStatus;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public boolean isWartungsprojekt() {
		return wartungsprojekt;
	}

	public void setWartungsprojekt(boolean wartungsprojekt) {
		this.wartungsprojekt = wartungsprojekt;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
