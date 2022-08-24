package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.AppNotificationForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ReadNotificationPage extends EnhancedDialog {

	private static final long serialVersionUID = 6474274295742327972L;

	private AppNotificationForm appNotificationForm;
	
	private Button okButton;
	
	
	public ReadNotificationPage() {
		super();
		
		Div content = new Div();
		appNotificationForm = new AppNotificationForm();
		appNotificationForm.getTitle().setReadOnly(true);
		appNotificationForm.getBodyContent().setReadOnly(true);
		appNotificationForm.getNotificationStatus().setReadOnly(true);
				
		content.add(appNotificationForm);
		content.addClassName("notification-content");
		content.setSizeFull();
		
		setHeader("Read Notification Form".toUpperCase());
		setModal(true);
		add(content);
		
		okButton = new Button("Ok", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(okButton);
		
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

	public Button getOkButton() {
		return okButton;
	}

	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}
}
