package de.hydro.gv.orgpm.model;

import java.util.Date;

import javax.enterprise.context.RequestScoped; //CDI Property
import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@RequestScoped
@Named //Name reference in JSF will be the class name by default
//@Model // Alternative to RequestScoped and Named.
public class Customer {//Model View for JSF
	
	private Integer id;
	
	private CreditCard creditCard;
	
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	@Size(min=2,max=30, message="{first.name.min.max}")
	@Pattern(regexp="[a-zA-Z]*")
	private String firstName;
	
	@Size(min=2,max=30, message="{first.name.min.max}")
	@Pattern(regexp="[a-zA-Z]*")
	private String lastName;
	
	@Past
	private Date dateOfBirth;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
}
