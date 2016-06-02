package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.junit.runners.ParentRunner;

@Entity
@Table (name = "dbo.CUSTOMER_CUSTOMERS")
@NamedQueries( {
	@NamedQuery( name = "customers.find.all", query = "SELECT c FROM Customer AS c" ),
	@NamedQuery( name = "customers.find.by.id", query = "SELECT c FROM Customer AS c WHERE c.customer_id = :id" ),
	@NamedQuery( name = "customers.find.by.exact.name", query = "SELECT c FROM Customer AS c WHERE c.customer_fullname = :name" ),
	@NamedQuery( name = "customers.find.by.name", query = "SELECT c FROM Customer AS c WHERE upper(c.customer_fullname) LIKE :text" ),
	@NamedQuery( name = "customers.find.by.parent", query = "SELECT c FROM Customer AS c, CustomerParent cp " 
				+ "WHERE c.parent_id.id = cp.id AND cp = :parent"),
	@NamedQuery( name = "customers.find.parent.by.customer", query = "SELECT cp FROM Customer AS c, CustomerParent cp " 
			+ "WHERE c.parent_id.id = cp.id AND c = :customer")} )
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer implements Serializable, Cloneable{

	private static final long serialVersionUID = -1419623977705571539L;
	
	@Id
	@Column( name = "Customer_Id" )
	private Long customer_id;
	
	@Column( name = "Customer_Enabled" )
	private Boolean customer_enabled;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CustomerType_Id", foreignKey = @ForeignKey( name = "FK_CUSTOMER_CUSTOMERTYPE" ) )
	private CustomerType customer_type;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "Parent_Id", foreignKey = @ForeignKey( name = "FK_CUSTOMER_CUSTOMERPARENT" ) )
	private CustomerParent parent_id;
	
	@Column( name = "Customer_Name" )
	private String customer_name;
	
	@Column( name = "Customer_FullName" )
	private String customer_fullname;
	
	@Column( name = "Customer_Rank" )
	private Character customer_rank;
	
	@Column( name = "Customer_PremiumType" )
	private String customer_premiumtype;
	
	@Column( name = "Customer_IsAllowed" )
	private Boolean customer_isallowed;
	
	@Column( name = "Customer_MDXValue" )
	private String customer_mdxvalue;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "Customer_Created" )
	private Date customer_created;
	
	@Column( name = "Customer_CreatedBy" )
	private String customer_created_by;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "Customer_Updated" )
	private Date customer_updatede;
	
	@Column( name = "Customer_UpdatedBy" )
	private String customer_updated_by;
	
	@Column( name = "Customer_CreatedByName" )
	private String customer_created_by_name;
	
	@Column( name = "Customer_UpdatedByName" )
	private String customer_updated_by_name;
	
	@Column( name = "PostCode" )
	private String postcode;
	
	@Column( name = "City" )
	private String city;
	
	@Column( name = "IgnoreKURSK" )
	private Boolean ignore_kursk;

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Boolean getCustomer_enabled() {
		return customer_enabled;
	}

	public void setCustomer_enabled(Boolean customer_enabled) {
		this.customer_enabled = customer_enabled;
	}

	public CustomerType getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type_id(CustomerType customer_type) {
		this.customer_type = customer_type;
	}

	public CustomerParent getParent_id() {
		return parent_id;
	}

	public void setParent_id(CustomerParent parent_id) {
		this.parent_id = parent_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_fullname() {
		return customer_fullname;
	}

	public void setCustomer_fullname(String customer_fullname) {
		this.customer_fullname = customer_fullname;
	}

	public Character getCustomer_rank() {
		return customer_rank;
	}

	public void setCustomer_rank(Character customer_rank) {
		this.customer_rank = customer_rank;
	}

	public String getCustomer_premiumtype() {
		return customer_premiumtype;
	}

	public void setCustomer_premiumtype(String customer_premiumtype) {
		this.customer_premiumtype = customer_premiumtype;
	}

	public Boolean getCustomer_isallowed() {
		return customer_isallowed;
	}

	public void setCustomer_isallowed(Boolean customer_isallowed) {
		this.customer_isallowed = customer_isallowed;
	}

	public String getCustomer_mdxvalue() {
		return customer_mdxvalue;
	}

	public void setCustomer_mdxvalue(String customer_mdxvalue) {
		this.customer_mdxvalue = customer_mdxvalue;
	}

	public Date getCustomer_created() {
		return customer_created;
	}

	public void setCustomer_created(Date customer_created) {
		this.customer_created = customer_created;
	}

	public String getCustomer_created_by() {
		return customer_created_by;
	}

	public void setCustomer_created_by(String customer_created_by) {
		this.customer_created_by = customer_created_by;
	}

	public Date getCustomer_updatede() {
		return customer_updatede;
	}

	public void setCustomer_updatede(Date customer_updatede) {
		this.customer_updatede = customer_updatede;
	}

	public String getCustomer_updated_by() {
		return customer_updated_by;
	}

	public void setCustomer_updated_by(String customer_updated_by) {
		this.customer_updated_by = customer_updated_by;
	}

	public String getCustomer_created_by_name() {
		return customer_created_by_name;
	}

	public void setCustomer_created_by_name(String customer_created_by_name) {
		this.customer_created_by_name = customer_created_by_name;
	}

	public String getCustomer_updated_by_name() {
		return customer_updated_by_name;
	}

	public void setCustomer_updated_by_name(String customer_updated_by_name) {
		this.customer_updated_by_name = customer_updated_by_name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIgnore_kursk() {
		return ignore_kursk;
	}

	public void setIgnore_kursk(Boolean ignore_kursk) {
		this.ignore_kursk = ignore_kursk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
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
		Customer other = (Customer) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.customer_fullname;
	}
	
    public Customer clone() throws CloneNotSupportedException{
        Customer obj=(Customer)super.clone();

        return obj;
    }
	
}
