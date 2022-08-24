package com.pdm.membershipweb.model.lookup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pdm.membershipweb.model.SystemUser;


@Entity
@Table(name = "Unit")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Unit extends AbstractLookup {

	private static final long serialVersionUID = -5196423044393931904L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
	private List<SystemUser> systemUserList = new ArrayList<SystemUser>();

	@ManyToOne(fetch = FetchType.EAGER)
	private Organization organization;

	
	public List<SystemUser> getSystemUserList() {
		return systemUserList;
	}

	public void setSystemUserList(List<SystemUser> systemUserList) {
		this.systemUserList = systemUserList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
