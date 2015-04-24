package de.hydro.gv.orgpm.models;

import java.io.Serializable;

import de.hydro.gv.orgpm.data.Mitarbeiter;

public class MitarbeiterModel extends Model<Mitarbeiter, MitarbeiterModel> implements Serializable {// Model
																									// View
	private static final long serialVersionUID = -3392669897842233915L;

	private Long id;
	private String vorname;
	private String name;
	private String gruppe;
	private String kennung;
	private Double arbeitszeit;
	private Double status;
	private String bemerkung;
	private Integer personalid;
	private String hydroid;
	private Integer kartenid;

	public MitarbeiterModel( Mitarbeiter mitarbeiter ) {
		super( mitarbeiter );
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname( String vorname ) {
		this.vorname = vorname;
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getGruppe() {
		return this.gruppe;
	}

	public void setGruppe( String gruppe ) {
		this.gruppe = gruppe;
	}

	public String getKennung() {
		return this.kennung;
	}

	public void setKennung( String kennung ) {
		this.kennung = kennung;
	}

	public Double getArbeitszeit() {
		return this.arbeitszeit;
	}

	public void setArbeitszeit( Double arbeitszeit ) {
		this.arbeitszeit = arbeitszeit;
	}

	public Double getStatus() {
		return this.status;
	}

	public void setStatus( Double status ) {
		this.status = status;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung( String bemerkung ) {
		this.bemerkung = bemerkung;
	}

	public Integer getPersonalid() {
		return this.personalid;
	}

	public void setPersonalid( Integer personalid ) {
		this.personalid = personalid;
	}

	public String getHydroid() {
		return this.hydroid;
	}

	public void setHydroid( String hydroid ) {
		this.hydroid = hydroid;
	}

	public Integer getKartenid() {
		return this.kartenid;
	}

	public void setKartenid( Integer kartenid ) {
		this.kartenid = kartenid;
	}

	@Override
	public MitarbeiterModel copyToModel() {
		this.setId( this.entity.getId() );
		this.setName( this.entity.getName() );
		this.setVorname( this.entity.getVorname() );
		this.setArbeitszeit( this.entity.getArbeitszeit() );
		this.setBemerkung( this.entity.getBemerkung() );
		this.setGruppe( this.entity.getGruppe() );
		this.setHydroid( this.entity.getHydroId() );
		this.setKartenid( this.entity.getKartenNum() );
		this.setKennung( this.entity.getMitarbeiterkennung() );
		this.setStatus( this.entity.getMitarbeiterStatus() );
		this.setPersonalid( this.entity.getPersonalNum() );
		return this.model;
	}

	@Override
	public Mitarbeiter copyToEntity() {
		this.entity.setId( this.getId() );
		this.entity.setVorname( this.getVorname() );
		this.entity.setName( this.getName() );
		this.entity.setArbeitszeit( this.getArbeitszeit() );
		this.entity.setBemerkung( this.getBemerkung() );
		this.entity.setGruppe( this.getGruppe() );
		this.entity.setHydroId( this.getHydroid() );
		this.entity.setKartenNum( this.getKartenid() );
		this.entity.setMitarbeiterkennung( this.getKennung() );
		this.entity.setMitarbeiterStatus( this.getStatus() );
		this.entity.setPersonalNum( this.getPersonalid() );
		return this.entity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if( this == obj ) {
			return true;
		}
		if( obj == null ) {
			return false;
		}
		if( this.getClass() != obj.getClass() ) {
			return false;
		}
		MitarbeiterModel other = (MitarbeiterModel) obj;
		if( this.id == null ) {
			if( other.id != null ) {
				return false;
			}
		} else if( !this.id.equals( other.id ) ) {
			return false;
		}
		return true;
	}

}
