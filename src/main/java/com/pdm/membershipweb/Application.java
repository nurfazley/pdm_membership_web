package com.pdm.membershipweb;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.pdm.membership.model", "com.pdm.membershipweb.model"})
@EnableJpaRepositories(basePackages = {"com.pdm.membership.dao", "com.pdm.membershipweb.dao"})
@ComponentScan(basePackages = {"com.pdm.membership.service", "com.pdm.membership.security", "com.pdm.membership.state", "com.pdm.membershipweb.helper", "com.pdm.membershipweb.service"})
@Theme(value = "pdmmembership", variant = Lumo.DARK)
@PWA(name = "PDM Membership", shortName = "PDM Membership", offlineResources = {})
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

	private static final long serialVersionUID = 3241174452801990096L;

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
