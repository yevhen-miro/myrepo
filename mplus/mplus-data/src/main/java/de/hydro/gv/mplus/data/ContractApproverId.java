package de.hydro.gv.mplus.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


public class ContractApproverId implements Serializable{

	private static final long serialVersionUID = 1322156687657879113L;
	private Long contractId;
	private Integer signLevel;
	private String approverId;
	
	public ContractApproverId() {	
	}
	
	public ContractApproverId(Long conId, Integer signLevel, String approverId ) {
		this.contractId = conId;
		this.signLevel = signLevel;
		this.approverId = approverId;
	}
	
	public Long getConId() {
		return contractId;
	}
	public void setConId(Long conId) {
		this.contractId = conId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approverId == null) ? 0 : approverId.hashCode());
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
		ContractApproverId other = (ContractApproverId) obj;
		if (approverId == null) {
			if (other.approverId != null)
				return false;
		} else if (!approverId.equals(other.approverId))
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
	
	
	
	
	
}
