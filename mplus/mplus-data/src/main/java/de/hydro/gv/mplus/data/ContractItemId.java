package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class ContractItemId implements Serializable {

	private static final long serialVersionUID = 1322156687657879113L;
	private Long contractId;
	private Integer prodInfoId;
	private String scrapTypeCode;
	private Integer dateId;
	private Boolean isCurrentYear;
	
	public ContractItemId() {	
	}
	
	public ContractItemId(Long conId, Integer prodInfoId, String scrapTypeCode, Integer dateId, Boolean isCurrYear ) {
		this.contractId = conId;
		this.prodInfoId = prodInfoId;
		this.scrapTypeCode = scrapTypeCode;
		this.dateId = dateId;
		this.isCurrentYear = isCurrYear;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getProdInfoId() {
		return prodInfoId;
	}

	public void setProdInfoId(Integer prodInfoId) {
		this.prodInfoId = prodInfoId;
	}

	public String getScrapTypeCode() {
		return scrapTypeCode;
	}

	public void setScrapTypeCode(String scrapTypeCode) {
		this.scrapTypeCode = scrapTypeCode;
	}

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public Boolean getIsCurrentYear() {
		return isCurrentYear;
	}

	public void setIsCurrentYear(Boolean isCurrentYear) {
		this.isCurrentYear = isCurrentYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((dateId == null) ? 0 : dateId.hashCode());
		result = prime * result + ((isCurrentYear == null) ? 0 : isCurrentYear.hashCode());
		result = prime * result + ((prodInfoId == null) ? 0 : prodInfoId.hashCode());
		result = prime * result + ((scrapTypeCode == null) ? 0 : scrapTypeCode.hashCode());
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
		ContractItemId other = (ContractItemId) obj;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (dateId == null) {
			if (other.dateId != null)
				return false;
		} else if (!dateId.equals(other.dateId))
			return false;
		if (isCurrentYear == null) {
			if (other.isCurrentYear != null)
				return false;
		} else if (!isCurrentYear.equals(other.isCurrentYear))
			return false;
		if (prodInfoId == null) {
			if (other.prodInfoId != null)
				return false;
		} else if (!prodInfoId.equals(other.prodInfoId))
			return false;
		if (scrapTypeCode == null) {
			if (other.scrapTypeCode != null)
				return false;
		} else if (!scrapTypeCode.equals(other.scrapTypeCode))
			return false;
		return true;
	}

	


}
