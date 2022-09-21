package com.pdm.membershipweb.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membership.model.Member;
import com.pdm.membership.service.MemberService;
import com.pdm.membershipweb.form.MemberForm;
import com.pdm.membershipweb.grid.MemberGrid;
import com.pdm.membershipweb.model.lookup.Country;
import com.pdm.membershipweb.model.lookup.State;
import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
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
	
	private MemberGrid memberGrid;
	
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
		updateMemberButton.addClickListener(event -> updateMemberPage()); 
		
		deleteMemberButton = new Button("Delete Record");
		deleteMemberButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteMemberButton.addClickListener(event -> {});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(addMemberButton);
		buttonLayout.add(readMemberButton);
		buttonLayout.add(updateMemberButton);
		buttonLayout.add(deleteMemberButton);
		add(buttonLayout);
		
		memberGrid = new MemberGrid(Member.class);
		memberGrid.setSelectionMode(SelectionMode.SINGLE);
		memberGrid.addItemClickListener(event -> {
			selectedMember = event.getItem();
		});
		memberGrid.setPageSize(10);
		memberGrid.setPaginatorSize(20);
		
		add(memberGrid);
		addClassName("list-view");
		setSizeFull();
		
		getNewsList();
	}
	
	private void getNewsList() {
		List<Member> memberList = memberService.findAll();
		memberGrid.setItems(memberList);
	}
	
	private void newMemberPage() {
		NewMemberPage newMemberPage = new NewMemberPage();
		MemberForm memberForm = newMemberPage.getMemberForm();
		
		memberForm.getState().setItems(getStateList());
		memberForm.getState().setValue(State.PAHANG.getTextString());
		memberForm.getCountry().setItems(getCountryList());
		memberForm.getCountry().setValue(Country.MALAYSIA.getTextString());
	
		newMemberPage.getSaveButton().addClickListener(listener -> {
			
		});
		
		newMemberPage.open();
	}
	
	private void updateMemberPage() {
		if (selectedMember == null) {
			setNotification("You need to select a member...");
			return;
		}
		
		UpdateMemberPage updateMemberPage = new UpdateMemberPage();
		updateMemberPage.open();
		
		MemberForm memberForm = updateMemberPage.getMemberForm();
		mapMemberToForm(selectedMember, memberForm);
	}
	
	private void mapMemberToForm(Member member, MemberForm memberForm) {
		memberForm.getMemberId().setValue(member.getMemberId());
		memberForm.getFullName().setValue(member.getFullName());
		memberForm.getIc().setValue(member.getIc());
		memberForm.getStaffId().setValue(member.getStaffId());
		memberForm.getPhoneNo().setValue(member.getPhoneNo());
		memberForm.getEmail().setValue(member.getEmail());
		memberForm.getAddress().setValue(member.getAddress());
		memberForm.getPostCode().setValue((double) member.getPostCode());
		
		memberForm.getState().setItems(getStateList());
		memberForm.getState().setValue(member.getState());
		
		memberForm.getCountry().setItems(getCountryList());
		memberForm.getCountry().setValue(member.getCountry());
		
		memberForm.getIsMember().setValue(member.isMember());
		memberForm.getIsActive().setValue(member.isActive());
		memberForm.getIsMobileAppActivated().setValue(member.isMobileAppActivated());
		memberForm.getIsStaff().setValue(member.isStaff());
	}
	
	private void mapFormToMember(MemberForm memberForm, Member member) {
		member.setMemberId(memberForm.getMemberId().getValue());
		member.setFullName(memberForm.getFullName().getValue());
		member.setIc(memberForm.getIc().getValue());
		member.setStaffId(memberForm.getStaffId().getValue());
		member.setPhoneNo(memberForm.getPhoneNo().getValue());
		member.setEmail(memberForm.getEmail().getValue());
		member.setAddress(memberForm.getAddress().getValue());
		member.setPostCode(memberForm.getPostCode().getValue().intValue());
		member.setState(memberForm.getState().getValue());
		member.setCountry(memberForm.getCountry().getValue());
		
		member.setMember(memberForm.getIsMember().getValue());
		member.setActive(memberForm.getIsActive().getValue());
	}
	
	private List<String> getStateList() {
		List<String> stateList = new ArrayList<>();
		Arrays.asList(State.values()).forEach(state -> stateList.add(state.getTextString()));
		
		return stateList;
	}
	
	private List<String> getCountryList() {
		List<String> countryList = new ArrayList<>();
		Arrays.asList(Country.values()).forEach(country -> countryList.add(country.getTextString()));
		
		return countryList;
	}
	
	private void setNotification(String content ) {
		Notification.show(content, 2000, Position.BOTTOM_CENTER);
	}
}
