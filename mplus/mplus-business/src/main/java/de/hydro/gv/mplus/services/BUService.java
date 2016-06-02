package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.BUDao;
import de.hydro.gv.mplus.dao.CBUDao;
import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.dao.PlantDao;
import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;

@Stateless
public class BUService {
	
@Inject
private BUDao buDao;	

public List<BU> getAllBUs() throws Exception {
	return this.buDao.readAllBUs();
}

public BU getBUbyCBU(CBU pCBU) throws Exception {
	return this.buDao.findByCBU(pCBU);
}

}
