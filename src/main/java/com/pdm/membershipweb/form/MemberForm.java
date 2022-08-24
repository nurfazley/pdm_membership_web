package com.pdm.membershipweb.form;

import com.vaadin.flow.component.formlayout.FormLayout;

public class MemberForm extends FormLayout {

	private static final long serialVersionUID = -6295644438667759207L;

	
	public MemberForm() {
		super();
		setResponsiveSteps(new ResponsiveStep("30em", 1));
		
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(new ResponsiveStep("30em", 1), new ResponsiveStep("30em", 2));
	}
}
