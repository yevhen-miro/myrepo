package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Joiner;

@Entity
@Table(name = "dbo.CONTRACTS")
@NamedQueries({ @NamedQuery(name = "contracts.find.all", query = "SELECT c FROM Contract AS c ORDER BY c.id desc"),
		@NamedQuery(name = "contracts.find.by.id", query = "SELECT c FROM Contract AS c WHERE c.id = :id ORDER BY c.id desc"),
		@NamedQuery(name = "contracts.find.by.customer", query = "SELECT c FROM Contract AS c WHERE c.customer = :customer ORDER BY c.id desc"),
		@NamedQuery(name = "contracts.find.by.seller", query = "SELECT c FROM Contract AS c WHERE c.seller = :seller ORDER BY c.id desc"),
		@NamedQuery(name = "contracts.find.by.plant", query = "SELECT c FROM Contract AS c WHERE :plant MEMBER OF c.plants"),
		@NamedQuery(name = "contracts.find.by.cbu", query = "SELECT c FROM Contract AS c WHERE c.cbu = :cbu"),
		@NamedQuery(name = "contracts.find.by.bu", query = "SELECT c FROM Contract AS c WHERE c.cbu.bu = :bu"),
		@NamedQuery(name = "contracts.find.by.bu.and.cbu", query = "SELECT c FROM Contract AS c WHERE c.cbu.bu = :bu AND c.cbu = :cbu"),
		// @NamedQuery( name = "contracts.find.by.plant", query = "SELECT cp
		// FROM ContractPlant AS cp WHERE cp.id.plantId = :plant" ),
		@NamedQuery(name = "contracts.find.by.status", query = "SELECT c FROM Contract AS c WHERE c.statusId = :status ORDER BY c.id desc") })
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contract implements Serializable {

	private static final long serialVersionUID = -4094666019280003723L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Column(name = "StatusId")
	private int statusId;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "CustomerId", foreignKey = @ForeignKey(name = "FK_CONTRACT_CUSTOMER") )
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Customer customer;

	@OneToMany
	@JoinTable(name = "dbo.ContractPlants", joinColumns = @JoinColumn(name = "ContractId", referencedColumnName = "Id") , inverseJoinColumns = @JoinColumn(name = "PlantId", referencedColumnName = "Plant_Id") )
	private List<Plant> plants;
/*
	@OneToMany
	@JoinTable(name = "dbo.ContractItems", joinColumns = @JoinColumn(name = "ContractId", referencedColumnName = "id") )
	private List<ContractItem> items;

	@OneToMany
	@NotNull
	@JoinTable(name = "dbo.ContractPlants", joinColumns = @JoinColumn(name = "ContractId", referencedColumnName = "Id") , inverseJoinColumns = @JoinColumn(name = "PlantId", referencedColumnName = "Plant_Id") )
	private List<Plant> plants;
*/
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "PlantId", foreignKey = @ForeignKey(name = "FK_CONTRACT_PLANT") )
	private Plant plant;
	
	@OneToMany(mappedBy = "user")
//	@NotNull
	private List<ContractApprover> approvers;
	
	@OneToMany
//	@NotNull
	@JoinTable(name = "dbo.ContractActivities", joinColumns = @JoinColumn(name = "ContractId", referencedColumnName = "Id"))
	private List<ContractActivity> activities;
	
	@OneToMany
//	@NotNull
	@JoinTable(name = "dbo.ContractClauses", joinColumns = @JoinColumn(name = "ContractId", referencedColumnName = "Id") , inverseJoinColumns = @JoinColumn(name = "ClauseTypeId", referencedColumnName = "Id") )
	private List<ClauseType> clauses;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SellerId", foreignKey = @ForeignKey(name = "FK_CONTRACT_SELLER") )
	private SystemUser seller;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "StartDateId", foreignKey = @ForeignKey(name = "FK_CONTRACT_STARTDATE") )
	private TimeDate startdateid;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "EndDateId", foreignKey = @ForeignKey(name = "FK_CONTRACT_ENDDATE") )
	private TimeDate enddateid;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "CBUId", foreignKey = @ForeignKey(name = "FK_CONTRACT_CBU") )
	private CBU cbu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CreatedBy", foreignKey = @ForeignKey(name = "FK_CONTRACT_SELLER") )
	private SystemUser createdby;
/*	
	@Column(name = "CreatedBy", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdby;
*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UpdatedBy", foreignKey = @ForeignKey(name = "FK_CONTRACT_SELLER") )
	private SystemUser updatedby;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IncotermId", foreignKey = @ForeignKey(name = "FK_CONTRACT_INCOTERM") )
	private Incoterms incotermid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ConversionCurrencyId", foreignKey = @ForeignKey(name = "FK_CONTRACT_CONVERSION_CURRENCY") )
	private Currency conversioncurrencyid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MetalCurrencyId", foreignKey = @ForeignKey(name = "FK_CONTRACT_METALL_CURRENCY") )
	private Currency metalcurrencyid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "paytermctxid", referencedColumnName = "Ctx_Id",  foreignKey = @ForeignKey(name = "FK_CONTRACT_PAYMENT_TERM_CTX")),
		@JoinColumn(name = "paytermcode", referencedColumnName = "Ctx_Code",  foreignKey = @ForeignKey(name = "FK_CONTRACT_PAYMENT_TERM_CODE"))})
	private PayTerm paymentterm;

	@Column(name = "AssumedLME")
	private BigDecimal assumedlme;

	@Column(name = "AssumedLMEType")
	private String assumedlmetype;

	@Column(name = "AssumedPremium")
	private BigDecimal assumedpremium;

	@Column(name = "AssumedPremiumType")
	private String assumedpremiumtype;

	@Column(name = "AssumedMetalFreight")
	private BigDecimal assumedmetalfreight;

	@Column(name = "AssumedUSDRate")
	private BigDecimal assumedusdrate;

	@Column(name = "VolumeTolerance")
	private Integer volumetolerance;

	@Column(name = "PaymentTermDays")
	private Integer paymenttermdays;

	@Column(name = "MaxConsignmentStock")
	private Integer maxconsignmentstock;

	@Column(name = "MaxConsignmentDays")
	private Integer maxconsignmentdays;

	@Column(name = "ConsignmentInTransit")
	private Integer consignmentInTransit;

	@Column(name = "MaxCalloffVolume")
	private Integer maxcalloffvolume;

	@Column(name = "Comments")
	private String comments;

	@Column(name = "Commission")
	private BigDecimal commission;

	@Column(name = "AgentName")
	private String agentname;

	@Column(name = "OtherDiscounts")
	private BigDecimal otherdiscounts;

	@Column(name = "CashDiscount")
	private BigDecimal cashdiscount;

	@Column(name = "Bonus")
	private BigDecimal bonus;

	@Column(name = "MaxMetalHedgeVolume")
	private Integer maxmetalhedgevolume;

	@Column(name = "FormulaType")
	private String formulatype;

	@Column(name = "MetalHedgingId")
	private Long metalhedgingid;

	@Column(name = "FixedMetalFreight")
	private String fixedmetalfreight;

	@Column(name = "FixedMetalPremium")
	private String fixedmetalpremium;

	// @Column( name = "ExpectedEBIT" )
	// private BigDecimal expectedebit;

	// @Column( name = "GrossCostChange" )
	// private BigDecimal grosscostchange;

	@Column(name = "StandardContract")
	private Integer standardcontract;

	@Column(name = "LegalWho")
	private String legalwho;

	@Temporal(TemporalType.DATE)
	@Column(name = "LegalWhen")
	private Date legalwhen;

	@Column(name = "LegalComment")
	private String legalcomment;

	@Column(name = "ExternalCreditLimit")
	private BigDecimal externalcreditlimit;

	@Column(name = "InternalCreditLimit")
	private BigDecimal internalCreditLimit;

	@Column(name = "RatingAtradiusId")
	private Long ratingatradiusid;

	@Column(name = "RatingMoodys")
	private String ratingmoodys;

	@Column(name = "ForeignCurrencyConversionVolume")
	private BigDecimal foreigncurrencyconversionvolume;

	@Column(name = "AlreadyHedged")
	private Boolean alreadyhedged;

	@Column(name = "AssumedCurrencyRate")
	private BigDecimal assumedcurrencyrate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Updated")
	private Date updated;

	@Column(name = "CreatedByName", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdbyname;

	@Column(name = "UpdatedByName", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String updatedbyname;

	@Column(name = "BonusDescription")
	private String bonusdescription;

	@Column(name = "TollingId")
	private Integer tollingid;

	@Column(name = "ScrapTypeId")
	private Long scraptypeid;

	@Column(name = "AgentId")
	private Integer agentid;
/*
	@Column(name = "PayTermCtxId")
	private Long paytermctxid;

	@Column(name = "PayTermCode")
	private String paytermcode;
*/
	
	
	public Long getId() {
		return id;
	}

/*

	public List<Plant> getPlant() {
		return plants;
	}

	public void setPlant(List<Plant> plants) {
		this.plants = plants;
	}

*/

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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

	public TimeDate getStartdateid() {
		return startdateid;
	}

	public void setStartdateid(TimeDate startdateid) {
		this.startdateid = startdateid;
	}

	public TimeDate getEnddateid() {
		return enddateid;
	}

	public void setEnddateid(TimeDate enddateid) {
		this.enddateid = enddateid;
	}

	public CBU getCbu() {
		return cbu;
	}

	public void setCbu(CBU cbu) {
		this.cbu = cbu;
	}

	public BigDecimal getAssumedlme() {
		return assumedlme;
	}

	public void setAssumedlme(BigDecimal assumedlme) {
		this.assumedlme = assumedlme;
	}

	public String getAssumedlmetype() {
		return assumedlmetype;
	}

	public void setAssumedlmetype(String assumedlmetype) {
		this.assumedlmetype = assumedlmetype;
	}

	public BigDecimal getAssumedpremium() {
		return assumedpremium;
	}

	public void setAssumedpremium(BigDecimal assumedpremium) {
		this.assumedpremium = assumedpremium;
	}

	public String getAssumedpremiumtype() {
		return assumedpremiumtype;
	}

	public void setAssumedpremiumtype(String assumedpremiumtype) {
		this.assumedpremiumtype = assumedpremiumtype;
	}

	public BigDecimal getAssumedmetalfreight() {
		return assumedmetalfreight;
	}

	public void setAssumedmetalfreight(BigDecimal assumedmetalfreight) {
		this.assumedmetalfreight = assumedmetalfreight;
	}

	public BigDecimal getAssumedusdrate() {
		return assumedusdrate;
	}

	public void setAssumedusdrate(BigDecimal assumedusdrate) {
		this.assumedusdrate = assumedusdrate;
	}

	public Integer getVolumetolerance() {
		return volumetolerance;
	}

	public void setVolumetolerance(Integer volumetolerance) {
		this.volumetolerance = volumetolerance;
	}

	public Incoterms getIncotermid() {
		return incotermid;
	}

	public void setIncotermid(Incoterms incotermid) {
		this.incotermid = incotermid;
	}

	public PayTerm getPaymentterm() {
		return paymentterm;
	}

	public void setPaymentterm(PayTerm paymentterm) {
		this.paymentterm = paymentterm;
	}

	public Integer getPaymenttermdays() {
		return paymenttermdays;
	}

	public void setPaymenttermdays(Integer paymenttermdays) {
		this.paymenttermdays = paymenttermdays;
	}

	public Integer getMaxconsignmentstock() {
		return maxconsignmentstock;
	}

	public void setMaxconsignmentstock(Integer maxconsignmentstock) {
		this.maxconsignmentstock = maxconsignmentstock;
	}

	public Integer getMaxconsignmentdays() {
		return maxconsignmentdays;
	}

	public void setMaxconsignmentdays(Integer maxconsignmentdays) {
		this.maxconsignmentdays = maxconsignmentdays;
	}

	public Integer getConsignmentintegerransit() {
		return consignmentInTransit;
	}

	public void setConsignmentintegerransit(Integer consignmentInTransit) {
		this.consignmentInTransit = consignmentInTransit;
	}

	public Integer getMaxcalloffvolume() {
		return maxcalloffvolume;
	}

	public void setMaxcalloffvolume(Integer maxcalloffvolume) {
		this.maxcalloffvolume = maxcalloffvolume;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public BigDecimal getOtherdiscounts() {
		return otherdiscounts;
	}

	public void setOtherdiscounts(BigDecimal otherdiscounts) {
		this.otherdiscounts = otherdiscounts;
	}

	public BigDecimal getCashdiscount() {
		return cashdiscount;
	}

	public void setCashdiscount(BigDecimal cashdiscount) {
		this.cashdiscount = cashdiscount;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public Integer getMaxmetalhedgevolume() {
		return maxmetalhedgevolume;
	}

	public void setMaxmetalhedgevolume(Integer maxmetalhedgevolume) {
		this.maxmetalhedgevolume = maxmetalhedgevolume;
	}

	public String getFormulatype() {
		return formulatype;
	}

	public void setFormulatype(String formulatype) {
		this.formulatype = formulatype;
	}

	public Long getMetalhedgingid() {
		return metalhedgingid;
	}

	public void setMetalhedgingid(Long metalhedgingid) {
		this.metalhedgingid = metalhedgingid;
	}

	public String getFixedmetalfreight() {
		return fixedmetalfreight;
	}

	public void setFixedmetalfreight(String fixedmetalfreight) {
		this.fixedmetalfreight = fixedmetalfreight;
	}

	public String getFixedmetalpremium() {
		return fixedmetalpremium;
	}

	public void setFixedmetalpremium(String fixedmetalpremium) {
		this.fixedmetalpremium = fixedmetalpremium;
	}

	public Currency getConversioncurrencyid() {
		return conversioncurrencyid;
	}

	public void setConversioncurrencyid(Currency conversioncurrencyid) {
		this.conversioncurrencyid = conversioncurrencyid;
	}

	public Currency getMetalcurrencyid() {
		return metalcurrencyid;
	}

	public void setMetalcurrencyid(Currency metalcurrencyid) {
		this.metalcurrencyid = metalcurrencyid;
	}

	// public BigDecimal getExpectedebit() {
	// return expectedebit;
	// }
	//
	// public void setExpectedebit(BigDecimal expectedebit) {
	// this.expectedebit = expectedebit;
	// }

	// public BigDecimal getGrosscostchange() {
	// return grosscostchange;
	// }
	//
	// public void setGrosscostchange(BigDecimal grosscostchange) {
	// this.grosscostchange = grosscostchange;
	// }

	public Integer getStandardcontract() {
		return standardcontract;
	}

	public void setStandardcontract(Integer standardcontract) {
		this.standardcontract = standardcontract;
	}

	public String getLegalwho() {
		return legalwho;
	}

	public void setLegalwho(String legalwho) {
		this.legalwho = legalwho;
	}

	public Date getLegalwhen() {
		return legalwhen;
	}

	public void setLegalwhen(Date legalwhen) {
		this.legalwhen = legalwhen;
	}

	public String getLegalcomment() {
		return legalcomment;
	}

	public void setLegalcomment(String legalcomment) {
		this.legalcomment = legalcomment;
	}

	public BigDecimal getExternalcreditlimit() {
		return externalcreditlimit;
	}

	public void setExternalcreditlimit(BigDecimal externalcreditlimit) {
		this.externalcreditlimit = externalcreditlimit;
	}

	public BigDecimal getIntegerernalcreditlimit() {
		return internalCreditLimit;
	}

	public void setIntegerernalcreditlimit(BigDecimal integerernalcreditlimit) {
		this.internalCreditLimit = integerernalcreditlimit;
	}

	public Long getRatingatradiusid() {
		return ratingatradiusid;
	}

	public void setRatingatradiusid(Long ratingatradiusid) {
		this.ratingatradiusid = ratingatradiusid;
	}

	public String getRatingmoodys() {
		return ratingmoodys;
	}

	public void setRatingmoodys(String ratingmoodys) {
		this.ratingmoodys = ratingmoodys;
	}

	public BigDecimal getForeigncurrencyconversionvolume() {
		return foreigncurrencyconversionvolume;
	}

	public void setForeigncurrencyconversionvolume(BigDecimal foreigncurrencyconversionvolume) {
		this.foreigncurrencyconversionvolume = foreigncurrencyconversionvolume;
	}

	public Boolean getAlreadyhedged() {
		return alreadyhedged;
	}

	public void setAlreadyhedged(Boolean alreadyhedged) {
		this.alreadyhedged = alreadyhedged;
	}

	public BigDecimal getAssumedcurrencyrate() {
		return assumedcurrencyrate;
	}

	public void setAssumedcurrencyrate(BigDecimal assumedcurrencyrate) {
		this.assumedcurrencyrate = assumedcurrencyrate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public SystemUser getCreatedby() {
		return createdby;
	}
	public void setCreatedby(SystemUser createdby) {
		this.createdby = createdby;
	}
	
	/*
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
*/
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public SystemUser getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(SystemUser updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedbyname() {
		return createdbyname;
	}

	public void setCreatedbyname(String createdbyname) {
		this.createdbyname = createdbyname;
	}

	public String getUpdatedbyname() {
		return updatedbyname;
	}

	public void setUpdatedbyname(String updatedbyname) {
		this.updatedbyname = updatedbyname;
	}

	public String getBonusdescription() {
		return bonusdescription;
	}

	public void setBonusdescription(String bonusdescription) {
		this.bonusdescription = bonusdescription;
	}

	public Integer getTollingid() {
		return tollingid;
	}

	public void setTollingid(Integer tollingid) {
		this.tollingid = tollingid;
	}

	public Long getScraptypeid() {
		return scraptypeid;
	}

	public void setScraptypeid(Long scraptypeid) {
		this.scraptypeid = scraptypeid;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
/*
	public Long getPaytermctxid() {
		return paytermctxid;
	}

	public void setPaytermctxid(Long paytermctxid) {
		this.paytermctxid = paytermctxid;
	}

	public String getPaytermcode() {
		return paytermcode;
	}

	public void setPaytermcode(String paytermcode) {
		this.paytermcode = paytermcode;
	}
*/
	public List<Plant> getPlants() {
		return plants;
	}

	public String getPlantsNames() {
		return Joiner.on(",").useForNull("null").join(getPlants());
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	public Integer getConsignmentInTransit() {
		return consignmentInTransit;
	}

	public void setConsignmentInTransit(Integer consignmentInTransit) {
		this.consignmentInTransit = consignmentInTransit;
	}

	public BigDecimal getInternalCreditLimit() {
		return internalCreditLimit;
	}

	public void setInternalCreditLimit(BigDecimal internalCreditLimit) {
		this.internalCreditLimit = internalCreditLimit;
	}
	
	

	public List<ClauseType> getClauses() {
		return clauses;
	}



	public void setClauses(List<ClauseType> clauses) {
		this.clauses = clauses;
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

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}



	// public List<ContractItem> getItems() {
	// return items;
	// }
	//
	// public void setItems(List<ContractItem> items) {
	// this.items = items;
	// }
/*
	public List<ContractItem> getItems() {
		return items;
	}

	public void setItems(List<ContractItem> items) {
		this.items = items;
	}
*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + "]";
	}

}
