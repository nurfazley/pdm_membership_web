package com.pdm.membershipweb.form;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;


public class NewsForm extends FormLayout {

	private static final long serialVersionUID = -5041219542416866617L;

	private DateTimePicker newsDateTime;
	
	private ComboBox<String> newsType;
	
	private ComboBox<String> newsStatus;
	
	private TextField title;
	
	private TextArea bodyContent;
	
	private Upload smallImageFileUpload;
	
	private Upload bigImageFileUpload;
	
	private MemoryBuffer smallFileBuffer;
	
	private MemoryBuffer bigFileBuffer;
	
	
	public NewsForm() {
		super();
		setResponsiveSteps(new ResponsiveStep("30em", 1));
		
		FormLayout formLayout = new FormLayout();
		formLayout.setResponsiveSteps(new ResponsiveStep("30em", 1), new ResponsiveStep("30em", 2));
		
		newsDateTime = new DateTimePicker();
		newsDateTime.setEnabled(false);
		newsDateTime.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(newsDateTime, "News Date Time"));
		
		newsType = new ComboBox<String>();
		newsType.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(newsType, "News Type"));
		
		title = new TextField();
		title.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(title, "News Title")), 2);
		
		bodyContent = new TextArea();
		bodyContent.setHeight("180px");
		bodyContent.setWidthFull();
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(bodyContent, "Content")), 2);
		
		newsStatus = new ComboBox<String>();
		newsStatus.setWidthFull();
		setFormItemLabelStyle(formLayout.addFormItem(newsStatus, "News Status"));
		
		smallFileBuffer = new MemoryBuffer();
		bigFileBuffer = new MemoryBuffer();
		
		smallImageFileUpload = new Upload(smallFileBuffer);
		smallImageFileUpload.setUploadButton(new Button("Upload File"));
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(smallImageFileUpload, "File Image (Small Image) - 120px X 120px")), 2);
		
		bigImageFileUpload = new Upload(bigFileBuffer);
		bigImageFileUpload.setUploadButton(new Button("Upload File"));
		formLayout.add(setFormItemLabelStyle(formLayout.addFormItem(bigImageFileUpload, "File Image (Big Image) - 360px X 140px")), 2);
		
		formLayout.setWidthFull();
		Accordion accordion = new Accordion();
		AccordionPanel newsSection = accordion.add("News Details".toUpperCase(), formLayout);
		newsSection.addThemeVariants(DetailsVariant.REVERSE);
		newsSection.setOpened(true);
		
		add(newsSection);
	}
	
	public DateTimePicker getNewsDateTime() {
		return newsDateTime;
	}

	public void setNewsDateTime(DateTimePicker newsDateTime) {
		this.newsDateTime = newsDateTime;
	}

	public TextField getTitle() {
		return title;
	}

	public void setTitle(TextField title) {
		this.title = title;
	}

	public ComboBox<String> getNewsType() {
		return newsType;
	}

	public void setNewsType(ComboBox<String> newsType) {
		this.newsType = newsType;
	}

	public ComboBox<String> getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(ComboBox<String> newsStatus) {
		this.newsStatus = newsStatus;
	}

	public TextArea getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(TextArea bodyContent) {
		this.bodyContent = bodyContent;
	}

	public Upload getSmallImageFileUpload() {
		return smallImageFileUpload;
	}

	public void setSmallImageFileUpload(Upload smallImageFileUpload) {
		this.smallImageFileUpload = smallImageFileUpload;
	}

	public Upload getBigImageFileUpload() {
		return bigImageFileUpload;
	}

	public void setBigImageFileUpload(Upload bigImageFileUpload) {
		this.bigImageFileUpload = bigImageFileUpload;
	}

	public MemoryBuffer getSmallFileBuffer() {
		return smallFileBuffer;
	}

	public void setSmallFileBuffer(MemoryBuffer smallFileBuffer) {
		this.smallFileBuffer = smallFileBuffer;
	}

	public MemoryBuffer getBigFileBuffer() {
		return bigFileBuffer;
	}

	public void setBigFileBuffer(MemoryBuffer bigFileBuffer) {
		this.bigFileBuffer = bigFileBuffer;
	}

	private FormItem setFormItemLabelStyle(FormItem item) {
		item.getElement().getStyle().set("--vaadin-form-item-label-width", "12em");
		return item;
	}
}
