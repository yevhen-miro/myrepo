package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name = "dbo.Organization_BU")
@NamedQueries({ @NamedQuery(name = "bu.find.by.cbu", query = "SELECT b FROM BU AS b, CBU as c WHERE c.bu.id = b.id AND c = :cbu")})
public class BU implements Serializable{
	
	private static final long serialVersionUID = 6326615801907973850L;

	@Id
	@Column( name = "BU_Id" )
	private Long id ;
	
	@Column( name = "BU_Name" )
	private String name ;
	
	@Column( name = "BU_Enabled" )
	private Boolean isEnabled ;
	
	@Column( name = "BU_MDXValue" )
	private String mdx ;
	
	@Column( name = "BU_BudgetYear" )
	private Integer budgetYear ;
	
	@Column( name = "BU_Created" )
	private Date created ;
	
	@Column( name = "BU_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "BU_Updated" )
	private Date updated ;
	
	@Column( name = "BU_UpdatedBy" )
	private String updatedBy ;
	
	@Column( name = "BU_CreatedByName" )
	private String createdByName ;
	
	@Column( name = "BU_UpdatedByName" )
	private String updatedByName ;
	
	@Column( name = "BU_BAControllerId" )
	private String controllerId;

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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMdx() {
		return mdx;
	}

	public void setMdx(String mdx) {
		this.mdx = mdx;
	}

	public Integer getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
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

	public String getControllerId() {
		return controllerId;
	}

	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
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
		BU other = (BU) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}



}
