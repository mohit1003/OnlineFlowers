package com.online.flowers.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.CategoryWiseReportsRepo;
import com.online.flowers.repo.DWMReportRepo;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.repo.ReportRepo;
import com.online.flowers.repo.SalesRepo;

@Service
public class FlowersService {
	
	@Autowired
	private FlowersRepo _repo;
	
	@Autowired
	private SalesRepo _salesRepo;

	@Autowired
	private ReportRepo _reportRepo;
	
	@Autowired
	private DWMReportRepo _dwmReportRepo;
	
	@Autowired
	private CategoryWiseReportsRepo _categoryWiseReportsRepo;

	
	@Autowired
	private ClaudinaryService cloudinaryService;
	
	@Autowired
	private FlowersRepo flowersRepo;
	

	public String saveImage(MultipartFile file, String name, String category, String price, String description) {
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
		
		FlowersModel flowerImage = new 
				FlowersModel(name, 
							(String)result.get("url"),
							(String)result.get("public_id"),
							category, 
							price, description);
		
		flowersRepo.save(flowerImage);
		return "ok";
	}
	
	
	public String updateFlower(String id, MultipartFile file, String name, String category, String price, String description) {
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
		String imageId = getPublicIdById(id);
		
		Optional.ofNullable(imageId).ifPresent(imageid -> {
			try {
				cloudinaryService.delete(imageId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
			
		Map result = null;
		try {
			result = cloudinaryService.upload(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "File not exists";
		}
		
		FlowersModel flowerRecord = new FlowersModel();
		flowerRecord.setId(Integer.parseInt(id));
		flowerRecord.setName((String)result.get("original_filename"));
		flowerRecord.setImageUrl((String)result.get("url"));
		flowerRecord.setImageId((String)result.get("public_id"));
		flowerRecord.setCategory(category);
		flowerRecord.setDescription(description);
		flowerRecord.setPrice(price);
							
		flowersRepo.save(flowerRecord);
		return "ok";
	}
	
	public String updateFlowerWithoutImage(FlowersModel flower) {	

		flowersRepo.save(flower);
		return "ok";
	}
	
	
	public String getPublicIdById(String id) {
		return flowersRepo.findPublicIdById(id);
	}
	
	
	public List<FlowersModel> list(){
        return flowersRepo.findByOrderById();
    }
	
	public void deleteFlower(String id){
    	flowersRepo.deleteById(Integer.parseInt(id));
    }
	
	
	

}
