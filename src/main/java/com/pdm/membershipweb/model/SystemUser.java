package com.pdm.membershipweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pdm.membershipweb.model.lookup.Organization;
import com.pdm.membershipweb.model.lookup.Role;
import com.pdm.membershipweb.model.lookup.Unit;


@Entity
@Table(name = "SystemUser")
public class SystemUser extends AbstractUser {

	private static final long serialVersionUID = 4776513291465253535L;

	@Column(name = "staffNo")
	private String staffNo;
	
	@Column(name = "ic")
	private String ic;

	@Column(name = "designation")
	private String designation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Unit unit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Organization organization;
	
	
	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
