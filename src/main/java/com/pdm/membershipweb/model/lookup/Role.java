package com.pdm.membershipweb.model.lookup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.pdm.membershipweb.model.SystemUser;


@Entity
@Table(name = "Role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role extends AbstractLookup {

	private static final long serialVersionUID = -8952484827724777844L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<SystemUser> systemUserList = new ArrayList<SystemUser>();

	public static final String ADMIN = "ADM";
	
	public static final String APPROVER = "APPR";
	
	public static final String PIC = "PIC";
	
	public static final String NORMAL_USER = "NORM";
	
	
	public List<SystemUser> getSystemUserList() {
		return systemUserList;
	}

	public void setSystemUserList(List<SystemUser> systemUserList) {
		this.systemUserList = systemUserList;
	}
}
