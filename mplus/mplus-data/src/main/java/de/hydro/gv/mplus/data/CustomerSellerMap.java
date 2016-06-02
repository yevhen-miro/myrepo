package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "dbo.Customer_SellerMap")
public class CustomerSellerMap implements Serializable{
	
	private static final long serialVersionUID = 7364286229240142060L;
	
	@Id
	@Column( name = "Map_Id" )
	private Long id ;

	@Column( name = "Customer_Id" )
	private Customer customer;
	
	@Column( name = "Seller_Id" )
	private String seller ;
	
	@Column( name = "Seller_Name" )
	private String sellerName ;
	
	@Column( name = "Seller_FullName" )
	private String sellerFullName ;
	
	@Column( name = "SalesOffice_Id" )
	private Long salesOffice ;
	
	@Column( name = "From_Date_Id" )
	private TimeDate from ;
	
	@Column( name = "To_Date_Id" )
	private TimeDate to ;
	
	@Column( name = "Country_Id" )
	private Long country ;
	
	@Column( name = "CBU_Id" )
	private CBU cbu ;
	
	@Column( name = "Plant_Id" )
	private Plant plant ;
	
	@Column( name = "Map_Created" )
	private Date created ;
	
	@Column( name = "Map_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "Map_Updated" )
	private Date updated ;
	
	@Column( name = "Map_UpdatedBy" )
	private String updatedBy ;
	
	@Column( name = "Map_CreatedByName" )
	private String createdByName ;
	
	@Column( name = "Map_UpdatedByName" )
	private String updatedByName ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerFullName() {
		return sellerFullName;
	}

	public void setSellerFullName(String sellerFullName) {
		this.sellerFullName = sellerFullName;
	}

	public Long getSalesOffice() {
		return salesOffice;
	}

	public void setSalesOffice(Long salesOffice) {
		this.salesOffice = salesOffice;
	}

	public TimeDate getFrom() {
		return from;
	}

	public void setFrom(TimeDate from) {
		this.from = from;
	}

	public TimeDate getTo() {
		return to;
	}

	public void setTo(TimeDate to) {
		this.to = to;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public CBU getCbu() {
		return cbu;
	}

	public void setCbu(CBU cbu) {
		this.cbu = cbu;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CustomerSellerMap other = (CustomerSellerMap) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return sellerFullName;
	}

	

}
