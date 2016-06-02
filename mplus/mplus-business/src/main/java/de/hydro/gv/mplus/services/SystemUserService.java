package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.dao.PlantDao;
import de.hydro.gv.mplus.dao.SystemUserDao;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemRole;
import de.hydro.gv.mplus.data.SystemUser;

@Stateless
public class SystemUserService {
	
@Inject
private SystemUserDao userDao;	

public void addUser(SystemUser pUser) {
	pUser.setCreated(new Date());
	pUser.setCreatedBy("21E46F33-6AED-4B40-BC52-0C2B11135079");
	userDao.createUser(pUser);
}

public void updateUser(SystemUser pUser) {
	pUser = userDao.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079");
	pUser.setUpdated(new Date());
	pUser.setUpdatedBy("21E46F33-6AED-4B40-BC52-0C2B11135079");
	userDao.updateUser(pUser);
}

public List<SystemUser> findAllUsers () {
	return userDao.findAllUsers();
}

public List<String> findAllUserNames () {
	return userDao.findAllUsersNames();
}

public SystemUser findUserById (String pId) {
	return userDao.findUserById(pId);
}

public SystemUser findUserByHydroId (String pHydroId) {
	return userDao.findUserByHydroId(pHydroId);
}

public SystemRole findRoleByHydroId (String pHydroId) {
	return userDao.findRoleByHydroId(pHydroId);
}

}
