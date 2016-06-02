package de.hydro.gv.mplus.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ContractPlantId implements Serializable{

	private static final long serialVersionUID = 1322156687657879113L;
	
	@Column( name = "ContractId" )
	private Contract contractId;

	@Column( name = "PlantId" )
	private Plant plantId;

	public ContractPlantId() {
		super();
	}

	public Contract getContractId() {
		return contractId;
	}

	public void setContractId(Contract contractId) {
		this.contractId = contractId;
	}

	public Plant getPlantId() {
		return plantId;
	}

	public void setPlantId(Plant plantId) {
		this.plantId = plantId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((plantId == null) ? 0 : plantId.hashCode());
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
		ContractPlantId other = (ContractPlantId) obj;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (plantId == null) {
			if (other.plantId != null)
				return false;
		} else if (!plantId.equals(other.plantId))
			return false;
		return true;
	}
	
	
	
}
