package com.pdm.membershipweb.page;

import com.pdm.membershipweb.form.NewsForm;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ReadNewsPage extends EnhancedDialog {

	private static final long serialVersionUID = -8734326766048851846L;

	private NewsForm newsForm;
	
	private Button okButton;
	
	
	public ReadNewsPage() {
		super();
		
		Div content = new Div();
		newsForm = new NewsForm();
		newsForm.getTitle().setReadOnly(true);
		newsForm.getBodyContent().setReadOnly(true);
		newsForm.getNewsType().setReadOnly(true);
		newsForm.getNewsStatus().setReadOnly(true);
						
		content.add(newsForm);
		content.addClassName("news-content");
		content.setSizeFull();
		
		setHeader("Read News Form".toUpperCase());
		setModal(true);
		add(content);
		
		okButton = new Button("Ok", evt -> close());
		HorizontalLayout buttonLayout = new HorizontalLayout(okButton);
		
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

	public Button getOkButton() {
		return okButton;
	}

	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}
}
