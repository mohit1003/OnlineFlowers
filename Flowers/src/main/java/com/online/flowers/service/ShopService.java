package com.online.flowers.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.model.ShopModel;
import com.online.flowers.repo.ShopRepo;

@Service
public class ShopService {
	
	@Autowired
	private ClaudinaryService cloudinaryService;
	
	@Autowired
	private ShopRepo shopRepo;
	
	
	public String saveShop(MultipartFile file, String shopName, String shopCity,
			 String shopCountry, String shopAddress, String isOpen, String shopContact) {
		boolean isShopOpen = false;
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(bi == null) {
			return "Image not valid";
		}
		
		Map result = null;
		try {
			result = cloudinaryService.upload(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isOpen.equalsIgnoreCase("open")) {
			isShopOpen = true;
		}
		else {
			isShopOpen = false;
		}
		
		ShopModel shop = new ShopModel();
		shop.setShopName(shopName);
		shop.setShopAddress(shopAddress);
		shop.setShopCity(shopCity);
		shop.setShopCountry(shopCountry);
		shop.setOpen(isShopOpen);
		shop.setImageId((String)result.get("public_id"));
		shop.setImageUrl((String)result.get("url"));
		shop.setShopContact(shopContact);
		
		
		shopRepo.save(shop);
		return "ok";
		
	}
	
	

}
