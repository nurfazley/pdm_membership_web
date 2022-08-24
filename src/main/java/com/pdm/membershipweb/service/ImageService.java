package com.pdm.membershipweb.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdm.membership.dao.ImageDAO;
import com.pdm.membership.model.Image;

@Service("imageService")
@Transactional
public class ImageService {

	@Autowired
	private ImageDAO imageDAO;
	
	
	public Image saveImage(Image image) {
		return imageDAO.save(image);
	}
}
