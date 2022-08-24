package com.pdm.membershipweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractUser implements Serializable {

	private static final long serialVersionUID = -258028452175217601L;

	@Id
	@SequenceGenerator(name = "userSequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
	private Long id;
	
	@Column(name = "fullName")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contactNo")
	private String contactNo;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "lastLoginDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date lastLoginDateTime;
	
	@Column(name = "lastLogoutDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date lastLogoutDateTime;
	
	@Column(name = "recordCreatedDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date recordCreatedDateTime;
	
	@Column(name = "recordLastUpdatedDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss a")
	private Date recordLastUpdatedDateTime;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(Date lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public Date getLastLogoutDateTime() {
		return lastLogoutDateTime;
	}

	public void setLastLogoutDateTime(Date lastLogoutDateTime) {
		this.lastLogoutDateTime = lastLogoutDateTime;
	}

	public Date getRecordCreatedDateTime() {
		return recordCreatedDateTime;
	}

	public void setRecordCreatedDateTime(Date recordCreatedDateTime) {
		this.recordCreatedDateTime = recordCreatedDateTime;
	}

	public Date getRecordLastUpdatedDateTime() {
		return recordLastUpdatedDateTime;
	}

	public void setRecordLastUpdatedDateTime(Date recordLastUpdatedDateTime) {
		this.recordLastUpdatedDateTime = recordLastUpdatedDateTime;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return getId().hashCode();
		}
		
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
	
		if (obj == null) {
			return false;
		}
	
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		AbstractUser other = (AbstractUser) obj;
		
		if (getId() == null || other.getId() == null) {
			return false;
		}
		
		return getId().equals(other.getId());
	}
}
