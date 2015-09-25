package de.hydro.gv.orgpm.models;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;

import javax.inject.Inject;

import de.hydro.gv.orgpm.actions.MitarbeiterAktionen;
import de.hydro.gv.orgpm.auth.Login;
import de.hydro.gv.orgpm.auth.RolleEnum;
import de.hydro.gv.orgpm.data.Mitarbeiter;

public class MitarbeiterModel extends Model<Mitarbeiter, MitarbeiterModel> implements Serializable {
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
	private String passwort;
	private Calendar geburtsdatum;
	private Calendar einstellungsdatum;
	private Calendar kuendigungsdatum;
	private Collection<ProjektModel> projekte;
	private RolleEnum rolle;

	public RolleEnum getRolle() {
		return this.rolle;
	}

	public void setRolle( RolleEnum rolle ) {
		this.rolle = rolle;
	}

	private Login login;

	@Inject
	MitarbeiterAktionen mitarbeiterAktionen;

	public Login getLogin() {
		return this.login;
	}

	public void setLogin( Login login ) {
		this.login = login;
	}

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

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort( String passwort ) {
		this.passwort = passwort;
	}

	public Calendar getGeburtsdatum() {
		return this.geburtsdatum;
	}

	public void setGeburtsdatum( Calendar geburtsdatum ) {
		this.geburtsdatum = geburtsdatum;
	}

	public Calendar getEinstellungsdatum() {
		return this.einstellungsdatum;
	}

	public void setEinstellungsdatum( Calendar einstellungsdatum ) {
		this.einstellungsdatum = einstellungsdatum;
	}

	public Calendar getKuendigungsdatum() {
		return this.kuendigungsdatum;
	}

	public void setKuendigungsdatum( Calendar kuendigungsdatum ) {
		this.kuendigungsdatum = kuendigungsdatum;
	}

	public Collection<ProjektModel> getProjekte() {
		return this.projekte;
	}

	public void setProjekte( Collection<ProjektModel> projekte ) {
		this.projekte = projekte;
	}

	private String encryptPassword( String password ) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance( "SHA-256" );
		byte[] passwordBytes = password.getBytes();
		byte[] hash = md.digest( passwordBytes );
		String passwordHash = Base64.getEncoder().encodeToString( hash );
		return passwordHash;
	}

	/*
	 * private String decryptPassword( String password ) throws
	 * NoSuchAlgorithmException { byte[] passwordByte =
	 * DatatypeConverter.parseBase64Binary( password ); // byte[] passwordByte =
	 * Base64.getDecoder().decode( password ); String passwordString = new
	 * String( passwordByte, StandardCharsets.UTF_8 ); return passwordString; }
	 */

	@Override
	public MitarbeiterModel copyToModel() {
		String oldPass;

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
		this.setPasswort( this.entity.getPasswort() );
		this.setEinstellungsdatum( this.entity.getEinstellungsdatum() );
		this.setGeburtsdatum( this.entity.getGeburtsdatum() );
		this.setKuendigungsdatum( this.entity.getKuendigungsdatum() );
		this.setRolle( this.entity.getRolle() );
		// this.setLogin( this.entity.getLogin() );
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
		// this.entity.setPasswort( this.getPasswort() );
		try {
			this.entity.setPasswort( this.encryptPassword( this.getPasswort() ) );
		} catch ( NoSuchAlgorithmException e ) {
			// TODO Auto-generated catch block e.printStackTrace(); }
		}
		this.entity.setGeburtsdatum( this.getGeburtsdatum() );
		this.entity.setEinstellungsdatum( this.getEinstellungsdatum() );
		this.entity.setKuendigungsdatum( this.getKuendigungsdatum() );
		// this.entity.setLogin( this.getLogin() );
		this.entity.setRolle( this.getRolle() );
		return this.entity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( this.id == null ) ? 0 : this.id.hashCode() );
		return result;
	}

}
