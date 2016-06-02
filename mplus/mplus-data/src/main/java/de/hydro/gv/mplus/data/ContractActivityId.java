package de.hydro.gv.mplus.data;

import java.io.Serializable;

public class ContractActivityId implements Serializable{
	
	private static final long serialVersionUID = 1322156687657879113L;
	private Long contractId;
	private Integer activityId;
	
	public ContractActivityId() {	
	}
	
	public ContractActivityId(Long conId, Integer actId ) {
		this.contractId = conId;
		this.activityId = actId;
	}

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
		ContractActivityId other = (ContractActivityId) obj;
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
	
	
	

}
