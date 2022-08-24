package com.pdm.membershipweb.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.security.MhPasswordEncoder;
import com.pdm.membershipweb.dao.SystemUserDAO;
import com.pdm.membershipweb.model.SystemUser;


@Service("securityService")
@Transactional
public class SecurityService {
	
	@Autowired
	private SystemUserDAO systemUserDAO;
	
	@Autowired
	private MhPasswordEncoder passwordEncoder;
	
	
	public String encryptPassword(String password, String salt) {
		return MhPasswordEncoder.encrypt(password, salt);
	}
	
	public SystemUser authenticate(String userName, String password) {
		boolean authenticated = false;
		SystemUser currentUser = systemUserDAO.findSystemUserByUserName(userName);
		
		if (currentUser != null) {
			authenticated = passwordEncoder.isPasswordMatches(password, currentUser.getPassword(), currentUser.getSalt());
			
			// If password is matched, update system user profile
			if (authenticated) {
				currentUser.setLastLoginDateTime(new Date());
				systemUserDAO.save(currentUser);
			}
			// If password is not matched, set system user to null
			else {
				currentUser = null;
			}
		}
		
		return currentUser;
	}
	
	public void logout(SystemUser user) {
		SystemUser currentUser = systemUserDAO.findById(user.getId()).get();
		
		if (currentUser != null) {
			currentUser.setLastLogoutDateTime(new Date());
			systemUserDAO.save(currentUser);
		}
	}
}
