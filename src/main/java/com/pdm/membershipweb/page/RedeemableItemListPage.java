package com.pdm.membershipweb.page;

import javax.annotation.PostConstruct;

import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Redeemable Item List")
@Route(value = "item", layout = MainLayout.class)
public class RedeemableItemListPage extends VerticalLayout {

	private static final long serialVersionUID = -3492364344653959711L;

	
	@PostConstruct
	public void init() {
		H3 pageTitle = new H3("Redeemable Item List".toUpperCase());
		pageTitle.addClassNames("m-0", "text-m");
		add(pageTitle);
	}
}
