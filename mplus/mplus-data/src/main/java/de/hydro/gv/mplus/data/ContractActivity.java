package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "ContractActivities", catalog = "MARGIN_PLUS", schema = "DBO")
@NamedQueries({ 
	@NamedQuery(name = "contract.activities.find.all", query = "SELECT act FROM ContractActivity AS act"),
	@NamedQuery(name = "contract.activities.find.by.contract", query = "SELECT act FROM ContractActivity AS act WHERE act.contract = :contract ORDER BY act.activityId DESC")
})
@IdClass(ContractActivityId.class)
public class ContractActivity implements Serializable {

	private static final long serialVersionUID = 1231347822318739092L;
	
	@Id
	@Column(name="ContractId", insertable=false, updatable= false)
	private Long contractId;
	
	@Id
	@Column( name = "ActivityId" )
	private Integer activityId;
	
	@Column( name = "ActivityType" )
	private Integer activityType;
	
	@ManyToOne
	@JoinColumn(name="ContractId")
	private Contract contract;
	
	@Column(name = "Message")
	private String Message;

	@Column(name = "MetaData")
	private String metaData;

	@Column(name = "Created")
	private Date Created;

	@Column(name = "CreatedBy")
	private String CreatedBy;

	@Column(name = "CreatedByName", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String CreatedByName;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getMetaData() {
		return metaData;
	}

	public void setMetaDate(String metaData) {
		this.metaData = metaData;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getCreatedByName() {
		return CreatedByName;
	}

	public void setCreatedByName(String createdByName) {
		CreatedByName = createdByName;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
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
		ContractActivity other = (ContractActivity) obj;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractActivity [contractId=" + contractId + ", activityId=" + activityId + "]";
	}

	

	

}
