package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.dao.PlantDao;
import de.hydro.gv.mplus.dao.SystemUserDao;
import de.hydro.gv.mplus.dao.TimeDateDao;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.data.TimeDate;

@Stateless
public class TimeDateService {
	
@Inject
private TimeDateDao timeDateDao;	

public TimeDate findDateById(Long pId) {
	return timeDateDao.findDateById(pId);
}

public TimeDate findDateIdByDate(Date pDate) {
	return timeDateDao.findDateIdByDate(pDate);
}

public Date findDateIdByTimeDate(TimeDate pTimeDate) {
	return timeDateDao.findDateIdByTimeDate(pTimeDate);
}

}
