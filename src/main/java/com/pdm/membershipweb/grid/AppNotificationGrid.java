package com.pdm.membershipweb.grid;

import java.text.SimpleDateFormat;

import org.vaadin.klaudeta.PaginatedGrid;

import com.pdm.membershipweb.model.AppNotification;


public class AppNotificationGrid extends PaginatedGrid<AppNotification> {

	private static final long serialVersionUID = -9020065302547203362L;

	
	public AppNotificationGrid(Class<AppNotification> appNotification) {
		super(appNotification);
		configureGrid();
	}
	
	private void configureGrid() {
		addClassName("notification-grid");
		setSizeFull();
		setColumns("id"); 
		
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		
		getColumnByKey("id").setSortable(true).setHeader("NOTIFICATION ID");
		addColumn(notification -> {
			return notification.getNotificationDateTime() == null ? "" : dateTimeFormatter.format(notification.getNotificationDateTime());
		}).setSortable(true).setHeader("NOTIFICATION DATE TIME");
		
		addColumn(notification -> {
			return notification.getTitle();
		}).setSortable(true).setHeader("TITLE");
		
		addColumn(notification -> {
			return notification.getAppNotificationStatus().name();
		}).setSortable(true).setHeader("NOTIFICATION STATUS");
		
		getColumns().forEach(col -> col.setAutoWidth(true));
	}
}
