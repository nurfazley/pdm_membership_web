package com.pdm.membershipweb.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membershipweb.dao.AppNotificationDAO;
import com.pdm.membershipweb.model.AppNotification;


@Service("appNotificationService")
@Transactional
public class AppNotificationService {
	
	@Autowired
	private AppNotificationDAO appNotificationDAO;


	public List<AppNotification> findNotificationListOrderByIdDesc() {
		return appNotificationDAO.findNotificationListOrderByIdDesc();
	}
	
	public List<AppNotification> findAll() {
		return appNotificationDAO.findAll();
	}
	
	public AppNotification save(AppNotification notification) {
		return appNotificationDAO.save(notification);
	}
}
