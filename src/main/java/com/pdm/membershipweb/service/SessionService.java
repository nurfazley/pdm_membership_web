package com.pdm.membershipweb.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

import com.pdm.membershipweb.model.SystemUser;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;


@Component
@VaadinSessionScope
@Transactional
public class SessionService {
	
    private SystemUser systemUser;
    
    
	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
}
