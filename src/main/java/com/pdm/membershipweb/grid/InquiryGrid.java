package com.pdm.membershipweb.grid;

import java.text.SimpleDateFormat;

import org.vaadin.klaudeta.PaginatedGrid;

import com.pdm.membership.model.Inquiry;

public class InquiryGrid extends PaginatedGrid<Inquiry> {

	private static
	final long serialVersionUID = 8342669904726999481L;

	
	public InquiryGrid(Class<Inquiry> inquiry) {
		super(inquiry);
		configureGrid();
	}
	
	private void configureGrid() {
		addClassName("inquiry-grid");
		setSizeFull();
		setColumns("id"); 
		
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		
		getColumnByKey("id").setSortable(true).setHeader("ID");
		
		addColumn(inquiry -> {return inquiry.getInquiryNo();}).setSortable(true).setHeader("INQUIRY NO");
		addColumn(inquiry -> {return dateTimeFormatter.format(inquiry.getInquiryDateTime());}).setSortable(true).setHeader("INQUIRY DATE");
		addColumn(inquiry -> {return inquiry.getInquiryType();}).setSortable(true).setHeader("INQUIRY TYPE");
		
		getColumns().forEach(col -> col.setAutoWidth(true));
	}
}
