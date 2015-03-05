package de.hydro.gv.orgpm.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity (name="OrderEntity")//Entity must have a primary key
@Table (name="order_table")
@NamedQueries({
	@NamedQuery(name="order.find.all",query="SELECT o FROM OrderEntity o"),
	@NamedQuery(name="order.delete.all",query="DELETE FROM OrderEntity")

}
)

public class Order {

	@Id
	@GeneratedValue
	private Integer id;
	private Date date;
	private float value;
	
	@ManyToOne
	@JoinColumn( name="fk_cust" ) //mapping
	private Customer owner;
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public Order(){
	}
	
	public Order (Date date, float value){
		this.date = date;
		this.value=value;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
}
