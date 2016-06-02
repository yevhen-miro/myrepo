package de.hydro.gv.mplus.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.hydro.gv.mplus.dao.ContractDao;
import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.ClauseType;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractActivity;
import de.hydro.gv.mplus.data.ContractApprover;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Currency;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Incoterms;
import de.hydro.gv.mplus.data.PayTerm;
import de.hydro.gv.mplus.data.PayTermId;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;

@Stateless
public class ContractService {

	@Inject
	private ContractDao contractDao;

	public List<Contract> getAllContracts() throws Exception {
		return this.contractDao.readAllContracts();
	}

	public void createContract(Contract pContract) {
		this.contractDao.createContract(pContract);
	}

	public void updateContract(Contract pContract) {
		this.contractDao.updateContract(pContract);
	}

	public List<ContractItem> getAllContractItems() throws Exception {
		return this.contractDao.readAllContractItems();
	}

	public Contract findContractById(Long pId) throws Exception {
		return this.contractDao.readAllContractsById(pId);
	}

	public List<Contract> getAllContractsByCustomer(Customer pCustomer) throws Exception {
		return this.contractDao.readAllContractsByCustomer(pCustomer);
	}

	public List<Contract> getAllContractsByPlant(Plant pPlant) throws Exception {
		return this.contractDao.readAllContractsByPlant(pPlant);
	}

	public List<Contract> getAllContractsBySeller(SystemUser pSeller) throws Exception {
		return this.contractDao.findAllContractsBySeller(pSeller);
	}

	public List<Contract> getAllContractsByStatus(Integer pStatus) throws Exception {
		return this.contractDao.readAllContractsByStatus(pStatus);
	}

	public List<ContractItem> getContractItemsByContract(Contract pContract) throws Exception {
		return this.contractDao.readContractItemsByContract(pContract);
	}

	public List<Contract> getContractsByCBU(CBU pCBU) throws Exception {
		return this.contractDao.findContractsByCBU(pCBU);
	}

	public List<Contract> getContractsByBU(BU pBU) throws Exception {
		return this.contractDao.findContractsByBU(pBU);
	}

	public List<Contract> getContractsByBUAndCBU(BU pBU, CBU pCBU) throws Exception {
		return this.contractDao.findContractsByBUAndCBU(pBU, pCBU);
	}

	public Incoterms getIncotermsById(String pId) {
		return this.contractDao.findIncotermById(pId);
	}

	public Currency getCurrencyById(String pId) {
		return this.contractDao.findCurrencyById(pId);
	}

	public PayTerm getPayTermById(PayTermId pId) {
		return this.contractDao.findPayTermById(pId);
	}

	public List<ClauseType> getAllClauses() {
		return this.contractDao.findAllClauses();
	}

	public List<ContractApprover> findContractApproversByContract(Contract pContract) {
		return this.contractDao.findContractApproversByContract(pContract);
	}

	public List<ContractActivity> findContractActivitiesByContract(Contract pContract) {
		return this.contractDao.findContractActivitiesByContract(pContract);
	}

	public List<ContractActivity> findAllContractActivities() {
		return this.contractDao.findAllContractActivities();
	}

}
