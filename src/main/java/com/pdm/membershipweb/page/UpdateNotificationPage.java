package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.AppNotificationForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class UpdateNotificationPage extends EnhancedDialog {

	private static final long serialVersionUID = -3377339337495659419L;

	private AppNotificationForm appNotificationForm;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	
	public UpdateNotificationPage() {
		super();
		
		Div content = new Div();
		appNotificationForm = new AppNotificationForm();
		
		content.add(appNotificationForm);
		content.addClassName("notification-content");
		content.setSizeFull();
		
		setHeader("Update Notification Form".toUpperCase());
		setModal(true);
		add(content);
		
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		
		setFooter(buttonLayout);
		setWidth("1220px");
		setHeight("560px");
	}

	public AppNotificationForm getAppNotificationForm() {
		return appNotificationForm;
	}

	public void setAppNotificationForm(AppNotificationForm appNotificationForm) {
		this.appNotificationForm = appNotificationForm;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}
}
