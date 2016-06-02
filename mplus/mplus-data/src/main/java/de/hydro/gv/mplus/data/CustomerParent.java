package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
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
@Table (name = "dbo.Customer_Parents")
@NamedQueries( {
	@NamedQuery( name = "customerparents.find.all", query = "SELECT cp FROM CustomerParent AS cp ORDER by cp.name"),
	@NamedQuery( name = "customerparents.find.by.id", query = "SELECT cp FROM CustomerParent AS cp WHERE cp.id = :id")}) 
@Cacheable(true) 
public class CustomerParent implements Serializable{

	private static final long serialVersionUID = 2562411062050929847L;
	
	@Id
	@Column( name = "Parent_Id")
	private Long id;
	
	@Column( name = "Parent_Name")
	private String name;
	
	@Column( name = "Parent_MDXValue")
	private String MDXValue;
	
	@Column( name = "Parent_IsAllowed")
	private Boolean isAllowed;
	
	@Column( name = "Parent_ChildCount")
	private Integer childCount;
	
	@Column( name = "Parent_Created")
	private Date parentCreated;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "Parent_CreatedBy", foreignKey = @ForeignKey( name = "FK_CUSTOMERPARENT_CREATED_BY" ) )
	private SystemUser createdBy;
	
	@Column( name = "Parent_Updated")
	private Date parentUpdated;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "Parent_UpdatedBy", foreignKey = @ForeignKey( name = "FK_CUSTOMERPARENT_UPDATED_BY" ) )
	private SystemUser updatedBy;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "Parent_CreatedByName", foreignKey = @ForeignKey( name = "FK_CUSTOMERPARENT_CREATED_BY_NAME" ) )
	private SystemUser createdByName;;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "Parent_UpdatedByName", foreignKey = @ForeignKey( name = "FK_CUSTOMERPARENT_UPDATED_BY_NAME" ) )
	private SystemUser updatedByName;

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

	public String getMDXValue() {
		return MDXValue;
	}

	public void setMDXValue(String mDXValue) {
		MDXValue = mDXValue;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public Date getParentCreated() {
		return parentCreated;
	}

	public void setParentCreated(Date parentCreated) {
		this.parentCreated = parentCreated;
	}

	public SystemUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(SystemUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getParentUpdated() {
		return parentUpdated;
	}

	public void setParentUpdated(Date parentUpdated) {
		this.parentUpdated = parentUpdated;
	}

	public SystemUser getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(SystemUser updatedBy) {
		this.updatedBy = updatedBy;
	}

	public SystemUser getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(SystemUser createdByName) {
		this.createdByName = createdByName;
	}

	public SystemUser getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(SystemUser updatedByName) {
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
		CustomerParent other = (CustomerParent) obj;
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
