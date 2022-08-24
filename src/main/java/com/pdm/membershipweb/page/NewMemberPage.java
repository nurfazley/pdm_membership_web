package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.NewsForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NewMemberPage extends EnhancedDialog {

	private static final long serialVersionUID = 5038523907764586735L;

	private Button saveButton;
	
	private Button cancelButton;
	
	
	public NewMemberPage() {
		super();
		
		Div content = new Div();
		//newsForm = new NewsForm();
		
		//content.add(newsForm);
		content.addClassName("member-content");
		content.setSizeFull();
		
		setHeader("New Member Form".toUpperCase());
		setModal(true);
		add(content);
		
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		
		setFooter(buttonLayout);
		setWidth("1220px");
		setHeight("560px");
	}
}
