package de.hydro.gv.orgpm.dao.tests;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.orgpm.dao.MitarbeiterDao;
import de.hydro.gv.orgpm.data.Mitarbeiter;

@RunWith( Arquillian.class )
public class MitarbeiterDaoTest extends SuperTest {

	@Inject
	MitarbeiterDao mitarbeiterDao;

	@Test
	public void testCreateMitarbeiter() {

		Mitarbeiter mitarbeiter = new Mitarbeiter();

		this.mitarbeiterDao.createMitarbeiter( mitarbeiter );

		Assert.assertEquals( 1, this.mitarbeiterDao.readAllMitarbeiter().size() );

	}

	// @Test
	// public void testReadAllMitarbeiter() {
	//
	// for ( int pos = 0; pos < 3; pos++ ) {
	// this.mitarbeiterDao.createMitarbeiter( new Mitarbeiter() );
	// }
	// Assert.assertEquals( 3, this.mitarbeiterDao.readAllMitarbeiter().size()
	// );
	//
	// }
	//
	// @Test
	// public void testUpdateMitarbeiter() throws Exception {
	// Mitarbeiter mitarbeiter = new Mitarbeiter();
	// this.mitarbeiterDao.createMitarbeiter( mitarbeiter );
	// mitarbeiter.setVorname( "Yevheniy" );
	// this.mitarbeiterDao.updateMitarbeiter( mitarbeiter );
	// Assert.assertEquals( "Yevheniy",
	// this.mitarbeiterDao.readAllMitarbeiter().get( 0 ).getVorname() );
	//
	// }
	//
	// @Test
	// public void testDeleteMitarbeiter() {
	// Mitarbeiter mitarbeiter = new Mitarbeiter();
	//
	// this.mitarbeiterDao.createMitarbeiter( mitarbeiter );
	//
	// this.mitarbeiterDao.deleteMitarbeiter( mitarbeiter );
	// Assert.assertEquals( 0, this.mitarbeiterDao.readAllMitarbeiter().size()
	// );
	//
	// }
	//
	// @Test
	// public void testGetQuery() {
	// Mitarbeiter mitarbeiter = new Mitarbeiter();
	// this.mitarbeiterDao.createMitarbeiter( mitarbeiter );
	// this.mitarbeiterDao.executeQueryWithResults( "mitarbeiter.find.all" );
	// Assert.assertEquals( 1, this.mitarbeiterDao.readAllMitarbeiter().size()
	// );
	// }
	//
	// @Test
	// public void readAndConvertMitarbeiter() {
	// @SuppressWarnings( "unchecked" )
	// List<de.hydro.gv.orgpm.data.Mitarbeiter> results =
	// (List<de.hydro.gv.orgpm.data.Mitarbeiter>) this.mitarbeiterDao
	// .executeQueryWithResults( "mitarbeiter.find.all" );
	// ArrayList<Mitarbeiter> arrayList = new ArrayList<Mitarbeiter>();
	// for ( de.hydro.gv.orgpm.data.Mitarbeiter mitarbeiter : results ) {
	// arrayList.add( this.convToModel( mitarbeiter ) );
	// }
	// }
	//
	// private Mitarbeiter convToModel( de.hydro.gv.orgpm.data.Mitarbeiter
	// mitarbeiter ) {
	// Mitarbeiter mitarbeiterModel = new Mitarbeiter();
	// mitarbeiterModel.setName( mitarbeiter.getName() );
	// mitarbeiterModel.setVorname( mitarbeiter.getVorname() );
	// mitarbeiterModel.setArbeitszeit( mitarbeiter.getArbeitszeit() );
	// mitarbeiterModel.setBemerkung( mitarbeiter.getBemerkung() );
	// mitarbeiterModel.setGruppe( mitarbeiter.getGruppe() );
	// mitarbeiterModel.setHydroId( mitarbeiter.getHydroId() );
	// mitarbeiterModel.setKartenNum( mitarbeiter.getKartenNum() );
	// mitarbeiterModel.setMitarbeiterkennung(
	// mitarbeiter.getMitarbeiterkennung() );
	// mitarbeiterModel.setMitarbeiterStatus( mitarbeiter.getMitarbeiterStatus()
	// );
	// mitarbeiterModel.setPersonalNum( mitarbeiter.getPersonalNum() );
	//
	// return mitarbeiterModel;
	// }
}
