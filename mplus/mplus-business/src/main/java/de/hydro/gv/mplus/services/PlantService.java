package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.dao.PlantDao;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;

@Stateless
public class PlantService {
	
@Inject
private PlantDao plantDao;	

public List<Plant> getAllPlants() throws Exception {
	return this.plantDao.readAllPlants();
}

public void addPlant(Plant pPlant) {
	pPlant.setPlantCreated(new Date());
	pPlant.setPlantCreatedBy("21E46F33-6AED-4B40-BC52-0C2B11135079");
	plantDao.createPlant(pPlant);
}

public void updatePlant(Plant pPlant) {
	pPlant = plantDao.findPlantById(5L);
	pPlant.setPlantUpdated(new Date());
	pPlant.setPlantUpdatedBy("21E46F33-6AED-4B40-BC52-0C2B11135079");
	plantDao.updatePlant(pPlant);
}


}
