package com.pdm.membershipweb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pdm.membershipweb.model.AppNotification;


@Repository("appNotificationDAO")
public interface AppNotificationDAO extends JpaRepository<AppNotification, Long>, JpaSpecificationExecutor<AppNotification>, AppNotificationDAOCustom {}
