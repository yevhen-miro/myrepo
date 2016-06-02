package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name = "dbo.Organization_CBU")
@NamedQueries( {
	@NamedQuery( name = "cbu.find.by.bu", query = "SELECT c FROM CBU AS c, BU AS b "
			 	+ "WHERE c.bu.id = b.id AND b = :bu ORDER BY c.name desc" )})

public class CBU implements Serializable{
	
	private static final long serialVersionUID = 6326615801907973850L;

	@Id
	@Column( name = "CBU_Id" )
	private Long id ;
	
	@Column( name = "CBU_Name" )
	private String name ;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "BU_Id", foreignKey = @ForeignKey( name = "FK_CBU_BU" ) )
	private BU bu ;
	
	@Column( name = "CBU_MDXValue" )
	private String mdxValue ;
	
	@Column( name = "CBU_Enabled" )
	private Boolean isEnabled ;
	
	@Column( name = "TDOrg_Id" )
	private Long tdorg ;
	
	@Column( name = "CBU_IsAllowed" )
	private Boolean isAllowed ;
	
	@Column( name = "CBU_ForecastWeights" )
	private String forecastWeights ;
	
	@Column( name = "CBU_BudgetYear" )
	private Integer budgetYear ;
	
	@Column( name = "ProdMgrId" )
	private String mgrid ;
	
	@Column( name = "HeadOfBUId" )
	private String headOfBuid ;
	
	@Column( name = "AssumedPremium" )
	private BigDecimal assumedPremium ;
	
	@Column( name = "AssumedMetalFreight" )
	private BigDecimal assumedMetalFreight ;
	
	@Column( name = "CBU_Created" )
	private Date created ;
	
	@Column( name = "CBU_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "CBU_Updated" )
	private Date updated ;
	
	@Column( name = "CBU_UpdatedBy" )
	private String updatedBy ;
	
	@Column( name = "CBU_CreatedByName" )
	private String createdByName ;
	
	@Column( name = "CBU_UpdatedByName" )
	private String updatedByName ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BU getBu() {
		return bu;
	}

	public void setBu(BU bu) {
		this.bu = bu;
	}

	public String getMdxValue() {
		return mdxValue;
	}

	public void setMdxValue(String mdxValue) {
		this.mdxValue = mdxValue;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Long getTdorg() {
		return tdorg;
	}

	public void setTdorg(Long tdorg) {
		this.tdorg = tdorg;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public String getForecastWeights() {
		return forecastWeights;
	}

	public void setForecastWeights(String forecastWeights) {
		this.forecastWeights = forecastWeights;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getMgrid() {
		return mgrid;
	}

	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}

	public String getHeadOfBuid() {
		return headOfBuid;
	}

	public void setHeadOfBuid(String headOfBuid) {
		this.headOfBuid = headOfBuid;
	}

	public BigDecimal getAssumedPremium() {
		return assumedPremium;
	}

	public void setAssumedPremium(BigDecimal assumedPremium) {
		this.assumedPremium = assumedPremium;
	}

	public BigDecimal getAssumedMetalFreight() {
		return assumedMetalFreight;
	}

	public void setAssumedMetalFreight(BigDecimal assumedMetalFreight) {
		this.assumedMetalFreight = assumedMetalFreight;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

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
		CBU other = (CBU) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cbu [name=" + name + "]";
	}


}
