package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
public class CBUService {
	
@Inject
private CBUDao cbuDao;	

public List<CBU> getAllCBUs() throws Exception {
	return this.cbuDao.readAllCBUs();
}

public List<CBU> findCbuByBU(BU pBU) throws Exception {
	return this.cbuDao.findCBUsByBU(pBU);
}


}
