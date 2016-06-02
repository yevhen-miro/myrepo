package de.hydro.gv.mplus.actions;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.services.PlantService;

@ViewScoped
@Named
public class PlantActions implements Serializable {

private static final long serialVersionUID = 2279216426451381465L;

private Plant actPlant;

private List<Plant> plants;
	

@Inject
private PlantService plantService;

private List<Plant> cachedPlantsList;

public List<Plant> getAllPlants() throws Exception {

	if( this.cachedPlantsList == null ) {
		this.cachedPlantsList = this.plantService.getAllPlants();

	}
	return this.cachedPlantsList;
}

public Plant getActPlant() {
	return actPlant;
}

public void setActPlant(Plant actPlant) {
	this.actPlant = actPlant;
}

public List<Plant> getPlants() {
	return plants;
}

public void setPlants(List<Plant> plants) {
	this.plants = plants;
}

public String addPlant () {
	plantService.addPlant(this.actPlant);
	return "new-plant.xhtml";
}

public String updatePlant () {
	plantService.updatePlant(this.actPlant);
	return "new-plant.xhtml";
}

@PostConstruct
public void init() throws Exception{
this.actPlant = new Plant();

}

}
