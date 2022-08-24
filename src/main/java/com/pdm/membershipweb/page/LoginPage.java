package com.pdm.membershipweb.page;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membershipweb.model.SystemUser;
import com.pdm.membershipweb.service.SecurityService;
import com.pdm.membershipweb.service.SessionService;
import com.pdm.membershipweb.view.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.VaadinSession;


@PageTitle("Login Page")
@Route(value = "/login")
@RouteAlias(value = "", layout = MainLayout.class)
@CssImport(value = "./styles/my-lumo-theme.css", themeFor = "vaadin-login-overlay-wrapper")
public class LoginPage extends VerticalLayout {

	private static final long serialVersionUID = -7005568535339782667L;

	private LoginOverlay component;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SessionService sessionService;
	
	
	@PostConstruct
	public void init() {
		System.out.println("Starting login...");
		
		VaadinSession.getCurrent().getSession().setMaxInactiveInterval(1800);
		LoginI18n i18n = LoginI18n.createDefault();
		
		component = new LoginOverlay();
		component.setTitle("PDM Sdn Bhd");
		component.setDescription("PDM Membership Web Application");
		component.setI18n(i18n);
		component.setOpened(true);
		
		component.addLoginListener(listener -> {
			String userName = listener.getUsername();
			String password = listener.getPassword();
			
			if (authenticate(userName, password)) {
				navigate("/pm/home");
			}
			else {
				Notification.show("Your username or password are not matched...", 2000, Position.BOTTOM_CENTER);
			}
		});
		component.addForgotPasswordListener(listener -> {
			
		});
		
		add(component);
	}
	
	private boolean authenticate(String userName, String password) {
		boolean authenticated = false;
		SystemUser user = securityService.authenticate(userName, password);
		
		if (user != null) {
			authenticated = true;
			sessionService.setSystemUser(user);
		}
		
		component.setEnabled(true); // Set enable the login operation again
		return authenticated;
	}
	
	private void navigate(String navigationPath) {
		UI.getCurrent().getPage().setLocation(navigationPath);
	}
}
