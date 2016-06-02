package de.hydro.gv.mplus.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import de.hydro.gv.mplus.data.BU;
import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.ClauseType;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractActivity;
import de.hydro.gv.mplus.data.ContractApprover;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.CustomerParent;
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

@RequestScoped
@ManagedBean
public class ContractDetailActions implements Serializable {

	private static final long serialVersionUID = 2279216426451381465L;
	private static final String destination = "E:\\Buffer\\";
	private static final Logger logger = Logger.getLogger(ContractDetailActions.class);

	/* Services CDI Injections */
	@Inject
	private ContractService contractService;

	@Inject
	private CBUService cbuService;

	@Inject
	private BUService buService;

	@Inject
	private CustomerService customerService;

	@Inject
	private TimeDateService timeDateService;

	@Inject
	private SystemUserService userService;

	@Inject
	private PlantService plantService;

	/* Form elements */
	private Long id;
	private Contract actContract;
	private SystemUser seller;
	private SystemUser approver;
	private String contractStatus;
	private Plant plant;
	private BU bu;
	private CBU cbu;
	private Customer customer;
	private String customerName;
	private CustomerParent customerParent;
	private boolean loaded = false;
	private Date contractStart;
	private Date contractEnd;
	private UploadedFile file;
	private Integer monthsdiff;

	private List<Customer> customerList;
	private List<CustomerParent> customerParents;
	private List<ClauseType> clauses;
	private List<ClauseType> selectedClauses;
	private List<ContractApprover> approvers;
	private List<ContractActivity> activities;
	private List<CBU> cbuList;
	private List<SystemUser> userList;
	private List<ContractItem> items;
	private List<Plant> plantList;
	private List<Contract> cachedContractList;

	/* Getters, Setters */
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<CustomerParent> getCustomerParents() {
		return customerParents;
	}

	public void setCustomerParents(List<CustomerParent> customerParents) {
		this.customerParents = customerParents;
	}

	public CustomerParent getCustomerParent() {
		return customerParent;
	}

	public void setCustomerParent(CustomerParent customerParent) {
		this.customerParent = customerParent;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
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

	public List<CBU> getAllCBU() throws Exception {

		if (this.cbuList == null) {
			this.cbuList = this.cbuService.getAllCBUs();

		}
		return this.cbuList;
	}

	public Integer getMonthsdiff() {
		return monthsdiff;
	}

	public List<CBU> getCBUByBU(BU pBU) throws Exception {
		cbuList = cbuService.findCbuByBU(pBU);
		return cbuList;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<ClauseType> getClauses() {
		return clauses;
	}

	public void setClauses(List<ClauseType> clauses) {
		this.clauses = clauses;
	}

	public List<ClauseType> getSelectedClauses() {
		return selectedClauses;
	}

	public void setSelectedClauses(List<ClauseType> selectedClauses) {
		this.selectedClauses = selectedClauses;
	}

	public List<Contract> getAllContracts() throws Exception {

		if (this.cachedContractList == null) {
			this.cachedContractList = this.contractService.getAllContracts();
		}
		// logger.infov("There are " + cachedContractList.size() + " elements
		// found");
		return this.cachedContractList;
	}

	public List<ContractItem> getAllContractItems() throws Exception {
		return this.contractService.getAllContractItems();
	}

	public List<ContractItem> getContractItemsByContract() throws Exception {

		return contractService.getContractItemsByContract(this.actContract);
	}

	public List<ContractApprover> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<ContractApprover> approvers) {
		this.approvers = approvers;
	}

	public List<ContractActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<ContractActivity> activities) {
		this.activities = activities;
	}

	/* View initialization */
	@PostConstruct
	public void init() throws Exception {

		long startTime = System.nanoTime();

		this.actContract = new Contract();
		/*
		 * this.items = new ArrayList<ContractItem>(); ContractItem ci = new
		 * ContractItem(); ContractItem ci2 = new ContractItem(); items.add(ci);
		 * items.add(ci2);
		 */

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String contractId = params.get("contract-id");

		// Lists initialization
		cachedContractList = contractService.getAllContracts();
		cbuList = cbuService.getAllCBUs();
		userList = userService.findAllUsers();
		plantList = plantService.getAllPlants();
		customerParents = customerService.getAllCustomerParents();
		clauses = contractService.getAllClauses();

		long endTime = System.nanoTime();

		long duration = (endTime - startTime); // divide by 1000000 to get
												// milliseconds.
		System.out.println("Bean loaded in " + duration / 1000000000 + " sec");

		if (contractId != null && !contractId.equals("") && !this.loaded) {
			try {
				Long startInfoTime = System.nanoTime();
				this.actContract = this.contractService.findContractById(new Long(contractId));
				this.actContract.setId(new Long(contractId));
				this.loaded = true;

				this.approvers = this.contractService.findContractApproversByContract(this.actContract);
				this.activities = this.contractService.findContractActivitiesByContract(this.actContract);

				this.contractStart = timeDateService.findDateIdByTimeDate(this.actContract.getStartdateid());
				this.contractEnd = timeDateService.findDateIdByTimeDate(this.actContract.getEnddateid());

				if (this.contractService.getContractItemsByContract(this.actContract) != null) {
					this.items = this.contractService.getContractItemsByContract(this.actContract);
				}
				if (items.size() == 0) {
					System.out.println("No Contract Items found for contract " + this.actContract.getId());
				}

				Long endInfoTime = System.nanoTime();

				Long durationInfo = (endInfoTime - startInfoTime); // divide by
																	// 1000000
																	// to get
				// milliseconds.
				System.out.println("Contract info loaded in " + durationInfo / 1000000000 + " sec");

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract is not found", null));

			}

		}
	}

	/* Action Methods */
	@SuppressWarnings("unchecked")
	public void searchContracts() throws Exception {

		if (this.cachedContractList == null) {
			this.cachedContractList = this.contractService.getAllContracts();
		}

		if (this.contractStatus != null) {
			if (this.seller != null) {// bug !? 22.04
				seller = null;
			}
			this.cachedContractList = this.contractService
					.getAllContractsByStatus((int) Long.parseLong(this.contractStatus));
		}

		if (this.id != null) {
			this.actContract = this.contractService.findContractById(this.id);
			List<Contract> cl = new ArrayList<Contract>();
			cl.add(this.contractService.findContractById(this.id));
			this.cachedContractList.clear();
			this.cachedContractList = cl;
		}

		if (this.customer != null) {
			this.cachedContractList = this.contractService.getAllContractsByCustomer(this.customer);
		}

		if (this.plant != null) {
			this.cachedContractList = this.contractService.getAllContractsByPlant(this.plant);
		}

		if (this.cbu != null) {
			this.cachedContractList = this.contractService.getContractsByCBU(this.cbu);
		}

		if (this.bu != null) {
			this.cachedContractList = this.contractService.getContractsByBU(this.bu);
		}

		if (this.seller != null) {
			this.cachedContractList = this.contractService.getAllContractsBySeller(this.seller);
		}

		if (this.bu != null && this.cbu != null) {
			this.cachedContractList = this.contractService.getContractsByBUAndCBU(this.bu, this.cbu);
		}

	}

	public void resetForm() throws Exception {
		this.cachedContractList = this.contractService.getAllContracts();
		this.id = null;
		this.customer = null;
		this.contractStatus = null;
		this.cbu = null;
		this.bu = null;
		this.plant = null;
		this.seller = null;
		this.customerList = null;

	}

	public void reset() throws Exception {
		resetForm();
	}

	public void upload() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onRowDblClckSelect(SelectEvent event) throws Exception {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String contractId = params.get("contract-id");
		contractId = ((Contract) event.getObject()).getId().toString();
		if (contractId != null && !contractId.equals("") && !this.loaded) {
			try {
				// this.actContract =
				// this.contractService.findContractById(((Contract)
				// event.getObject()).getId());
				// this.actContract.setId(new Long(contractId));
				// this.loaded = true;
				String url = "contractdetail.xhtml?contract-id=" + contractId;
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
				if (this.actContract.getStartdateid().getDateFull() != null || this.actContract.getStartdateid().getDateFull() != null) {

					Calendar startCalendar = new GregorianCalendar();
					startCalendar.setTime(this.contractStart);
					Calendar endCalendar = new GregorianCalendar();
					endCalendar.setTime(contractEnd);

					this.monthsdiff = (endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)) * 12
							+ endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
				}
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

	public String updateContract() {
		this.actContract.setCreatedby(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		this.actContract.setCustomer(this.customer);
		this.actContract.setCbu(this.cbu);
		this.contractService.updateContract(this.actContract);

		return "contracts.xhtml";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createNewContract() throws Exception {
		this.actContract.setCreatedby(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		this.actContract.setCustomer(this.customer);
		this.actContract.setCbu(this.cbu);
		// List<Plant> plantList = new ArrayList();
		// plantList.add(this.plant);
		// this.actContract.setPlants(plantList);
		this.actContract.setPlant(this.plant);
		this.actContract.setAgentid(0);
		this.actContract.setAlreadyhedged(false);
		this.actContract.setStartdateid(this.timeDateService.findDateIdByDate(contractStart));
		this.actContract.setEnddateid(this.timeDateService.findDateIdByDate(contractEnd));
		this.actContract.setSeller(userService.findUserById("21E46F33-6AED-4B40-BC52-0C2B11135079"));
		this.actContract.setAssumedlme(new BigDecimal(0));
		this.actContract.setAssumedlmetype("");
		this.actContract.setAssumedpremium(new BigDecimal(0));
		this.actContract.setAssumedpremiumtype("");
		this.actContract.setAssumedmetalfreight(new BigDecimal(0));
		this.actContract.setAssumedusdrate(new BigDecimal(0));
		this.actContract.setVolumetolerance(0);
		this.actContract.setIncotermid(contractService.getIncotermsById("CFR"));
		PayTermId pid = new PayTermId();
		pid.setCtxId(1);
		pid.setCtxCode("A120");
		this.actContract.setPaymentterm(contractService.getPayTermById(pid));
		this.actContract.setPaymenttermdays(0);
		this.actContract.setMaxconsignmentstock(0);
		this.actContract.setMaxconsignmentdays(0);
		this.actContract.setConsignmentInTransit(0);
		this.actContract.setMaxcalloffvolume(0);
		this.actContract.setComments("");
		this.actContract.setCommission(new BigDecimal(0));
		this.actContract.setAgentname("");
		this.actContract.setOtherdiscounts(new BigDecimal(0));
		this.actContract.setCashdiscount(new BigDecimal(0));
		this.actContract.setBonus(new BigDecimal(0));
		this.actContract.setMaxmetalhedgevolume(0);
		this.actContract.setFormulatype("");
		this.actContract.setMetalhedgingid(0L);
		this.actContract.setFixedmetalfreight("");
		this.actContract.setFixedmetalpremium("");
		this.actContract.setConversioncurrencyid(contractService.getCurrencyById("EUR"));
		this.actContract.setMetalcurrencyid(contractService.getCurrencyById("EUR"));
		this.actContract.setStandardcontract(0);
		this.actContract.setLegalwho("");
		this.actContract.setLegalwhen(new Date());
		this.actContract.setLegalcomment("");
		this.actContract.setExternalcreditlimit(new BigDecimal(0));
		this.actContract.setInternalCreditLimit(new BigDecimal(0));
		this.actContract.setRatingatradiusid(0L);
		this.actContract.setRatingmoodys("");
		this.actContract.setForeigncurrencyconversionvolume(new BigDecimal(0));
		this.actContract.setAlreadyhedged(true);
		this.actContract.setAssumedcurrencyrate(new BigDecimal(0));
		this.actContract.setCreated(new Date());
		this.actContract.setUpdated(new Date());
		this.actContract.setCreatedbyname("");
		this.actContract.setUpdatedbyname("");
		this.actContract.setBonusdescription("");
		this.actContract.setTollingid(0);
		this.actContract.setScraptypeid(0L);
		this.actContract.setAgentid(0);

		this.contractService.createContract(actContract);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "A new contract was created", null));
		return "contracts.xhtml";
	}

	public String addNewContract() {
		return "new-contract.xhtml";
	}

	public void onCustomerChanged() throws Exception {
		if (this.customer != null) {
			this.cachedContractList = contractService.getAllContractsByCustomer(customer);

		} else {
			this.cachedContractList = null;
		}
	}

	public void onCustomerChangedListener() throws Exception {
		System.out.println(this.customer);
	}

	public void onCustomerForDetailsChanged() throws CloneNotSupportedException {
		if (this.customer != null) {
			Customer selectedCustomer = (Customer) this.customer.clone();
			this.actContract = new Contract();
			this.actContract.setCustomer(selectedCustomer);
		} else
			this.setCustomer(null);
	}

	public void onCustomerForContractChanged() throws CloneNotSupportedException {
		if (this.customer != null) {
			Customer selectedCustomer = (Customer) this.customer.clone();
			this.actContract = new Contract();
			this.actContract.setCustomer(selectedCustomer);
		} else
			this.setCustomer(null);
	}

	public void onBUChange() throws Exception {

		if (this.bu != null) {
			this.cbuList = this.cbuService.findCbuByBU(bu);

		} else {
			this.cbuList = this.cbuService.getAllCBUs();
		}

	}

	public void onCBUChange() throws Exception {

		if (this.cbu != null) {
			this.bu = this.buService.getBUbyCBU(this.cbu);

		} else {
			this.bu = this.buService.getAllBUs().get(0);
		}

	}

	public void onParentChange() throws Exception {

		if (this.customerParent.getId() != null) {
			this.customerList = customerService.getAllCustomersByParent(this.customerParent);

		}

	}

	public List<Customer> findCustomersByName() throws Exception {
		String text = this.customerName == null ? "" : this.customerName;
		customerList = customerService.findCustomersByName(text.toUpperCase());
		return customerList;
	}

	public void resetCustomerDialog() {
		this.customer = null;
		this.customerList = null;
		this.customerParent = null;
		this.customerName = null;
	}

	public List<Customer> findCustomersByCustomerName() throws Exception {
		return this.customerService.findCustomersByName(this.customerName);
	}

}
