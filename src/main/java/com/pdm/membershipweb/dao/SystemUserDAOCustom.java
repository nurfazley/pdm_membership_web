package com.pdm.membershipweb.dao;

import java.util.List;

import com.pdm.membershipweb.model.SystemUser;
import com.pdm.membershipweb.model.lookup.Role;


public interface SystemUserDAOCustom {

	public List<SystemUser> getUserListByRole(Role role);
	
	public List<SystemUser> findAllByIdDesc();
	
	public SystemUser findSystemUserByUserName(String userName);
	
	public List<SystemUser> findByRoleIdAndOrganizationName(Long roleId, String organizationName);
}
