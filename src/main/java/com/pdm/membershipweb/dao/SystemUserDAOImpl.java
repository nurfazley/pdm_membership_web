package com.pdm.membershipweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membershipweb.model.SystemUser;
import com.pdm.membershipweb.model.lookup.Role;


public class SystemUserDAOImpl implements SystemUserDAOCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<SystemUser> getUserListByRole(Role role) {
		TypedQuery<SystemUser> query = entityManager.createQuery("FROM SystemUser user WHERE user.role.code = :code", SystemUser.class);
		query.setParameter("code", role.getCode());
		
		return query.getResultList();
	}

	@Override
	public List<SystemUser> findAllByIdDesc() {
		TypedQuery<SystemUser> query = entityManager.createQuery("FROM SystemUser user ORDER BY user.id DESC", SystemUser.class);
		return query.getResultList();
	}
	
	@Override
	public SystemUser findSystemUserByUserName(String userName) {
		TypedQuery<SystemUser> query = entityManager.createQuery("FROM SystemUser user WHERE user.userName = :userName", SystemUser.class);
		query.setParameter("userName", userName);
		SystemUser user = null;
		
		try {
			user = query.getSingleResult();
		}
		catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemUser> findByRoleIdAndOrganizationName(Long roleId, String organizationName) {
		TypedQuery<SystemUser> query = (TypedQuery<SystemUser>) entityManager.createQuery("FROM SystemUser user where user.role.id = :roleId AND user.organization.textString = :organizationName");
		query.setParameter("roleId", roleId);
		query.setParameter("organizationName", organizationName);
		
		return query.getResultList();
	}
}
