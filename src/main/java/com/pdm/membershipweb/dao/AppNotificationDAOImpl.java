package com.pdm.membershipweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pdm.membershipweb.model.AppNotification;

public class AppNotificationDAOImpl implements AppNotificationDAOCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<AppNotification> findNotificationListOrderByIdDesc() {
		TypedQuery<AppNotification> query = entityManager.createQuery("FROM AppNotification n ORDER BY n.id DESC", AppNotification.class);
		return query.getResultList();
	}
}
