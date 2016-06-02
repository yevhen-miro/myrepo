package de.hydro.gv.mplus.auth;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import de.hydro.gv.mplus.data.SystemUser;

@Entity
@Table( name = "System_Logins" )
public class Login implements Serializable {

	private static final long serialVersionUID = -847133214593977915L;

	@GeneratedValue( strategy = GenerationType.AUTO)
	@Id
	@Column(name = "USER_ID")
	private String id;

	@OneToOne( mappedBy = "login", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@PrimaryKeyJoinColumn
	private SystemUser user;

	@NotNull
	@Column( name = "PASSWORD" )
	private String password;
	
	@Column( name = "Login_Created" )
	private Date created ;
	
	@Column( name = "Login_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "Login_Updated" )
	private Date updated ;
	
	@Column( name = "Login_UpdatedBy" )
	private String updatedBy ;
	
	
	@Column( name = "Login_CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdyByName ;
	
	@Column( name = "Login_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String updatedByName ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCreatedyByName() {
		return createdyByName;
	}

	public void setCreatedyByName(String createdyByName) {
		this.createdyByName = createdyByName;
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
		Login other = (Login) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [user=" + user + ", password=" + password + "]";
	}

	

}
