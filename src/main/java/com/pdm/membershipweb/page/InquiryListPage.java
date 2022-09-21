package com.pdm.membershipweb.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membership.model.Inquiry;
import com.pdm.membership.model.lookup.InquiryType;
import com.pdm.membership.model.lookup.NewsType;
import com.pdm.membership.service.InquiryService;
import com.pdm.membershipweb.form.InquiryForm;
import com.pdm.membershipweb.grid.InquiryGrid;
import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Inquiry List")
@Route(value = "inquiry", layout = MainLayout.class)
public class InquiryListPage extends VerticalLayout {

	private static final long serialVersionUID = 6838629859072254514L;

	private Button addInquiryButton;
	
	private Button readInquiryButton;
	
	private Button updateInquiryButton;
	
	private Button deleteInquiryButton;
	
	private InquiryGrid inquiryGrid;
	
	private Inquiry selectedInquiry;
	
	@Autowired
	private InquiryService inquiryService;
	
	
	@PostConstruct
	public void init() {
		H3 pageTitle = new H3("Inquiry List".toUpperCase());
		pageTitle.addClassNames("m-0", "text-m");
		add(pageTitle);
		
		addInquiryButton = new Button("Add Record");
		addInquiryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addInquiryButton.addClickListener(event -> newInquiryPage()); 
		
		readInquiryButton = new Button("Read Record");
		readInquiryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		readInquiryButton.addClickListener(event -> {}); 
		
		updateInquiryButton = new Button("Update Record");
		updateInquiryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		updateInquiryButton.addClickListener(event -> {}); 
		
		deleteInquiryButton = new Button("Delete Record");
		deleteInquiryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteInquiryButton.addClickListener(event -> {});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(addInquiryButton);
		buttonLayout.add(readInquiryButton);
		buttonLayout.add(updateInquiryButton);
		buttonLayout.add(deleteInquiryButton);
		add(buttonLayout);
		
		inquiryGrid = new InquiryGrid(Inquiry.class);
		inquiryGrid.setSelectionMode(SelectionMode.SINGLE);
		inquiryGrid.addItemClickListener(event -> {
			selectedInquiry = event.getItem();
		});
		inquiryGrid.setPageSize(10);
		inquiryGrid.setPaginatorSize(20);
		
		add(inquiryGrid);
		addClassName("list-view");
		setSizeFull();
		
		getInquiryList();
	}
	
	private void getInquiryList() {
		List<Inquiry> inquiryList = inquiryService.findAll();
		inquiryGrid.setItems(inquiryList);
	}
	
	private void newInquiryPage() {
		NewInquiryPage newInquiryPage = new NewInquiryPage();
		
		InquiryForm inquiryForm = newInquiryPage.getInquiryForm();
		inquiryForm.getInquiryType().setItems(getInquiryTypeList());
		inquiryForm.getInquiryType().setValue(InquiryType.FEEDBACK.getTextString());
		
		newInquiryPage.getSaveButton().addClickListener(listener -> {
			
		});
		newInquiryPage.open();
	}
	
	private List<String> getInquiryTypeList() {
		List<String> inquiryTypeList = new ArrayList<>();
		
		for (InquiryType item : InquiryType.values()) {
			inquiryTypeList.add(item.name());
		}
		
		return inquiryTypeList;
	}
}
