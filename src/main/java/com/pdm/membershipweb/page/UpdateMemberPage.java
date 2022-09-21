package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.MemberForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class UpdateMemberPage extends EnhancedDialog {

	
	private static final long serialVersionUID = 5868472928183268694L;

	private MemberForm memberForm;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	
	public UpdateMemberPage() {
		super();
		
		Div content = new Div();
		memberForm = new MemberForm();
		
		content.add(memberForm);
		content.addClassName("member-content");
		content.setSizeFull();
		
		setHeader("Update Member Form".toUpperCase());
		setModal(true);
		add(content);
		
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		
		setFooter(buttonLayout);
		setWidth("1220px");
		setHeight("560px");
	}

	public MemberForm getMemberForm() {
		return memberForm;
	}

	public void setMemberForm(MemberForm memberForm) {
		this.memberForm = memberForm;
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
