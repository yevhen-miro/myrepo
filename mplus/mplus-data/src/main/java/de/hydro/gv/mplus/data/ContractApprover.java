package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "ContractApprovers", catalog = "MARGIN_PLUS", schema = "DBO")
@NamedQueries({ 
	@NamedQuery(name = "contract.approvers.find.all", query = "SELECT ca FROM ContractApprover AS ca"),
	@NamedQuery(name = "contract.approvers.find.by.contract", query = "SELECT ca FROM ContractApprover AS ca WHERE ca.contract = :contract ORDER BY ca.signLevel")
})
@IdClass(ContractApproverId.class)
public class ContractApprover implements Serializable {

	private static final long serialVersionUID = 1231347822318739092L;
	
	@Id
	@Column(name="ContractId", insertable=false, updatable= false)
	private Long contractId;
	
	@Id
	@Column( name = "signLevel" )
	private Integer signLevel;
	
	@Id
	@Column(name="UserId", insertable=false, updatable= false)
	private String approverId;
	
	@ManyToOne
	@JoinColumn(name="ContractId")
	private Contract contract;

	@ManyToOne
	@JoinColumn(name="UserId")
	private SystemUser user;

	@Column(name = "HasApproved")
	private Boolean isApproved;

	@Column(name = "HasFinalized")
	private Boolean isFinalized;

	@Column(name = "HasDeleted")
	private Boolean isDeleted;

	@Column(name = "Created")
	private Date Created;

	@Column(name = "CreatedBy")
	private String CreatedBy;

	@Column(name = "Updated")
	private Date Updated;

	@Column(name = "UpdatedBy")
	private String UpdatedBy;

	@Column(name = "CreatedByName", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String CreatedByName;

	@Column(name = "UpdatedByName", length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String UpdatedByName;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getSignLevel() {
		return signLevel;
	}

	public void setSignLevel(Integer signLevel) {
		this.signLevel = signLevel;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsFinalized() {
		return isFinalized;
	}

	public void setIsFinalized(Boolean isFinalized) {
		this.isFinalized = isFinalized;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public Date getUpdated() {
		return Updated;
	}

	public void setUpdated(Date updated) {
		Updated = updated;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
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
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((signLevel == null) ? 0 : signLevel.hashCode());
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
		ContractApprover other = (ContractApprover) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (signLevel == null) {
			if (other.signLevel != null)
				return false;
		} else if (!signLevel.equals(other.signLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractApprover [contractId=" + contractId + ", signLevel=" + signLevel + ", approverId=" + approverId
				+ "]";
	}

	

}
