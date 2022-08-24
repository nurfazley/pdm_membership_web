package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.NewsForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class UpdateNewsPage extends EnhancedDialog  {

	private static final long serialVersionUID = 4711445901890856174L;

	private NewsForm newsForm;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	
	public UpdateNewsPage() {
		super();
		
		Div content = new Div();
		newsForm = new NewsForm();
		
		content.add(newsForm);
		content.addClassName("news-content");
		content.setSizeFull();
		
		setHeader("Update News Form".toUpperCase());
		setModal(true);
		add(content);
		
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
		
		setFooter(buttonLayout);
		setWidth("1220px");
		setHeight("560px");
	}

	public NewsForm getNewsForm() {
		return newsForm;
	}

	public void setNewsForm(NewsForm newsForm) {
		this.newsForm = newsForm;
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
