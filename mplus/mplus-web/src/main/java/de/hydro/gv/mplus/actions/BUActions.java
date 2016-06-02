package de.hydro.gv.mplus.actions;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.services.BUService;


@ViewScoped
@Named
public class BUActions implements Serializable {

private static final long serialVersionUID = 2279216426451381465L;

private BU actBU;

private List<BU> BUs;
	

@Inject
private BUService buService;

private List<BU> cachedBUList;

public List<BU> getAllBUs() throws Exception {

	if( this.cachedBUList == null ) {
		this.cachedBUList = this.buService.getAllBUs();

	}
	return this.cachedBUList;
}

public BU getActBU() {
	return actBU;
}

public void setActBU(BU actBU) {
	this.actBU = actBU;
}

public List<BU> getBUs() {
	return BUs;
}

public void setBUs(List<BU> cBUs) {
	BUs = cBUs;
}




}
