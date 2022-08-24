package com.pdm.membershipweb.helper;

import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public final class DialogBoxHelper {

	
	public DialogBoxHelper() {
		super();
	}
	
	public static EnhancedDialog createSimpleHtmlDialogBox(String title, String errorListHtml) {
		EnhancedDialog simpleDialog = new EnhancedDialog();
		
		Html html = new Html(errorListHtml);
		simpleDialog.setHeader(title);
		simpleDialog.add(html);
		simpleDialog.setModal(true);
		
		Button okButton = new Button("Ok");
		okButton.addClickListener(listener -> simpleDialog.close());
		HorizontalLayout buttonLayout = new HorizontalLayout(okButton);
		simpleDialog.setFooter(buttonLayout);
		
		return simpleDialog;
	}
}
