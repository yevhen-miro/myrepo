package de.hydro.gv.mplus.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PayTermId implements Serializable{

	private static final long serialVersionUID = 1322156687657879113L;
	
	@Column( name = "Ctx_Id" )
	private Integer ctxId;

	@Column( name = "Ctx_Code" )
	private String ctxCode;

	public PayTermId() {
		super();
	}

	public Integer getCtxId() {
		return ctxId;
	}

	public void setCtxId(Integer ctxId) {
		this.ctxId = ctxId;
	}

	public String getCtxCode() {
		return ctxCode;
	}

	public void setCtxCode(String ctxCode) {
		this.ctxCode = ctxCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctxCode == null) ? 0 : ctxCode.hashCode());
		result = prime * result + ((ctxId == null) ? 0 : ctxId.hashCode());
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
		PayTermId other = (PayTermId) obj;
		if (ctxCode == null) {
			if (other.ctxCode != null)
				return false;
		} else if (!ctxCode.equals(other.ctxCode))
			return false;
		if (ctxId == null) {
			if (other.ctxId != null)
				return false;
		} else if (!ctxId.equals(other.ctxId))
			return false;
		return true;
	}


	
	
	
}
