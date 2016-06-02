package de.hydro.gv.mplus.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.services.PlantService;
import de.hydro.gv.mplus.services.SystemUserService;

@ViewScoped
@Named
public class SystemUserActions implements Serializable {

private static final long serialVersionUID = 2279216426451381465L;

private SystemUser actUser;

private String selectedUser; // +getter +setter
private List<SelectItem> userList; // +getter (no setter necessary)



public String getSelectedUser() {
	return selectedUser;
}

public void setSelectedUser(String selectedUser) {
	this.selectedUser = selectedUser;
}

public List<SelectItem> getUserList() {
	return userList;
}

public void setUserList(List<SelectItem> userList) {
	this.userList = userList;
}


@Inject
private SystemUserService userService;

public SystemUser getActUser() {
	return actUser;
}

public void setActUser(SystemUser actUser) {
	this.actUser = actUser;
}

public String addUser () {
	userService.addUser(this.actUser);
	return "new-user.xhtml";
}

public String updateUser () {
	userService.updateUser(this.actUser);
	return "new-user.xhtml";
}

public List<SystemUser> findAllUsers () {
	return userService.findAllUsers();
}

public List<SelectItem> findAllUserNames () {
	List<SelectItem> items = new ArrayList<SelectItem>();
	for(String value : userService.findAllUserNames()){
	    items.add(new SelectItem(value));
	}
	return items;
}


@PostConstruct
public void init() throws Exception{
this.actUser= new SystemUser();
userList = findAllUserNames();

}

}
