package de.hydro.gv.orgpm.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped //CDI Annotation
public class RequestId {
	

	private int requestId;
	
	public int getRequestId() {
		return requestId;
	}
	
	
	@PostConstruct
	public void init(){
		System.out.println("Request-ID "+requestId + " was generated");
	}
	
	@PreDestroy
	public void kill(){
		System.out.println("Request-ID "+requestId + " was deleted!");
	}
	




	
	
}
