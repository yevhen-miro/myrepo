package de.hydro.gv.orgpm.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named( "menu" )
public class MenuSevice implements Serializable {

	private static final long serialVersionUID = 1485116609307271083L;

	public List<de.hydro.gv.orgpm.auth.Menu> getAlleWurzeln() {
		de.hydro.gv.orgpm.dao.MenuDao mrp = new de.hydro.gv.orgpm.dao.MenuDao();
		return mrp.findAllRoot();
	}

}
