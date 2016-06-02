package de.hydro.gv.mplus.actions;

import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.services.CBUService;

@ViewScoped
@Named
public class CBUActions implements Serializable {

private static final long serialVersionUID = 2279216426451381465L;

private CBU actCBU;

private BU actBU;

private List<CBU> CBUs;
	

@Inject
private CBUService cbuService;

private List<CBU> cachedCBUList;

public List<CBU> getAllCBU() throws Exception {

	if( this.cachedCBUList == null ) {
		this.cachedCBUList = this.cbuService.getAllCBUs();

	}
	return this.cachedCBUList;
}

public List<CBU> getCBUByBU(BU pBU) throws Exception {
	cachedCBUList = cbuService.findCbuByBU(pBU);
	return cachedCBUList;
}

public CBU getActCBU() {
	return actCBU;
}

public void setActCBU(CBU actCBU) {
	this.actCBU = actCBU;
}

public List<CBU> getCBUs() {
	return CBUs;
}

public void setCBUs(List<CBU> cBUs) {
	CBUs = cBUs;
}


public BU getActBU() {
	return actBU;
}

public void setActBU(BU actBU) {
	this.actBU = actBU;
}

public void onBUChange() throws Exception {
	
	if( this.actBU != null ) {
		this.cachedCBUList = this.cbuService.findCbuByBU(actBU);
		this.actBU = null;
		
	} else {
		this.cachedCBUList = this.cbuService.findCbuByBU(actBU);
	}

}

}
