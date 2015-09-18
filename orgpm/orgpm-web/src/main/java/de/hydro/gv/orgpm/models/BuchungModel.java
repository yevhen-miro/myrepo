package de.hydro.gv.orgpm.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hydro.gv.orgpm.data.Aktivitaet;
import de.hydro.gv.orgpm.data.Buchung;
import de.hydro.gv.orgpm.data.Projekt;

@ManagedBean
@ViewScoped
public class BuchungModel extends Model<Buchung, BuchungModel> implements Serializable {

	private static final long serialVersionUID = -2626028877968848831L;

	private Long id;
	private ProjektModel projekt;
	private AktivitaetModel aktivitaet;
	private Date datum;
	private Date anfangZeit;
	private Date endeZeit;
	private Date pauseVon;
	private Date pauseBis;
	private Long std;
	private Long duration;
	private Long min;
	private String taetigkeiten;
	private Integer wartungId;
	private String hydroid;
	private Collection<AktivitaetModel> activities;

	private Date initDate = new Date();

	public BuchungModel( Buchung buchung ) {
		super( buchung );
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate( Date initDate ) {
		this.initDate = initDate;
	}

	public Long getDuration() {
		return this.duration;
	}

	public void setDuration( Long duration ) {
		this.duration = duration;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public ProjektModel getProjekt() {
		return this.projekt;
	}

	public void setProjekt( ProjektModel projekt ) {
		this.projekt = projekt;
	}

	public AktivitaetModel getAktivitaet() {
		return this.aktivitaet;
	}

	public void setAktivitaet( AktivitaetModel aktivitaet ) {
		this.aktivitaet = aktivitaet;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum( Date datum ) {
		this.datum = datum;
	}

	public Date getAnfangZeit() {
		return this.anfangZeit;
	}

	public void setAnfangZeit( Date anfangZeit ) {
		this.anfangZeit = anfangZeit;
	}

	public Date getEndeZeit() {
		return this.endeZeit;
	}

	public void setEndeZeit( Date endeZeit ) {
		this.endeZeit = endeZeit;
	}

	public Date getPauseVon() {
		return this.pauseVon;
	}

	public void setPauseVon( Date pauseVon ) {
		this.pauseVon = pauseVon;
	}

	public Date getPauseBis() {
		return this.pauseBis;
	}

	public void setPauseBis( Date pauseBis ) {
		this.pauseBis = pauseBis;
	}

	public Long getStd() {
		return this.std;
	}

	public void setStd( Long std ) {
		this.std = std;
	}

	public Long getMin() {
		return this.min;
	}

	public void setMin( Long min ) {
		this.min = min;
	}

	public String getTaetigkeiten() {
		return this.taetigkeiten;
	}

	public void setTaetigkeiten( String taetigkeiten ) {
		this.taetigkeiten = taetigkeiten;
	}

	public Integer getWartungId() {
		return this.wartungId;
	}

	public void setWartungId( Integer wartungId ) {
		this.wartungId = wartungId;
	}

	public String getHydroid() {
		return this.hydroid;
	}

	public void setHydroid( String hydroid ) {
		this.hydroid = hydroid;
	}

	public Collection<AktivitaetModel> getActivities() {
		return this.activities;
	}

	public void setActivities( Collection<AktivitaetModel> activities ) {
		this.activities = activities;
	}

	@Override
	public BuchungModel copyToModel() {
		this.setId( this.entity.getId() );
		this.setAktivitaet( new AktivitaetModel( this.entity.getAktivitaetId() != null ? this.entity.getAktivitaetId()
				: new Aktivitaet() ) );
		this.setAnfangZeit( this.entity.getAnfangZeit() );
		this.setDatum( this.entity.getDatum() );
		this.setEndeZeit( this.entity.getEndeZeit() );
		this.setMin( this.entity.getMin() );
		this.setPauseBis( this.entity.getPauseBis() );
		this.setPauseVon( this.entity.getPauseVon() );
		this.setProjekt( new ProjektModel( this.entity.getProjekt() != null ? this.entity.getProjekt() : new Projekt() ) );
		this.setStd( this.entity.getStd() );
		this.setTaetigkeiten( this.entity.getTaetigkeiten() );
		this.setWartungId( this.entity.getWartungId() );
		this.setHydroid( this.entity.getHydroid() );
		return this.model;
	}

	@Override
	public Buchung copyToEntity() {
		this.duration = this.endeZeit.getTime() - this.anfangZeit.getTime();
		Long aZeit = this.anfangZeit.getTime();
		Long eZeit = this.endeZeit.getTime();
		long minutes = TimeUnit.MILLISECONDS.toMinutes( this.duration );
		this.entity.setId( this.getId() );
		this.entity.setAktivitaetId( this.getAktivitaet() != null ? this.getAktivitaet().convertToEntity() : null );
		this.entity.setAnfangZeit( this.getAnfangZeit() );
		this.entity.setDatum( this.getDatum() );
		this.entity.setEndeZeit( this.getEndeZeit() );
		this.entity
				.setPauseBis( ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) >= 700 || TimeUnit.MILLISECONDS
						.toMinutes( eZeit ) >= 700 )
						&& ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) < 740 || TimeUnit.MILLISECONDS.toMinutes( eZeit ) < 740 ) ? new Date(
						44400000 ) : null );
		this.entity
				.setPauseVon( ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) >= 700 || TimeUnit.MILLISECONDS
						.toMinutes( eZeit ) >= 700 )
						&& ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) < 740 || TimeUnit.MILLISECONDS.toMinutes( eZeit ) < 740 ) ? new Date(
						42000000 ) : null );
		this.entity.setProjekt( this.getProjekt() != null ? this.getProjekt().convertToEntity() : null );
		this.entity
				.setStd( TimeUnit.MINUTES.toHours( ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) >= 700 || TimeUnit.MILLISECONDS
						.toMinutes( eZeit ) >= 700 )
						&& ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) < 740 || TimeUnit.MILLISECONDS.toMinutes( eZeit ) < 740 ) ? minutes - 40
						: minutes ) );
		this.entity
				.setMin( ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) >= 700 || TimeUnit.MILLISECONDS.toMinutes( eZeit ) >= 700 )
						&& ( TimeUnit.MILLISECONDS.toMinutes( aZeit ) < 740 || TimeUnit.MILLISECONDS.toMinutes( eZeit ) < 740 ) ? minutes - 40
						: minutes );
		this.entity.setTaetigkeiten( this.getTaetigkeiten() );
		this.entity.setWartungId( this.getWartungId() );
		this.entity.setHydroid( this.getHydroid() );
		return this.entity;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
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
		BuchungModel other = (BuchungModel) obj;
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
