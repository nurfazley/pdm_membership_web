package com.pdm.membershipweb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pdm.membershipweb.model.lookup.AppNotificationStatus;


@Entity
@Table(name = "AppNotification")
public class AppNotification implements Serializable {

	private static final long serialVersionUID = -1944441628563830943L;

	@Id
	@SequenceGenerator(name = "notificationSequence", sequenceName = "notification_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificationSequence")
	private Long id;
	
	private Date notificationDateTime;
	
	private String title;
	
	private String bodyContent;
	
	@Enumerated(EnumType.STRING)
	private AppNotificationStatus appNotificationStatus = AppNotificationStatus.DRAFT;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNotificationDateTime() {
		return notificationDateTime;
	}

	public void setNotificationDateTime(Date notificationDateTime) {
		this.notificationDateTime = notificationDateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public AppNotificationStatus getAppNotificationStatus() {
		return appNotificationStatus;
	}

	public void setAppNotificationStatus(AppNotificationStatus appNotificationStatus) {
		this.appNotificationStatus = appNotificationStatus;
	}
}
