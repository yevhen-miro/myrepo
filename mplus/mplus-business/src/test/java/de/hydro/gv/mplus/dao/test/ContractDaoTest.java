package de.hydro.gv.mplus.dao.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.services.ContractService;



@RunWith(Arquillian.class)
public class ContractDaoTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
				.addPackage(ContractService.class.getPackage())
				.addPackage(ContractDao.class.getPackage())
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	
	@Inject
	private ContractService contractService;
/*	
	private Contract createContract() {
		Contract contract = new Contract ();
		contract.setId(999L);
		contract.setAssumedlmetype("testy");
		return contract;
	}
	*/
	
	@Test
	public void testCreateMitarbeiter() throws Exception {
		
		//Contract contract = createContract();
		
		//contractService.createContract(contract);
		
		//Assert.assertEquals(1,contractService.getAllContracts().size() );
		
	}
}
