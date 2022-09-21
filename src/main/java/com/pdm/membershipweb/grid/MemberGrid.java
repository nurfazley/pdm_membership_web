package com.pdm.membershipweb.grid;

import java.text.SimpleDateFormat;

import org.vaadin.klaudeta.PaginatedGrid;

import com.pdm.membership.model.Member;

public class MemberGrid extends PaginatedGrid<Member> {

	private static final long serialVersionUID = 2842101264913214968L;
	
	
	public MemberGrid(Class<Member> member) {
		super(member);
		configureGrid();
	}
	
	private void configureGrid() {
		addClassName("member-grid");
		setSizeFull();
		setColumns("id"); 
		
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		
		getColumnByKey("id").setSortable(true).setHeader("ID");
		
		addColumn(member -> member.getMemberId()).setSortable(true).setHeader("MEMBER ID");
		addColumn(member -> member.getFullName()).setSortable(true).setHeader("MEMBER FULLNAME");
		addColumn(member -> member.getIc()).setSortable(true).setHeader("IC");
		addColumn(member -> member.getPhoneNo()).setSortable(true).setHeader("PHONE NO");
		
		getColumns().forEach(col -> col.setAutoWidth(true));
	}
}
