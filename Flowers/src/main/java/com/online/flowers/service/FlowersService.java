package com.online.flowers.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.dto.Message;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.FlowersRepo;

@Service
public class FlowersService {
	
	@Autowired
	private FlowersRepo _repo;
	
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
				FlowersModel((String)result.get("original_filename"), 
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
		
//		Map result = null;
		//cloudinaryService.delete(getPublicIdById(flowerId));
		
		
		//FlowersModel flowerImage = new FlowersModel(Integer.parseInt(id), name, category, price, description);
		
		flowersRepo.save(flower);
		return "ok";
	}
	
	
	public String getPublicIdById(String id) {
		return flowersRepo.findPublicIdById(id);
	}
	
	
	
//	public static byte[] compressBytes(byte[] data) {
//		Deflater deflater = new Deflater();
//		deflater.setInput(data);
//		deflater.finish();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[2048];
//		while (!deflater.finished()) {
//			int count = deflater.deflate(buffer);
//			outputStream.write(buffer, 0, count);
//		}
//		try {
//			outputStream.close();
//		} catch (IOException e) {
//		}
//		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//		return outputStream.toByteArray();
//	}
//	
//	public static byte[] decompressBytes(byte[] data) {
//		Inflater inflater = new Inflater();
//		inflater.setInput(data);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//		byte[] buffer = new byte[1024];
//		try {
//			while (!inflater.finished()) {
//				int count = inflater.inflate(buffer);
//				outputStream.write(buffer, 0, count);
//			}
//			outputStream.close();
//		} catch (IOException ioe) {
//		} catch (DataFormatException e) {
//		}
//		return outputStream.toByteArray();
//	}

}
