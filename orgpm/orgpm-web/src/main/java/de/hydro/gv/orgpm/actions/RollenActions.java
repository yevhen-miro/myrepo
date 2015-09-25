package de.hydro.gv.orgpm.actions;

import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import de.hydro.gv.orgpm.auth.RolleEnum;

@RequestScoped
@Dependent
@Named
public class RollenActions {

	public RolleEnum[] getAlle() {
		return RolleEnum.values();
	}

}
