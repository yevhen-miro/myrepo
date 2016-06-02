package de.hydro.gv.mplus.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

import de.hydro.gv.mplus.dao.SystemUserDao;
import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.PayTermId;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.services.BUService;
import de.hydro.gv.mplus.services.CBUService;
import de.hydro.gv.mplus.services.ContractService;
import de.hydro.gv.mplus.services.CustomerService;
import de.hydro.gv.mplus.services.PlantService;
import de.hydro.gv.mplus.services.SystemUserService;
import de.hydro.gv.mplus.services.TimeDateService;

@ViewScoped
@Named
public class LazyContractActions implements Serializable {

	private static final long serialVersionUID = 2279216426451381465L;
	
	private LazyDataModel<Contract> lazyModel;
	private Long id;
	
	@Inject
	private ContractService contractService;
	
	public LazyDataModel<Contract> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Contract> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	private Contract actContract;
	private Customer customer;
	private SystemUser seller;
	private SystemUser approver;
	private String contractStatus;
	private Long id;
	private boolean loaded = false;
	private UploadedFile file;
	private Plant plant;
	private BU bu;
	private CBU cbu;
	private List<CBU> cbuList;
	private List<SystemUser> userList;
	private List<ContractItem> items;
	private List<Plant> plantList;
	private Date contractStart;
	private Date contractEnd;

	
	
	


	public SystemUser getApprover() {
		return approver;
	}

	public void setApprover(SystemUser approver) {
		this.approver = approver;
	}

	public List<Plant> getPlantList() {
		return plantList;
	}

	public void setPlantList(List<Plant> plantList) {
		this.plantList = plantList;
	}

	public List<SystemUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SystemUser> userList) {
		this.userList = userList;
	}

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public List<ContractItem> getItem() {
		return items;
	}

	public void setItem(List<ContractItem> item) {
		this.items = item;
	}

	public List<CBU> getCbuList() {
		return cbuList;
	}

	public void setCbuList(List<CBU> cbuList) {
		this.cbuList = cbuList;
	}

	public BU getBu() throws Exception {
		if (this.actContract.getCbu() != null) {
			return this.bu = buService.getBUbyCBU(this.actContract.getCbu());	
		}
		return bu;
	}

	public void setBu(BU bu) {
		this.bu = bu;
	}

	public CBU getCbu() {
		return cbu;
	}

	public void setCbu(CBU cbu) {
		this.cbu = cbu;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	private String destination = "E:\\Buffer\\";


	
	@Inject
	private CBUService cbuService;
	
	@Inject
	private BUService  buService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private TimeDateService timeDateService;
	
	@Inject
	private SystemUserService userService;
	
	@Inject
	private PlantService plantService;

	private List<Contract> cachedContractList;

	private static final Logger logger = Logger.getLogger(LazyContractActions.class);

	public List<Contract> getAllContracts() throws Exception {

		if (this.cachedContractList == null) {
			this.cachedContractList = this.contractService.getAllContracts();

		}

		 if (this.contractStatus != null) {
			this.cachedContractList = this.contractService
					.getAllContractsByStatus((int) Long.parseLong(this.contractStatus));
		}

		if (this.id != null) {
			this.actContract = this.contractService.getAllContractsById(this.id).get(0);
			this.cachedContractList = this.contractService.getAllContractsById(this.id);
		}

		if (this.customer != null) {
			this.cachedContractList = this.contractService.getAllContractsByCustomer(this.customer);
		}
		
		if (this.plant != null) {
			this.cachedContractList = this.contractService.getAllContractsByPlant(this.plant);
		}
		
		if (this.cbu!= null) {
			this.cachedContractList = this.contractService.getContractsByCBU(this.cbu);
		}
		
		if (this.bu!= null) {
			this.cachedContractList = this.contractService.getContractsByBU(this.bu);
		}
		
		if (this.bu!= null && this.cbu !=null) {
			this.cachedContractList = this.contractService.getContractsByBUAndCBU(this.bu,this.cbu);
		}
		
		logger.infov("There are " + cachedContractList.size() + " elements found");
		return this.cachedContractList;
	}

	public List<ContractItem> getAllContractItems() throws Exception {
		return this.contractService.getAllContractItems();
	}

	public List<ContractItem> getContractItemsByContract() throws Exception {

		return contractService.getContractItemsByContract(this.actContract);
	}

	public void searchContracts() throws Exception {
		//this.cachedContractList = null;
//		 if (this.contractStatus != null) {
//			this.cachedContractList = this.contractService
//					.getAllCContractsByStatus((int) Long.parseLong(this.contractStatus));
//		}
//
//		if (this.id != null) {
//			this.actContract = this.contractService.getAllContractsById(this.id).get(0);
//			this.cachedContractList = this.contractService.getAllContractsById(this.id);
//		}
//
//		if (this.customer != null) {
//			this.cachedContractList = this.contractService.getAllCContractsByCustomer(this.customer);
//		}
		
	}

	public Contract getActContract() {
		return actContract;
	}

	public void setActContract(Contract actContract) {
		this.actContract = actContract;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SystemUser getSeller() {
		return seller;
	}

	public void setSeller(SystemUser seller) {
		this.seller = seller;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void reset() throws Exception {
		this.cachedContractList = null;
		this.id = null;
		this.customer = null;
		this.contractStatus = null;
		this.getAllContracts();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
*/
	@PostConstruct
	public void init() throws Exception{
//	cachedContractList = new ArrayList<Contract> ();
//	cachedContractList = contractService.getAllContracts();
	
	lazyModel = new LazyContractDataModel(contractService.getAllContracts());
	/*

		this.actContract = new Contract();
		this.items = new ArrayList<ContractItem>();
		ContractItem ci = new ContractItem();
		ContractItem ci2 = new ContractItem();
		items.add(ci);
		items.add(ci2);
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String contractId = params.get("contract-id");
		cbuList = cbuService.getAllCBUs();
		userList = userService.findAllUsers();
		plantList = plantService.getAllPlants();


		if (contractId != null && !contractId.equals("") && !this.loaded) {
			try {
				this.actContract = this.contractService.getAllContractsById(new Long(contractId)).get(0);
				this.actContract.setId(new Long(contractId));
				this.loaded = true;
				if (this.contractService.getContractItemsByContract(this.actContract) != null){
					this.items = this.contractService.getContractItemsByContract(this.actContract);
				}
				this.contractStart = timeDateService.findDateIdByTimeDate(this.actContract.getStartdateid());
				this.contractEnd = timeDateService.findDateIdByTimeDate(this.actContract.getEnddateid());
				//this.actContract.setCustomer(new Customer());
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract is not found", null));

			}
		}*/
	}
    
/*
	public void onRowDblClckSelect(SelectEvent event) throws Exception {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String contractId = params.get("contract-id");
		contractId = ((Contract) event.getObject()).getId().toString();
		if (contractId != null && !contractId.equals("") && !this.loaded) {
			try {
				this.actContract = this.contractService.getAllContractsById(((Contract) event.getObject()).getId()).get(0);
				this.actContract.setId(new Long(contractId));
				this.loaded = true;
				String url = "contract-detail.xhtml?contract-id="+contractId;
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract is not found", null));
			}
		}
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// Do what you want with the file
		try {
			copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyFile(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateContract() {
		this.contractService.updateContract(this.actContract);
	}

	public String createContract() {
		System.out.println(actContract.toString());
		//this.actContract.setCreatedby(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		this.actContract.setCbu(this.cbu);
		this.actContract.setCustomer(this.customer);
		List<Plant> plantList = new ArrayList();
		plantList.add(this.plant);
		this.actContract.setPlants(plantList);
		this.actContract.setSeller(this.seller);
		this.actContract.setAgentid(0);
		this.actContract.setAlreadyhedged(false);
		this.actContract.setStartdateid(this.timeDateService.findDateIdByDate(contractStart));
		this.actContract.setEnddateid(this.timeDateService.findDateIdByDate(contractEnd));
	
		
		this.contractService.createContract(actContract);
		return "contracts.xhtml";
	}
	*/
	/*
	public String createNewContract() throws Exception {
		this.actContract.setCreatedby(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		//this.actContract.setCreatedby("21E46F33-6AED-4B40-BC52-0C2B11135079");
		//this.actContract.setSeller("21E46F33-6AED-4B40-BC52-0C2B11135079");
		//this.actContract.setId(99999L);
		this.actContract.setCustomer(customerService.findCustomerById(23L));
		this.actContract.setCbu(this.cbu);
		
		List<Plant> plantList = new ArrayList();
		plantList.add(this.plant);
		this.actContract.setPlants(plantList);
		//this.actContract.setSeller(this.seller);
		this.actContract.setAgentid(0);
		this.actContract.setAlreadyhedged(false);
		this.actContract.setStartdateid(this.timeDateService.findDateIdByDate(contractStart));
		this.actContract.setEnddateid(this.timeDateService.findDateIdByDate(contractEnd));
		this.actContract.setSeller(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		
		//List<ContractItem> itemList = new ArrayList();
		//Contract con = contractService.getContractsByCBU(this.actContract.getCbu()).get(0);
		//itemList = contractService.getContractItemsByContract(con);
		//this.actContract.setItems(itemList);


		this.actContract.setAssumedlme(new BigDecimal(0));
		
		this.actContract.setAssumedlmetype("");
		this.actContract.setAssumedpremium (new BigDecimal(0));
		this.actContract.setAssumedpremiumtype("");
		this.actContract.setAssumedmetalfreight (new BigDecimal(0));
		this.actContract.setAssumedusdrate (new BigDecimal(0));
		this.actContract.setVolumetolerance(0);
		this.actContract.setIncotermid(contractService.getIncotermsById("CFR"));
		PayTermId pid = new PayTermId();
		pid.setCtxId(1);
		pid.setCtxCode("A120");
		this.actContract.setPaymentterm (contractService.getPayTermById(pid));
		this.actContract.setPaymenttermdays(0);
		this.actContract.setMaxconsignmentstock(0);
		this.actContract.setMaxconsignmentdays(0);
		this.actContract.setConsignmentInTransit (0);
		this.actContract.setMaxcalloffvolume (0);
		this.actContract.setComments ("");
		this.actContract.setCommission (new BigDecimal(0));
		this.actContract.setAgentname ("");
		this.actContract.setOtherdiscounts (new BigDecimal(0));
		this.actContract.setCashdiscount (new BigDecimal(0));
		this.actContract.setBonus (new BigDecimal(0));
		this.actContract.setMaxmetalhedgevolume(0);
		this.actContract.setFormulatype("");
		this.actContract.setMetalhedgingid (0L);
		this.actContract.setFixedmetalfreight("");
		this.actContract.setFixedmetalpremium("");
		this.actContract.setConversioncurrencyid (contractService.getCurrencyById("EUR"));
		this.actContract.setMetalcurrencyid (contractService.getCurrencyById("EUR"));
		this.actContract.setStandardcontract (0);
		this.actContract.setLegalwho("");
		this.actContract.setLegalwhen(new Date());
		this.actContract.setLegalcomment ("");
		this.actContract.setExternalcreditlimit (new BigDecimal(0));
		this.actContract.setInternalCreditLimit (new BigDecimal(0));
		this.actContract.setRatingatradiusid (0L);
		this.actContract.setRatingmoodys("");
		this.actContract.setForeigncurrencyconversionvolume(new BigDecimal(0));
		this.actContract.setAlreadyhedged(true);
		this.actContract.setAssumedcurrencyrate(new BigDecimal(0));
		this.actContract.setCreated(new Date());
		this.actContract.setUpdated(new Date());
		this.actContract.setCreatedbyname("");
		this.actContract.setUpdatedbyname ("");
		this.actContract.setBonusdescription ("");
		this.actContract.setTollingid (0);
		this.actContract.setScraptypeid(0L);
		this.actContract.setAgentid (0);
//		this.actContract.setPaytermctxid (0L);
	//	this.actContract.setPaytermcode ("");
		
		this.contractService.createContract(actContract);
		return "contracts.xhtml";
	}

	public String addNewContract() {
		return "new-contract.xhtml";
	}
	
	public void onCustomerChanged() throws Exception {	
		if( this.customer != null ) {
			this.cachedContractList = contractService.getAllContractsByCustomer(customer);
			
		} else {
			this.cachedContractList = null;
		}
	}
	
	public void onCustomerForDetailsChanged() throws CloneNotSupportedException {
		if (this.customer != null) {
			Customer selectedCustomer = (Customer) this.customer.clone();
			this.actContract = new Contract();
			this.actContract.setCustomer(selectedCustomer);
		} else this.setCustomer(null);
	}
	
	public void onCustomerForContractChanged() throws CloneNotSupportedException {
		if (this.customer != null) {
			Customer selectedCustomer = (Customer) this.customer.clone();
			this.actContract = new Contract();
			this.actContract.setCustomer(selectedCustomer);
		} else this.setCustomer(null);
	}
	
	public void onBUChange() throws Exception {
		
		if( this.bu != null ) {
			this.cbuList = this.cbuService.findCbuByBU(bu);
			
		} else {
			this.cbuList = this.cbuService.getAllCBUs();
		}

	}
	
	public List<CBU> getAllCBU() throws Exception {

		if( this.cbuList == null ) {
			this.cbuList = this.cbuService.getAllCBUs();

		}
		return this.cbuList;
	}

	public List<CBU> getCBUByBU(BU pBU) throws Exception {
		cbuList = cbuService.findCbuByBU(pBU);
		return cbuList;
	}
*/
	@SuppressWarnings("unchecked")
	public void searchContracts() throws Exception {
		//LazyContractDataModel lm = new LazyContractDataModel(contractService.findContractById(this.id));
		//this.lazyModel = lm;
		//System.out.println(contractService.findContractById(this.id).size());
	}
	
}
