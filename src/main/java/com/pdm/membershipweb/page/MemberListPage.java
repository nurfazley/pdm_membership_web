package com.pdm.membershipweb.page;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membership.model.Member;
import com.pdm.membership.service.MemberService;
import com.pdm.membershipweb.grid.MembersGrid;
import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Member List")
@Route(value = "member", layout = MainLayout.class)
public class MemberListPage extends VerticalLayout {

	private static final long serialVersionUID = -543910697492036073L;
	
	private Button addMemberButton;
	
	private Button readMemberButton;
	
	private Button updateMemberButton;
	
	private Button deleteMemberButton;
	
	private MembersGrid membersGrid;
	
	private Member selectedMember;
	
	@Autowired
	private MemberService memberService;

	
	@PostConstruct
	public void init() {
		H3 pageTitle = new H3("Member List".toUpperCase());
		pageTitle.addClassNames("m-0", "text-m");
		add(pageTitle);
		
		addMemberButton = new Button("Add Record");
		addMemberButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addMemberButton.addClickListener(event -> newMemberPage()); 
		
		readMemberButton = new Button("Read Record");
		readMemberButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		readMemberButton.addClickListener(event -> {}); 
		
		updateMemberButton = new Button("Update Record");
		updateMemberButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		updateMemberButton.addClickListener(event -> {}); 
		
		deleteMemberButton = new Button("Delete Record");
		deleteMemberButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteMemberButton.addClickListener(event -> {});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(addMemberButton);
		buttonLayout.add(readMemberButton);
		buttonLayout.add(updateMemberButton);
		buttonLayout.add(deleteMemberButton);
		add(buttonLayout);
		
		membersGrid = new MembersGrid(Member.class);
		membersGrid.setSelectionMode(SelectionMode.SINGLE);
		membersGrid.addItemClickListener(event -> {
			selectedMember = event.getItem();
		});
		membersGrid.setPageSize(10);
		membersGrid.setPaginatorSize(20);
		
		add(membersGrid);
		addClassName("list-view");
		setSizeFull();
		
		getNewsList();
	}
	
	private void getNewsList() {
		List<Member> memberList = memberService.findAll();
		membersGrid.setItems(memberList);
	}
	
	private void newMemberPage() {
		NewMemberPage newMemberPage = new NewMemberPage();
	
		newMemberPage.open();
	}
}
