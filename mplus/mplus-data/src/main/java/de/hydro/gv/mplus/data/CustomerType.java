package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "dbo.CUSTOMER_TYPES")
@NamedQueries( {
	@NamedQuery( name = "customertypes.find.all", query = "SELECT ct FROM CustomerType AS ct" ) } )
public class CustomerType implements Serializable{

	private static final long serialVersionUID = 2132399877628277724L;

	@Id
	@Column( name = "CustomerType_Id" )
	private Long customertype_id;
	
	@Column( name = "CustomerType_Name" )
	private String customertype_name;
	
	@Column( name = "CustomerType_Enabled" )
	private Boolean customertype_enabled;
	
	@Column( name = "CustomerType_MDXValue" )
	private String customertype_mdx;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CustomerType_Created" )
	private Date customertype_created;
	
	@Column( name = "CustomerType_CreatedBy" )
	private String customertype_created_by;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CustomerType_Updated" )
	private Date customertype_updatede;
	
	@Column( name = "CustomerType_UpdatedBy" )
	private String customertype_updated_by;
	
	@Column( name = "CustomerType_CreatedByName" )
	private String customertype_created_by_name;
	
	@Column( name = "CustomerType_UpdatedByName" )
	private String customertype_updated_by_name;

	public Long getCustomertype_id() {
		return customertype_id;
	}

	public void setCustomertype_id(Long customertype_id) {
		this.customertype_id = customertype_id;
	}

	public String getCustomertype_name() {
		return customertype_name;
	}

	public void setCustomertype_name(String customertype_name) {
		this.customertype_name = customertype_name;
	}

	public Boolean getCustomertype_enabled() {
		return customertype_enabled;
	}

	public void setCustomertype_enabled(Boolean customertype_enabled) {
		this.customertype_enabled = customertype_enabled;
	}

	public String getCustomertype_mdx() {
		return customertype_mdx;
	}

	public void setCustomertype_mdx(String customertype_mdx) {
		this.customertype_mdx = customertype_mdx;
	}

	public Date getCustomertype_created() {
		return customertype_created;
	}

	public void setCustomertype_created(Date customertype_created) {
		this.customertype_created = customertype_created;
	}

	public String getCustomertype_created_by() {
		return customertype_created_by;
	}

	public void setCustomertype_created_by(String customertype_created_by) {
		this.customertype_created_by = customertype_created_by;
	}

	public Date getCustomertype_updatede() {
		return customertype_updatede;
	}

	public void setCustomertype_updatede(Date customertype_updatede) {
		this.customertype_updatede = customertype_updatede;
	}

	public String getCustomertype_updated_by() {
		return customertype_updated_by;
	}

	public void setCustomertype_updated_by(String customertype_updated_by) {
		this.customertype_updated_by = customertype_updated_by;
	}

	public String getCustomertype_created_by_name() {
		return customertype_created_by_name;
	}

	public void setCustomertype_created_by_name(String customertype_created_by_name) {
		this.customertype_created_by_name = customertype_created_by_name;
	}

	public String getCustomertype_updated_by_name() {
		return customertype_updated_by_name;
	}

	public void setCustomertype_updated_by_name(String customertype_updated_by_name) {
		this.customertype_updated_by_name = customertype_updated_by_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customertype_id == null) ? 0 : customertype_id.hashCode());
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
		CustomerType other = (CustomerType) obj;
		if (customertype_id == null) {
			if (other.customertype_id != null)
				return false;
		} else if (!customertype_id.equals(other.customertype_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getCustomertype_name().toString();
	}

	
}
