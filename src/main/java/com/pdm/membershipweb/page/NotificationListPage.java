package com.pdm.membershipweb.page;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membership.model.Member;
import com.pdm.membership.service.MemberService;
import com.pdm.membershipweb.form.AppNotificationForm;
import com.pdm.membershipweb.grid.AppNotificationGrid;
import com.pdm.membershipweb.helper.DialogBoxHelper;
import com.pdm.membershipweb.model.AppNotification;
import com.pdm.membershipweb.model.lookup.AppNotificationStatus;
import com.pdm.membershipweb.service.AppNotificationService;
import com.pdm.membershipweb.service.FirebaseMessagingService;
import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.componentfactory.EnhancedDialog;
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


@PageTitle("Notification List")
@Route(value = "notification", layout = MainLayout.class)
public class NotificationListPage extends VerticalLayout {

	private static final long serialVersionUID = 5967314889911066555L;

	private Button addNotificationButton;
	
	private Button readNotificationButton;
	
	private Button updateNotificationButton;
	
	private Button deleteNotificationButton;
	
	private AppNotificationGrid notificationGrid;
	
	private static AppNotification selectedNotification;
	
	@Autowired
	private AppNotificationService appNotificationService;
	
	@Autowired
	private FirebaseMessagingService firebaseMessagingService;
	
	@Autowired
	private MemberService memberService;
	
	
	@PostConstruct
	public void init() {
		H3 pageTitle = new H3("Notification List".toUpperCase());
		pageTitle.addClassNames("m-0", "text-m");
		add(pageTitle);
		
		addNotificationButton = new Button("Add Record");
		addNotificationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addNotificationButton.addClickListener(event -> newNotificationPage()); 
		
		readNotificationButton = new Button("Read Record");
		readNotificationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		readNotificationButton.addClickListener(event -> readNotificationPage()); 
		
		updateNotificationButton = new Button("Update Record");
		updateNotificationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		updateNotificationButton.addClickListener(event -> updateNotificationPage()); 
		
		deleteNotificationButton = new Button("Delete Record");
		deleteNotificationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteNotificationButton.addClickListener(event -> {});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(addNotificationButton);
		buttonLayout.add(readNotificationButton);
		buttonLayout.add(updateNotificationButton);
		buttonLayout.add(deleteNotificationButton);
		add(buttonLayout);
		
		notificationGrid = new AppNotificationGrid(AppNotification.class);
		notificationGrid.setSelectionMode(SelectionMode.SINGLE);
		notificationGrid.addItemClickListener(event -> {
			selectedNotification = event.getItem();
		});
		notificationGrid.setPageSize(10);
		notificationGrid.setPaginatorSize(20);
		
		add(notificationGrid);
		addClassName("list-view");
		setSizeFull();
		
		getNotificationList();
	}
	
	private void getNotificationList() {
		List<AppNotification> notificationList = appNotificationService.findNotificationListOrderByIdDesc();
		notificationGrid.setItems(notificationList);
	}
	
	private boolean validateForm(AppNotificationForm appNotificationForm) {
		List<String> errors = new ArrayList <String>();
		
		if (appNotificationForm.getTitle().getValue() == null || appNotificationForm.getTitle().getValue().equals("")) {
			errors.add("Title cannot be emptied.");
		}
		
		if (appNotificationForm.getBodyContent().getValue() == null || appNotificationForm.getBodyContent().getValue().equals("")) {
			errors.add("Body content cannot be emptied.");
		}
		
		if (appNotificationForm.getNotificationStatus().getValue() == null) {
			errors.add("Notification status cannot be emptied.");	
		}
		
		if (errors != null && !errors.isEmpty()) {
			String errString = new String();
			errString += "<ul>";
			
			for (String err : errors) {
				errString += "<li>" + err + "</li>";
			}
			
			errString += "</ul>";
			
			EnhancedDialog simpleDialog = DialogBoxHelper.createSimpleHtmlDialogBox("ERRORS", errString);
			simpleDialog.setWidth("480px");
			simpleDialog.setModal(true);
			simpleDialog.open();
			
			return false;
		}
		
		return true;
	}
	
	private void newNotificationPage() {
		NewNotificationPage newNotificationPage = new NewNotificationPage();
		AppNotificationForm appNotificationForm = newNotificationPage.getAppNotificationForm();
		
		List<String> notificationStatusList = getNotificationStatusList();
		appNotificationForm.getNotificationStatus().setItems(notificationStatusList);
		appNotificationForm.getNotificationStatus().setValue(AppNotificationStatus.DRAFT.name());
		
		newNotificationPage.getSaveButton().addClickListener(listener -> {
			if (!validateForm(appNotificationForm)) {
				return;
			}
			
			Date now = new Date();
			
			selectedNotification = new AppNotification();
			selectedNotification.setNotificationDateTime(now);
			selectedNotification.setTitle(appNotificationForm.getTitle().getValue());
			selectedNotification.setBodyContent(appNotificationForm.getBodyContent().getValue());
			selectedNotification.setAppNotificationStatus(AppNotificationStatus.valueOf(appNotificationForm.getNotificationStatus().getValue()));
			
			selectedNotification = appNotificationService.save(selectedNotification);
			setNotification("Notification is saving into the system...");
			
			if (appNotificationForm.getNotificationStatus().getValue().equals(AppNotificationStatus.PENDING.name())) {
				List<Member> memberList = memberService.findAll();
				List<String> tokenList = new ArrayList<>();
				
				if (memberList != null && !memberList.isEmpty()) {
					for (Member member : memberList) {
						if (member.getFpnToken() != null && !member.getFpnToken().equals("")) {
							tokenList.add(member.getFpnToken());
						}
					}
					
					sendNotificationToFirebase(selectedNotification, tokenList);
				}
			}
			
			newNotificationPage.close();
			
			getNotificationList();
			notificationGrid.getDataProvider().refreshItem(selectedNotification);
			selectedNotification = new AppNotification();
		});
		newNotificationPage.open();
	}
	
	private void readNotificationPage() {
		if (selectedNotification == null ) {
			setNotification("You need to select a notification...");
			return;
		}
		
		ReadNotificationPage readNotificationPage = new ReadNotificationPage();
		readNotificationPage.open();
		
		AppNotificationForm appNotificationForm = readNotificationPage.getAppNotificationForm();
		mapNotificationToForm(selectedNotification, appNotificationForm);
	}
	
	private void updateNotificationPage() {
		if (selectedNotification == null ) {
			setNotification("You need to select a notification...");
			return;
		}
		
		UpdateNotificationPage updateNotificationPage = new UpdateNotificationPage();
		updateNotificationPage.open();
		
		AppNotificationForm appNotificationForm = updateNotificationPage.getAppNotificationForm();
		mapNotificationToForm(selectedNotification, appNotificationForm);
		
		updateNotificationPage.getSaveButton().addClickListener(listener -> {
			if (!validateForm(appNotificationForm)) {
				return;
			}
			
			mapFormToNotification(appNotificationForm, selectedNotification);
			appNotificationService.save(selectedNotification);
			
			setNotification("Notifiction is being updated into the system...");
			updateNotificationPage.close();
			
			if (appNotificationForm.getNotificationStatus().getValue().equals(AppNotificationStatus.PENDING.name())) {
				List<Member> memberList = memberService.findAll();
				List<String> tokenList = new ArrayList<>();
				
				if (memberList != null && !memberList.isEmpty()) {
					for (Member member : memberList) {
						if (member.getFpnToken() != null && !member.getFpnToken().equals("")) {
							tokenList.add(member.getFpnToken());
						}
					}
					
					sendNotificationToFirebase(selectedNotification, tokenList);
				}
			}
			
			getNotificationStatusList();
			notificationGrid.getDataProvider().refreshItem(selectedNotification);
			selectedNotification = new AppNotification();
		});
	}
	
	private void mapNotificationToForm(AppNotification appNotification, AppNotificationForm appNotificationForm) {
		appNotificationForm.getNotificationDateTime().setValue(appNotification.getNotificationDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		appNotificationForm.getTitle().setValue(appNotification.getTitle());
		appNotificationForm.getBodyContent().setValue(appNotification.getBodyContent());
		appNotificationForm.getNotificationStatus().setItems(getNotificationStatusList());
		appNotificationForm.getNotificationStatus().setValue(appNotification.getAppNotificationStatus().name());
	}
	
	private void mapFormToNotification(AppNotificationForm appNotificationForm, AppNotification appNotification) {
		appNotification.setNotificationDateTime(Date.from(appNotificationForm.getNotificationDateTime().getValue().atZone(ZoneId.systemDefault()).toInstant()));
		appNotification.setTitle(appNotificationForm.getTitle().getValue());
		appNotification.setBodyContent(appNotificationForm.getBodyContent().getValue());
		appNotification.setAppNotificationStatus(AppNotificationStatus.valueOf(appNotificationForm.getNotificationStatus().getValue()));
	}
	
	private List<String> getNotificationStatusList() {
		List<String> notificationStatusList = new ArrayList<>();
		
		for (AppNotificationStatus status : AppNotificationStatus.values()) {
			notificationStatusList.add(status.name());
		}
		
		return notificationStatusList;
	}
	
	private void sendNotificationToFirebase(AppNotification appNotification, List<String> fpnTokenList) {
		System.out.println("Sending multicast notification...");
		firebaseMessagingService.sendMulticastNotification(appNotification, fpnTokenList);
		
		appNotification.setAppNotificationStatus(AppNotificationStatus.SENT);
		appNotificationService.save(appNotification);
	}
	
	private void setNotification(String content ) {
		Notification.show(content, 2000, Position.BOTTOM_CENTER);
	}
}
