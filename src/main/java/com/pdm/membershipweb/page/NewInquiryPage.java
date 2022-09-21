package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.InquiryForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NewInquiryPage extends EnhancedDialog {

	private static final long serialVersionUID = -4971459477455288291L;

	private InquiryForm inquiryForm;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	
	public NewInquiryPage() {
		super();
		
		Div content = new Div();
		inquiryForm = new InquiryForm();
		
		content.add(inquiryForm);
		content.addClassName("inquiry-content");
		content.setSizeFull();
		
		setHeader("New Inquiry Form".toUpperCase());
		setModal(true);
		add(content);
		
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		
		setFooter(buttonLayout);
		setWidth("1220px");
		setHeight("560px");
	}

	public InquiryForm getInquiryForm() {
		return inquiryForm;
	}

	public void setInquiryForm(InquiryForm inquiryForm) {
		this.inquiryForm = inquiryForm;
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
