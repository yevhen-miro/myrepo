package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dbo.ContractItems")
@IdClass(ContractItemId.class)
@NamedQueries({
		@NamedQuery(name = "contractitems.find.all", query = "SELECT ci FROM ContractItem AS ci ORDER BY ci.contract desc"),
		@NamedQuery(name = "contractitems.find.by.contract", query = "SELECT ci FROM ContractItem AS ci, Contract AS c "
				+ "WHERE ci.contract.id = c.id and ci.contract = :contract") })

public class ContractItem implements Serializable {

	private static final long serialVersionUID = -333458573455788354L;

	@Id
	@Column(name="ContractId", insertable=false, updatable= false)
	private Long contractId;
	
	@Id
	@Column(name="ProdInfoId")
	private Integer prodInfoId;
	
	@Id
	@Column(name="ScrapTypeCode")
	private String scrapTypeCode;
	
	@Id
	@Column(name="DateID", insertable=false, updatable= false)
	private Integer dateId;
	
	@Id
	@Column(name="IsCurYear")
	private Boolean isCurrentYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ContractId")
	private Contract contract;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "DateId" )
	private TimeDate date;

	@Column(name = "ProdInfoName")
	private String prodInfoName;

	@Column(name = "Volume")
	private BigDecimal volume;

	@Column(name = "CTP")
	private BigDecimal ctp;
	
	@Column( name = "Created" )
	private Date created ;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "UpdatedBy")
	private String updatedBy;

	@Column(name = "UpdatedByName")
	private String updatedByName;

	@Column(name = "ProdInfoMode")
	private String prodInfoMode;

	@Column(name = "GM")
	private BigDecimal gm;

	@Column(name = "Updated")
	private Date updated;

	@Column(name = "CreatedByName")
	private String createdByName;

	@Column(name = "ProductGroup_Id")
	private Long productGroup;
	
	@Column(name = "GrossCostChange")
	private BigDecimal costChange;
	
	@Column(name = "NetEffect")
	private BigDecimal netEffect;
	
	@Column(name = "EBIT")
	private BigDecimal ebit;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getProdInfoId() {
		return prodInfoId;
	}

	public void setProdInfoId(Integer prodInfoId) {
		this.prodInfoId = prodInfoId;
	}

	public String getScrapTypeCode() {
		return scrapTypeCode;
	}

	public void setScrapTypeCode(String scrapTypeCode) {
		this.scrapTypeCode = scrapTypeCode;
	}

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public Boolean getIsCurrentYear() {
		return isCurrentYear;
	}

	public void setIsCurrentYear(Boolean isCurrentYear) {
		this.isCurrentYear = isCurrentYear;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public TimeDate getDate() {
		return date;
	}

	public void setDate(TimeDate date) {
		this.date = date;
	}

	public String getProdInfoName() {
		return prodInfoName;
	}

	public void setProdInfoName(String prodInfoName) {
		this.prodInfoName = prodInfoName;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getCtp() {
		return ctp;
	}

	public void setCtp(BigDecimal ctp) {
		this.ctp = ctp;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public String getProdInfoMode() {
		return prodInfoMode;
	}

	public void setProdInfoMode(String prodInfoMode) {
		this.prodInfoMode = prodInfoMode;
	}

	public BigDecimal getGm() {
		return gm;
	}

	public void setGm(BigDecimal gm) {
		this.gm = gm;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Long getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(Long productGroup) {
		this.productGroup = productGroup;
	}

	public BigDecimal getCostChange() {
		return costChange;
	}

	public void setCostChange(BigDecimal costChange) {
		this.costChange = costChange;
	}

	public BigDecimal getNetEffect() {
		return netEffect;
	}

	public void setNetEffect(BigDecimal netEffect) {
		this.netEffect = netEffect;
	}

	public BigDecimal getEbit() {
		return ebit;
	}

	public void setEbit(BigDecimal ebit) {
		this.ebit = ebit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((dateId == null) ? 0 : dateId.hashCode());
		result = prime * result + ((isCurrentYear == null) ? 0 : isCurrentYear.hashCode());
		result = prime * result + ((prodInfoId == null) ? 0 : prodInfoId.hashCode());
		result = prime * result + ((scrapTypeCode == null) ? 0 : scrapTypeCode.hashCode());
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
		ContractItem other = (ContractItem) obj;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (dateId == null) {
			if (other.dateId != null)
				return false;
		} else if (!dateId.equals(other.dateId))
			return false;
		if (isCurrentYear == null) {
			if (other.isCurrentYear != null)
				return false;
		} else if (!isCurrentYear.equals(other.isCurrentYear))
			return false;
		if (prodInfoId == null) {
			if (other.prodInfoId != null)
				return false;
		} else if (!prodInfoId.equals(other.prodInfoId))
			return false;
		if (scrapTypeCode == null) {
			if (other.scrapTypeCode != null)
				return false;
		} else if (!scrapTypeCode.equals(other.scrapTypeCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractItem [contractId=" + contractId + ", prodInfoId=" + prodInfoId + ", scrapTypeCode="
				+ scrapTypeCode + ", dateId=" + dateId + ", isCurrentYear=" + isCurrentYear + "]";
	}
	
	

}
