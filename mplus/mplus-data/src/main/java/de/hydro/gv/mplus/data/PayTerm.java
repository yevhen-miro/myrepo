package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name="System_PayTermMap",catalog="MARGIN_PLUS", schema="DBO")
public class PayTerm implements Serializable{

	private static final long serialVersionUID = 1231347822318739092L;

	@EmbeddedId
	private PayTermId id ;
	
	@Column( name = "Map_CreditDays" )
	private Integer creditDays;
	
	@Column( name = "Map_CreditPct" )
	private Integer creditPct ;
	
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
	private String CreatedByName ;
	
	@Column( name = "Map_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String UpdatedByName;
	
	@Column( name = "Description" )
	private String description ;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PayTermId getId() {
		return id;
	}

	public void setId(PayTermId id) {
		this.id = id;
	}

	public Integer getCreditDays() {
		return creditDays;
	}

	public void setCreditDays(Integer creditDays) {
		this.creditDays = creditDays;
	}

	public Integer getCreditPct() {
		return creditPct;
	}

	public void setCreditPct(Integer creditPct) {
		this.creditPct = creditPct;
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
		PayTerm other = (PayTerm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id.getCtxCode() + " " + description;
	}

	

	
	
}
