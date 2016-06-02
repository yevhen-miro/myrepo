package de.hydro.gv.mplus.utils;

public enum ContractStatus {
	
    DELETED("Deleted"),
    NEW("New"),
    UNAPPROVED("Unapproved"),
    APPROVALPENDING("ApprovalPending"),
    APPROVED("Approved"),
    FINALIZED("Finalized"),
    FINALIZEPENDING("FinalizePending"),
    CANCELLED("Cancelled"),
    CANCELPENDING("CancelPending");
	
	private String contractStatus;
	
	private ContractStatus(String pStatus) {
		contractStatus = pStatus;
	}
	
	public String getContractStatus() {
		return contractStatus;
	}
	

 }


	
	
	

	 
	

