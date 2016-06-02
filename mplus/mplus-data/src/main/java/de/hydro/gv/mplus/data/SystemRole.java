package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import de.hydro.gv.mplus.auth.Login;

@Entity
@Table (name = "dbo.System_RoleMap")
@NamedQueries( {
	@NamedQuery( name = "roles.find.role.by.hydroid", query = "SELECT r FROM SystemUser AS u, SystemRole r  WHERE u.map.id = r.id AND u.shortName = :hydroid") } )
public class SystemRole implements Serializable{

	private static final long serialVersionUID = -4871817413634229300L;

	@Id
	@Column(name = "Map_Id")
	private Long id ;
	
	@Column( name = "Map_DisplayName" )
	private String displayName ;
	
	@Column( name = "Map_InternalName" )
	private String internalName ;
	
	@Column( name = "Map_Description" )
	private String description ;
	
	@Column( name = "Map_ADName" )
	private String ADName ;
	
	@Column( name = "Map_LocalName" )
	private String localName ;
	
	@Column( name = "Dim_Id" )
	private Integer dimId ;
	
	@Column( name = "Map_LocalRoleName" )
	private String localRoleName ;
	
	@Column( name = "Map_Created" )
	private Date created ;
	
	@Column( name = "Map_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "Map_Updated" )
	private Date updated ;
	
	@Column( name = "Map_UpdatedBy" )
	private String updatedBy ;
	
	
	@Column( name = "Map_CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdyByName ;
	
	@Column( name = "Map_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String updatedByName ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getADName() {
		return ADName;
	}

	public void setADName(String aDName) {
		ADName = aDName;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public Integer getDimId() {
		return dimId;
	}

	public void setDimId(Integer dimId) {
		this.dimId = dimId;
	}

	public String getLocalRoleName() {
		return localRoleName;
	}

	public void setLocalRoleName(String localRoleName) {
		this.localRoleName = localRoleName;
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

	public String getCreatedyByName() {
		return createdyByName;
	}

	public void setCreatedyByName(String createdyByName) {
		this.createdyByName = createdyByName;
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
		SystemRole other = (SystemRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return displayName;
	}
	
	


}
