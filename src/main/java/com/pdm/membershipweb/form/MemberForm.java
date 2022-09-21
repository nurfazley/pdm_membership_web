package com.pdm.membershipweb.form;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

public class MemberForm extends FormLayout {

	private static final long serialVersionUID = -6295644438667759207L;

	private TextField memberId;
	
	private TextField fullName;
	
	private TextField ic;
	
	private TextField staffId;
	
	private TextField phoneNo;
	
	private EmailField email;
	
	private TextArea address;
	
	private NumberField postCode;
	
	private ComboBox<String> state;
	
	private ComboBox<String> country;
	
	private Checkbox isMember;
	
	private Checkbox isStaff;
	
	private Checkbox isMobileAppActivated;
	
	private Checkbox isActive;
	
	
	public MemberForm() {
		super();
		setResponsiveSteps(new ResponsiveStep("30em", 1));
		
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(new ResponsiveStep("30em", 1), new ResponsiveStep("30em", 2));
		
		memberId = new TextField();
		memberId.setWidthFull();
		memberId.setEnabled(false);
		setFormItemLabelStyle(formLayout.addFormItem(memberId, "Member Id"));
		
		fullName = new TextField();
		fullName.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(fullName, "Fullname")), 2);
		
		ic = new TextField();
		ic.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(ic, "Ic"));
		
		staffId = new TextField();
		staffId.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(staffId, "Staff Id"));
		
		phoneNo = new TextField();
		phoneNo.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(phoneNo, "Phone No"));
		
		email = new EmailField();
		email.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(email, "Email"));
		
		address = new TextArea();
		address.setWidthFull();
		address.setHeight("180px");
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(address, "Address")), 2);
		
		postCode = new NumberField();
		postCode.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(postCode, "Postcode"));
		
		state = new ComboBox<String>();
		state.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(state, "State"));
		
		country = new ComboBox<String>();
		country.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(country, "Country"));
		
		isMember = new Checkbox();
		isMember.setValue(true);
		isMember.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(isMember, "Is A Member?"));
		
		isStaff = new Checkbox();
		isStaff.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(isStaff, "Is A Staff?"));
		
		isMobileAppActivated = new Checkbox();
		isMobileAppActivated.setValue(true);
		isMobileAppActivated.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(isMobileAppActivated, "Is Mobile Activated?"));
		
		isActive = new Checkbox();
		isActive.setValue(true);
		isActive.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(isActive, "Is Active?"));
				
		formLayout.setWidthFull();
		Accordion accordion = new Accordion();
		AccordionPanel newsSection = accordion.add("Member Details".toUpperCase(), formLayout);
		newsSection.addThemeVariants(DetailsVariant.REVERSE);
		newsSection.setOpened(true);
		
		add(newsSection);
	}
	
	public TextField getMemberId() {
		return memberId;
	}

	public void setMemberId(TextField memberId) {
		this.memberId = memberId;
	}

	public TextField getFullName() {
		return fullName;
	}

	public void setFullName(TextField fullName) {
		this.fullName = fullName;
	}

	public TextField getIc() {
		return ic;
	}

	public void setIc(TextField ic) {
		this.ic = ic;
	}

	public TextField getStaffId() {
		return staffId;
	}

	public void setStaffId(TextField staffId) {
		this.staffId = staffId;
	}

	public TextField getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(TextField phoneNo) {
		this.phoneNo = phoneNo;
	}

	public EmailField getEmail() {
		return email;
	}

	public void setEmail(EmailField email) {
		this.email = email;
	}

	public TextArea getAddress() {
		return address;
	}

	public void setAddress(TextArea address) {
		this.address = address;
	}

	public NumberField getPostCode() {
		return postCode;
	}

	public void setPostCode(NumberField postCode) {
		this.postCode = postCode;
	}

	public ComboBox<String> getState() {
		return state;
	}

	public void setState(ComboBox<String> state) {
		this.state = state;
	}

	public ComboBox<String> getCountry() {
		return country;
	}

	public void setCountry(ComboBox<String> country) {
		this.country = country;
	}

	public Checkbox getIsMember() {
		return isMember;
	}

	public void setIsMember(Checkbox isMember) {
		this.isMember = isMember;
	}

	public Checkbox getIsStaff() {
		return isStaff;
	}

	public void setIsStaff(Checkbox isStaff) {
		this.isStaff = isStaff;
	}

	public Checkbox getIsMobileAppActivated() {
		return isMobileAppActivated;
	}

	public void setIsMobileAppActivated(Checkbox isMobileAppActivated) {
		this.isMobileAppActivated = isMobileAppActivated;
	}

	public Checkbox getIsActive() {
		return isActive;
	}

	public void setIsActive(Checkbox isActive) {
		this.isActive = isActive;
	}

	private FormItem setFormItemLabelStyle(FormItem item) {
		item.getElement().getStyle().set("--vaadin-form-item-label-width", "12em");
		return item;
	}
}
