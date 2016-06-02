package de.hydro.gv.mplus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import de.hydro.gv.mplus.auth.Login;

@Entity
@Table (name = "dbo.System_Users")
@NamedQueries( {
	@NamedQuery( name = "users.find.all", query = "SELECT u FROM SystemUser AS u" ),
	@NamedQuery( name = "users.find.by.id", query = "SELECT u FROM SystemUser AS u WHERE u.id = :id"),
	@NamedQuery( name = "users.find.by.hydroid", query = "SELECT u FROM SystemUser AS u WHERE u.shortName = :hydroid"),
	@NamedQuery( name = "users.find.all.usernames", query = "SELECT u.fullName FROM SystemUser u WHERE u.isEnabled = true ORDER by u.fullName") } )
public class SystemUser implements Serializable{

	private static final long serialVersionUID = -4871817413634229300L;

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "User_ID", columnDefinition="uniqueidentifier")
	private String id ;
	
	@Column( name = "User_Enabled" )
	private Boolean isEnabled ;
	
	@Column( name = "User_Name" )
	private String name ;
	
	@Column(name = "User_ShortName"  , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String shortName ;
	
	@Column( name = "User_FullName" )
    private String fullName ;
	
	@Column( name = "User_EmailAddr" )
	private String email ;
	
	@Column( name = "User_LastActivity" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private Date lastActivity ;
	
	@Column( name = "User_MDXValue"  , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String mdx ;
	
	
	@Column( name = "SalesOffice_Id" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private Long office ;
	
	@Column( name = "User_Created" )
	private Date created ;
	
	@Column( name = "User_CreatedBy" )
	private String createdBy ;
	
	@Column( name = "User_Updated" )
	private Date updated ;
	
	@Column( name = "User_UpdatedBy" )
	private String updatedBy ;
	
	
	@Column( name = "User_CreatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String createdyByName ;
	
	@Column( name = "User_UpdatedByName" , length = 25, insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private String updatedByName ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleMap_Id", foreignKey = @ForeignKey(name = "FK_USER_MAPROLE") )
	private SystemRole map ;

	@Column( name = "DeputyId" )
	private String deputy ;
	
	@OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
	@PrimaryKeyJoinColumn
	private Login login;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}

	public String getMdx() {
		return mdx;
	}

	public void setMdx(String mdx) {
		this.mdx = mdx;
	}

	public Long getOffice() {
		return office;
	}

	public void setOffice(Long office) {
		this.office = office;
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

	public SystemRole getMap() {
		return map;
	}

	public void setMap(SystemRole map) {
		this.map = map;
	}

	public String getDeputy() {
		return deputy;
	}

	public void setDeputy(String deputy) {
		this.deputy = deputy;
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
		SystemUser other = (SystemUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.fullName;
	}


}
