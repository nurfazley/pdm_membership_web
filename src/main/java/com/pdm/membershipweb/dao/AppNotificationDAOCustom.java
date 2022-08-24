package com.pdm.membershipweb.dao;

import java.util.List;

import com.pdm.membershipweb.model.AppNotification;

public interface AppNotificationDAOCustom {

	public List<AppNotification> findNotificationListOrderByIdDesc();
}
