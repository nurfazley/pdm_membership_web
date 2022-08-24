package com.pdm.membershipweb.grid;

import java.text.SimpleDateFormat;
import org.vaadin.klaudeta.PaginatedGrid;
import com.pdm.membership.model.News;


public class NewsGrid extends PaginatedGrid<News> {

	private static final long serialVersionUID = -5331697242551900659L;

	
	public NewsGrid(Class<News> news) {
		super(news);
		configureGrid();
	}
	
	private void configureGrid() {
		addClassName("news-grid");
		setSizeFull();
		setColumns("id"); 
		
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		
		getColumnByKey("id").setSortable(true).setHeader("NEWS ID");
		addColumn(news -> {
			return news.getNewsDateTime() == null ? "" : dateTimeFormatter.format(news.getNewsDateTime());
		}).setSortable(true).setHeader("NEWS DATE TIME");
		
		addColumn(news -> {
			return news.getTitle();
		}).setSortable(true).setHeader("TITLE");
		
		addColumn(news -> {
			return news.getNewsType();
		}).setSortable(true).setHeader("NEWS TYPE");
		
		addColumn(news -> {
			return news.getNewsStatus();
		}).setSortable(true).setHeader("NEWS STATUS");
		
		getColumns().forEach(col -> col.setAutoWidth(true));
	}
}
