package com.pdm.membershipweb.form;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class AppNotificationForm extends FormLayout {

	private static final long serialVersionUID = -2606301959139861574L;

	private DateTimePicker notificationDateTime;
	
	private TextField title;
	
	private TextArea bodyContent;
	
	private ComboBox<String> notificationStatus;
		
	
	public AppNotificationForm() {
		super();
		setResponsiveSteps(new ResponsiveStep("30em", 1));
		
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(new ResponsiveStep("30em", 1), new ResponsiveStep("30em", 2));
		
		notificationDateTime = new DateTimePicker();
		notificationDateTime.setEnabled(false);
		notificationDateTime.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(notificationDateTime, "Notification Date Time"));
		
		title = new TextField();
		title.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(title, "News Title")), 2);
		
		bodyContent = new TextArea();
		bodyContent.setHeight("180px");
		bodyContent.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(bodyContent, "Content")), 2);
		
		notificationStatus = new ComboBox<String>();
		notificationStatus.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(notificationStatus, "Notification Status"));
			
		formLayout.setWidthFull();
		Accordion accordion = new Accordion();
		AccordionPanel newsSection = accordion.add("Notification Details".toUpperCase(), formLayout);
		newsSection.addThemeVariants(DetailsVariant.REVERSE);
		newsSection.setOpened(true);
		
		add(newsSection);
	}
	
	public DateTimePicker getNotificationDateTime() {
		return notificationDateTime;
	}

	public void setNotificationDateTime(DateTimePicker notificationDateTime) {
		this.notificationDateTime = notificationDateTime;
	}

	public TextField getTitle() {
		return title;
	}

	public void setTitle(TextField title) {
		this.title = title;
	}

	public TextArea getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(TextArea bodyContent) {
		this.bodyContent = bodyContent;
	}

	public ComboBox<String> getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(ComboBox<String> notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	private FormItem setFormItemLabelStyle(FormItem item) {
		item.getElement().getStyle().set("--vaadin-form-item-label-width", "12em");
		return item;
	}
}
