package com.pdm.membershipweb.form;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


public class InquiryForm extends FormLayout {

	private static final long serialVersionUID = -7659972585576756621L;

	private DateTimePicker inquiryDateTime;

	private TextField inquiryNo;
	
	private ComboBox<String> inquiryType;
	
	private TextArea inquiryText;
	
	
	public InquiryForm() {
		super();
		setResponsiveSteps(new ResponsiveStep("30em", 1));
	
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(new ResponsiveStep("30em", 1), new ResponsiveStep("30em", 2));
		
		inquiryDateTime = new DateTimePicker();
		inquiryDateTime.setEnabled(false);
		inquiryDateTime.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(inquiryDateTime, "Inquiry Date Time"));
		
		inquiryNo = new TextField();
		inquiryNo.setEnabled(false);
		inquiryNo.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(inquiryNo, "Inquiry No"));
		
		inquiryType = new ComboBox<String>();
		inquiryType.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(inquiryType, "Inquiry Type"));
		
		inquiryText = new TextArea();
		inquiryText.setHeight("180px");
		inquiryText.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(inquiryText, "Inquiry Text")), 2);
		
		formLayout.setWidthFull();
		Accordion accordion = new Accordion();
		AccordionPanel inquirySection = accordion.add("Inquiry Details".toUpperCase(), formLayout);
		inquirySection.addThemeVariants(DetailsVariant.REVERSE);
		inquirySection.setOpened(true);
		
		add(inquirySection);
	}
	
	public DateTimePicker getInquiryDateTime() {
		return inquiryDateTime;
	}

	public void setInquiryDateTime(DateTimePicker inquiryDateTime) {
		this.inquiryDateTime = inquiryDateTime;
	}

	public TextField getInquiryNo() {
		return inquiryNo;
	}

	public void setInquiryNo(TextField inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public ComboBox<String> getInquiryType() {
		return inquiryType;
	}

	public void setInquiryType(ComboBox<String> inquiryType) {
		this.inquiryType = inquiryType;
	}

	public TextArea getInquiryText() {
		return inquiryText;
	}

	public void setInquiryText(TextArea inquiryText) {
		this.inquiryText = inquiryText;
	}

	private FormItem setFormItemLabelStyle(FormItem item) {
		item.getElement().getStyle().set("--vaadin-form-item-label-width", "12em");
		return item;
	}
}
