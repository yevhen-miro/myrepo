package de.hydro.gv.mplus.utils;

public enum ActivityType {
	
	NEW("New"),
	APPROVE_REQUEST("Approve Request"),
	APPROVED("Approved"),
	FINALIZED("Finalized"),
	DELETE_REQUEST("Delete Request"),
	DELETED("Deleted"),
	UNFINALIZED("Unfinalized"),
	FILE_UPLOAD("File Upload"),
	FINALIZE_REQUEST("Finalize Request"),
	CANCEL_REQUEST("Cancel Request"),
	CHANGED("Changed");
	
	
	private String activityType;
	
	private ActivityType(String pActivityType) {
		activityType = pActivityType;
	}
	
	public String getActivityType() {
		return activityType;
	}
	

 }


	
	
	

	 
	

