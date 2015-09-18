package de.hydro.gv.orgpm.models;

import java.io.Serializable;
import java.util.Date;

import de.hydro.gv.orgpm.data.Projekt;

public class ProjektModel extends Model<Projekt, ProjektModel> implements Serializable {

	private static final long serialVersionUID = 6347136558814328048L;

	private Long id;
	private String begriff;
	private String bemerkung;
	private String hauptprojekt;
	private Double planaufwand;
	private String projektGruppe;
	private String projekt;
	private String projektLeiter;
	private String projektName;
	private Integer status;
	private Date start;
	private Date ende;
	private Boolean storno;
	private Boolean wartung;

	public ProjektModel( de.hydro.gv.orgpm.data.Projekt projekt ) {
		super( projekt );
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getBegriff() {
		return this.begriff;
	}

	public void setBegriff( String begriff ) {
		this.begriff = begriff;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung( String bemerkung ) {
		this.bemerkung = bemerkung;
	}

	public String getHauptprojekt() {
		return this.hauptprojekt;
	}

	public void setHauptprojekt( String hauptprojekt ) {
		this.hauptprojekt = hauptprojekt;
	}

	public Double getAufwand() {
		return this.planaufwand;
	}

	public void setAufwand( Double aufwand ) {
		this.planaufwand = aufwand;
	}

	public String getProjektgruppe() {
		return this.projektGruppe;
	}

	public void setProjektgruppe( String projektgruppe ) {
		this.projektGruppe = projektgruppe;
	}

	public String getProjekt() {
		return this.projekt;
	}

	public void setProjekt( String projekt ) {
		this.projekt = projekt;
	}

	public String getProjektleiter() {
		return this.projektLeiter;
	}

	public void setProjektleiter( String projektleiter ) {
		this.projektLeiter = projektleiter;
	}

	public String getProjektname() {
		return this.projektName;
	}

	public void setProjektname( String projektname ) {
		this.projektName = projektname;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}

	public Date getStart() {
		return this.start;
	}

	public void setStart( Date start ) {
		this.start = start;
	}

	public Date getEnde() {
		return this.ende;
	}

	public void setEnde( Date ende ) {
		this.ende = ende;
	}

	public Boolean getStorno() {
		return this.storno;
	}

	public void setStorno( Boolean storno ) {
		this.storno = storno;
	}

	public Boolean getWartung() {
		return this.wartung;
	}

	public void setWartung( Boolean wartung ) {
		this.wartung = wartung;
	}

	@Override
	public ProjektModel copyToModel() {
		this.setId( this.entity.getId() );
		this.setBegriff( this.entity.getBegriff() );
		this.setBemerkung( this.entity.getBemerkung() );
		this.setAufwand( this.entity.getPlanaufwand() );
		this.setHauptprojekt( this.entity.getHauptprojekt() );
		this.setProjektgruppe( this.entity.getProjektGruppe() );
		this.setProjekt( this.entity.getProjektId() );
		this.setProjektleiter( this.entity.getProjektLeiter() );
		this.setProjektname( this.entity.getProjektName() );
		this.setStart( this.entity.getProjektstart() );
		this.setEnde( this.entity.getProjektende() );
		this.setStatus( this.entity.getProjektStatus() );
		this.setWartung( this.entity.isWartungsprojekt() );
		this.setStorno( this.entity.isStorniertesProjekt() );
		return this.model;
	}

	@Override
	public Projekt copyToEntity() {
		this.entity.setId( this.getId() );
		this.entity.setBegriff( this.getBegriff() );
		this.entity.setBemerkung( this.getBemerkung() );
		this.entity.setPlanaufwand( this.getAufwand() );
		this.entity.setHauptprojekt( this.getHauptprojekt() );
		this.entity.setProjektGruppe( this.getProjektgruppe() );
		this.entity.setProjektId( this.getProjekt() );
		this.entity.setProjektLeiter( this.getProjektleiter() );
		this.entity.setProjektName( this.getProjektname() );
		this.entity.setProjektstart( this.getStart() );
		this.entity.setProjektende( this.getEnde() );
		this.entity.setProjektStatus( this.getStatus() );
		this.entity.setWartungsprojekt( this.getWartung() );
		this.entity.setStorniertesProjekt( this.getStorno() );
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
		ProjektModel other = (ProjektModel) obj;
		if( this.projekt == null ) {
			if( other.projekt != null ) {
				return false;
			}
		} else if( !this.projekt.equals( other.projekt ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
