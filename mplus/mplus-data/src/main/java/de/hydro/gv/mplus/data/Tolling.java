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
@Table(name="Incoterms",catalog="MARGIN_PLUS", schema="DBO")
public class Tolling implements Serializable{

	private static final long serialVersionUID = 1231347822318739092L;

	@Id
	@Column( name = "Tolling_Id" )
	private  Integer id ;
	
	@Column( name = "Tolling_Name" )
	private String name ;
	
	@Column( name = "Tolling_MDXValue" )
	private String mdx ;
	
	@Column( name = "Tolling_Created" )
	private Date created ;
	
	@Column( name = "Tolling_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "Tolling_Updated" )
	private Date updated ;
	
	@Column( name = "Tolling_UpdatedBy" )
	private String updatedBy ;
	
	@Column( name = "Tolling_CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String CreatedByName ;
	
	@Column( name = "Tolling_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String UpdatedByName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return CreatedByName;
	}

	public void setCreatedByName(String createdByName) {
		CreatedByName = createdByName;
	}

	public String getUpdatedByName() {
		return UpdatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		UpdatedByName = updatedByName;
	}

	public String getMdx() {
		return mdx;
	}

	public void setMdx(String mdx) {
		this.mdx = mdx;
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
		Tolling other = (Tolling) obj;
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
