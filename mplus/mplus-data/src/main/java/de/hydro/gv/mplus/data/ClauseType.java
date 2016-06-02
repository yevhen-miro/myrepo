package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="ContractClauseTypes",catalog="MARGIN_PLUS", schema="DBO")
public class ClauseType implements Serializable{

	private static final long serialVersionUID = 1231347822318739092L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "Id" )
	private Long id ;
	
	@Column( name = "Name" )
	private String name ;
	
	@Column( name = "Category" )
	private Integer category ;
	
	@Column( name = "DefaultState" )
	private Boolean isDefault ;
	
	@Column( name = "Description" )
	private String description ;
	
	@Column( name = "Created" )
	private Date created ;
	
	@Column( name = "CreatedBy" )
	private String createdBy ;
	
	@Column( name = "Updated" )
	private Date updated ;
	
	@Column( name = "UpdatedBy" )
	private String updatedBy ;
	
	@Column( name = "CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdByName ;	
	
	@Column( name = "UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		ClauseType other = (ClauseType) obj;
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
