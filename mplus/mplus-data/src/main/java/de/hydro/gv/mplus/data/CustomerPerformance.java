package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "vw_Customer_Statistics", catalog = "MARGIN_PLUS", schema = "DBO")
@NamedQueries({
		@NamedQuery(name = "customers.performance.find.by.customer", query = "SELECT cp FROM CustomerPerformance AS cp WHERE cp.id.customer = :customer"),
		@NamedQuery(name = "customers.performance.find.by.customer.and.month.and.year", query = "SELECT cp FROM CustomerPerformance AS cp WHERE cp.id.customer = :customer AND cp.id.month = :month AND cp.id.year = :year"),
		@NamedQuery(name = "customers.performance.find.by.customer.and.month", query = "SELECT cp.tonnage FROM CustomerPerformance AS cp, Customer c WHERE cp.id.customer = :customer AND cp.id.month = :month")})
public class CustomerPerformance implements Serializable {

	private static final long serialVersionUID = 1231347822318739092L;

	@Id
	@Embedded
	private CustomerPerformanceId id;

	@Column(name = "Tonnage")
	private BigDecimal tonnage;

	public CustomerPerformanceId getId() {
		return id;
	}

	public void setId(CustomerPerformanceId id) {
		this.id = id;
	}

	public BigDecimal getTonnage() {
		return tonnage;
	}

	public void setTonnage(BigDecimal tonnage) {
		this.tonnage = tonnage;
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
		CustomerPerformance other = (CustomerPerformance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerPerformance [id=" + id + ", tonnage=" + tonnage + "]";
	}

}
