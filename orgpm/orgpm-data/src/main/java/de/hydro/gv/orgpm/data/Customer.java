package de.hydro.gv.orgpm.data;

import java.io.Serializable; // is important to translate objects into data thread
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="CustomerEntity") //Annotation for a DB-Object
@Table(name="customer_table") //Table in DB
@NamedQueries({
	@NamedQuery(name="customer.find.all",query="SELECT c FROM CustomerEntity AS c"),
	@NamedQuery(name="customer.delete.all",query="DELETE FROM CustomerEntity"),
	@NamedQuery(name="customer.find.by.lastName",query="SELECT c FROM CustomerEntity AS c WHERE c.lastName= :lastName"),
	@NamedQuery(name="customer.find.by.empty.orders",query="SELECT c FROM CustomerEntity AS c WHERE SIZE(c.orders)=0")

}
)
public class Customer implements Serializable{
	
	@Id
	@GeneratedValue// (strategy=GenerationType.SEQUENCE) attribute
	@Column(name = "pk")
	private Integer id;
	
	@Column(name = "fn")
	private String firstName;

	@Column(name = "ln")
	private String lastName;
	
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToMany(cascade={CascadeType.PERSIST},mappedBy="owner")
	private Collection<Order> orders;
	
	public Customer (){ // Default constructor
	}
	
	public Customer(String firstName, String lastName, Date dateOfBirth) { //Constructor with parameters
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		// TODO Auto-generated constructor stub
	}
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
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

	public void addOrder(Order order) {
		if(orders==null){orders=new ArrayList<Order>();
		orders.add(order);
		order.setOwner(this);
		}
	}

}
