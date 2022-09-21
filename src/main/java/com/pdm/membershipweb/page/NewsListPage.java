package com.pdm.membershipweb.page;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pdm.membership.model.Image;
import com.pdm.membership.model.News;
import com.pdm.membership.model.lookup.NewsStatus;
import com.pdm.membership.model.lookup.NewsType;
import com.pdm.membership.service.ImageService;
import com.pdm.membership.service.NewsService;
import com.pdm.membershipweb.form.NewsForm;
import com.pdm.membershipweb.grid.NewsGrid;
import com.pdm.membershipweb.helper.DialogBoxHelper;
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


@PageTitle("News List")
@Route(value = "news", layout = MainLayout.class)
public class NewsListPage extends VerticalLayout {

	private static final long serialVersionUID = -783266535187138056L;
	
	private Button addNewsButton;
	
	private Button readNewsButton;
	
	private Button updateNewsButton;
	
	private Button deleteNewsButton;
	
	private NewsGrid newsGrid;
	
	private static News selectedNews;
	
	private String smallFileName;
	
	private String smallFileType;
	
	private String bigFileName;
	
	private String bigFileType;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ImageService imageService;
	
	
	@PostConstruct
	public void init() {
		H3 pageTitle = new H3("News List".toUpperCase());
		pageTitle.addClassNames("m-0", "text-m");
		add(pageTitle);
		
		addNewsButton = new Button("Add Record");
		addNewsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addNewsButton.addClickListener(event -> newNewsPage()); 
		
		readNewsButton = new Button("Read Record");
		readNewsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		readNewsButton.addClickListener(event -> readNewsPage()); 
		
		updateNewsButton = new Button("Update Record");
		updateNewsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		updateNewsButton.addClickListener(event -> updateNewsPage()); 
		
		deleteNewsButton = new Button("Delete Record");
		deleteNewsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteNewsButton.addClickListener(event -> {});
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.add(addNewsButton);
		buttonLayout.add(readNewsButton);
		buttonLayout.add(updateNewsButton);
		buttonLayout.add(deleteNewsButton);
		add(buttonLayout);
		
		newsGrid = new NewsGrid(News.class);
		newsGrid.setSelectionMode(SelectionMode.SINGLE);
		newsGrid.addItemClickListener(event -> {
			selectedNews = event.getItem();
		});
		newsGrid.setPageSize(10);
		newsGrid.setPaginatorSize(20);
		
		add(newsGrid);
		addClassName("list-view");
		setSizeFull();
		
		getNewsList();
	}

	private void getNewsList() {
		List<News> newsList = newsService.findAllOrderByIdDesc();
		newsGrid.setItems(newsList);
	}
	
	private boolean validateForm(NewsForm newsForm) {
		List<String> errors = new ArrayList <String>();
		
		if (newsForm.getTitle().getValue() == null || newsForm.getTitle().getValue().equals("")) {
			errors.add("Title cannot be emptied.");
		}
		
		if (newsForm.getBodyContent().getValue() == null || newsForm.getBodyContent().getValue().equals("")) {
			errors.add("Body content cannot be emptied.");
		}
		
		if (newsForm.getNewsType().getValue() == null) {
			errors.add("News type cannot be emptied.");	
		}
		
		if (newsForm.getNewsStatus().getValue() == null) {
			errors.add("News status cannot be emptied.");	
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
	
	private void newNewsPage() {
		NewNewsPage newNewsPage = new NewNewsPage();
		
		NewsForm newsForm = newNewsPage.getNewsForm();
		newsForm.getNewsType().setItems(getNewsTypeList());
		newsForm.getNewsStatus().setItems(getNewsStatusList());
		newsForm.getSmallImageFileUpload().addSucceededListener(event -> {
			smallFileName = "li-" + event.getFileName();
			smallFileType = event.getMIMEType();
		});
		newsForm.getBigImageFileUpload().addSucceededListener(event -> {
			bigFileName = "mi-" + event.getFileName();
			bigFileType = event.getMIMEType();
		});
	
		newNewsPage.getSaveButton().addClickListener(listener -> {
			if (!validateForm(newsForm)) {
				return;
			}
			
			setNotification("News is saving into the system...");
			Date now = new Date();
			
			selectedNews = new News();
			selectedNews.setNewsDateTime(now);
			selectedNews.setTitle(newsForm.getTitle().getValue());
			selectedNews.setBodyContent(newsForm.getBodyContent().getValue());
			selectedNews.setNewsType(NewsType.valueOf(newsForm.getNewsType().getValue()));
			selectedNews.setNewsStatus(NewsStatus.valueOf(newsForm.getNewsStatus().getValue()));
			
			selectedNews = newsService.save(selectedNews);
			
			if (newsForm.getSmallImageFileUpload() != null && newsForm.getSmallFileBuffer() != null && newsForm.getSmallFileBuffer().getFileName() != null) {
				InputStream is = newsForm.getSmallFileBuffer().getInputStream();
				byte[] byteData = null;
				
				try {
					byteData = IOUtils.toByteArray(is);
					Image smallImage = createImageObject(selectedNews, byteData, smallFileName, smallFileType);
					imageService.saveImage(smallImage);
					
					selectedNews.getImageList().add(smallImage);
					newsService.update(selectedNews);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (newsForm.getBigImageFileUpload() != null && newsForm.getBigFileBuffer() != null && newsForm.getBigFileBuffer().getFileName() != null) {
				InputStream is = newsForm.getBigFileBuffer().getInputStream();
				byte[] byteData = null;
				
				try {
					byteData = IOUtils.toByteArray(is);
					Image bigImage = createImageObject(selectedNews, byteData, bigFileName, bigFileType);
					imageService.saveImage(bigImage);
					
					selectedNews.getImageList().add(bigImage);
					newsService.update(selectedNews);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			newNewsPage.close();
			getNewsList();
			newsGrid.getDataProvider().refreshItem(selectedNews);
			selectedNews = new News();
		});
		newNewsPage.open();
	}
	
	private void readNewsPage() {
		if (selectedNews == null) {
			setNotification("You need to select a news...");
			return;
		}
		
		ReadNewsPage readNewsPage = new ReadNewsPage();
		readNewsPage.open();
	
		NewsForm newsForm = readNewsPage.getNewsForm();
		mapNewsToForm(selectedNews, newsForm);
	}
	
	private void updateNewsPage() {
		if (selectedNews == null) {
			setNotification("You need to select a news...");
			return;
		}
		
		UpdateNewsPage updateNewsPage = new UpdateNewsPage();
		updateNewsPage.open();
		
		NewsForm newsForm = updateNewsPage.getNewsForm();
		mapNewsToForm(selectedNews, newsForm);
		
		newsForm.getSmallImageFileUpload().addSucceededListener(event -> {
			smallFileName = "li-" + event.getFileName();
			smallFileType = event.getMIMEType();
		});
		newsForm.getBigImageFileUpload().addSucceededListener(event -> {
			bigFileName = "mi-" + event.getFileName();
			bigFileType = event.getMIMEType();
		});
		
		updateNewsPage.getSaveButton().addClickListener(listener -> {
			if (!validateForm(newsForm)) {
				return;
			}
			
			Image smallImage = null;
			Image bigImage = null;
			
			if (newsForm.getSmallImageFileUpload() != null && newsForm.getSmallFileBuffer() != null && newsForm.getSmallFileBuffer().getFileName() != null) {
				System.out.println("Have new small image...");
				InputStream is = newsForm.getSmallFileBuffer().getInputStream();
				byte[] byteData = null;
				
				try {
					byteData = IOUtils.toByteArray(is);
					
					List<Image> imageList = imageService.getImageListByNewsId(selectedNews.getId());
					Long smallImageTempId = null;
					
					if (imageList != null && !imageList.isEmpty()) {
						for (Image tempImage : imageList) {
							if (tempImage.getImageName().startsWith("li-")) {
								smallImageTempId = tempImage.getId();
								smallImage = tempImage;
							}
						}
					}
					
					// Create new image
					if (byteData != null && byteData.length > 0) {
						Image newSmallImage = createImageObject(selectedNews, byteData, smallFileName, smallFileType);
						imageService.saveImage(newSmallImage);
						
						// Delete old image
						if (smallImage != null) {
							imageService.deleteImage(smallImage);
						}
					}
					// Existing image
					else {
						if (smallImage != null && smallImageTempId != null) {
							imageService.saveImage(smallImage);
						}
					}
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (newsForm.getBigImageFileUpload() != null && newsForm.getBigFileBuffer() != null && newsForm.getBigFileBuffer().getFileName() != null) {
				System.out.println("Have new big image...");
				InputStream is = newsForm.getBigFileBuffer().getInputStream();
				byte[] byteData = null;
				
				try {
					byteData = IOUtils.toByteArray(is);
					
					List<Image> imageList = imageService.getImageListByNewsId(selectedNews.getId());
					Long bigImageTempId = null;
					
					if (imageList != null && !imageList.isEmpty()) {
						for (Image tempImage : imageList) {
							if (tempImage.getImageName().startsWith("mi-")) {
								bigImageTempId = tempImage.getId();
								bigImage = tempImage;
							}
						}
					}
					
					// Create new image
					if (byteData != null && byteData.length > 0) {
						Image newBigImage = createImageObject(selectedNews, byteData, bigFileName, bigFileType);
						imageService.saveImage(newBigImage);
						
						// Delete old image
						if (bigImage != null) {
							imageService.deleteImage(bigImage);
						}
					}
					else {
						if (bigImage != null && bigImageTempId != null) {
							bigImage.setId(bigImageTempId);
							imageService.saveImage(bigImage);
						}
					}
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		
			List<Image> newImageList = new ArrayList<>();
			newImageList.add(smallImage);
			newImageList.add(bigImage);
			
			mapFormToNews(newsForm, selectedNews);
			selectedNews.setImageList(newImageList);
			newsService.save(selectedNews);
			setNotification("News is being updated into the system...");
						
			updateNewsPage.close();
			getNewsList();
			newsGrid.getDataProvider().refreshItem(selectedNews);
			selectedNews = new News();
		});
	}
	
	private void mapNewsToForm(News news, NewsForm newsForm) {
		newsForm.getNewsDateTime().setValue(news.getNewsDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		newsForm.getTitle().setValue(news.getTitle());
		newsForm.getBodyContent().setValue(news.getBodyContent());
		
		newsForm.getNewsType().setItems(getNewsTypeList());
		newsForm.getNewsType().setValue(news.getNewsType().name());
		
		newsForm.getNewsStatus().setItems(getNewsStatusList());
		newsForm.getNewsStatus().setValue(news.getNewsStatus().name());
	}
	
	private void mapFormToNews(NewsForm newsForm, News news) {
		news.setNewsDateTime(Date.from(newsForm.getNewsDateTime().getValue().atZone(ZoneId.systemDefault()).toInstant()));
		news.setTitle(newsForm.getTitle().getValue());
		news.setBodyContent(newsForm.getBodyContent().getValue());
		news.setNewsType(NewsType.valueOf(newsForm.getNewsType().getValue()));
		news.setNewsStatus(NewsStatus.valueOf(newsForm.getNewsStatus().getValue()));
	}
	
	private List<String> getNewsTypeList() {
		List<String> newsTypeList = new ArrayList<>();
		
		for (NewsType item : NewsType.values()) {
			newsTypeList.add(item.name());
		}
		
		return newsTypeList;
	}
	
	private List<String> getNewsStatusList() {
		List<String> newsStatusList = new ArrayList<>();
		
		for (NewsStatus status : NewsStatus.values()) {
			newsStatusList.add(status.name());
		}
		
		return newsStatusList;
	}
	
	private Image createImageObject(News news, byte[] byteArray, String fileName, String fileType) {
		Image image = new Image();
		
		if (byteArray != null && byteArray.length > 0) {
			image.setImageName(fileName);
			image.setContentType(fileType);
			image.setContentByte(byteArray);
			image.setNews(news);
		}
		
		return image;
	}
	
	private void setNotification(String content ) {
		Notification.show(content, 2000, Position.BOTTOM_CENTER);
	}
}
