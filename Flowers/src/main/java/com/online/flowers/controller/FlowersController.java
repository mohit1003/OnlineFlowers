package com.online.flowers.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.model.FlowersModel;
import com.online.flowers.service.FlowersService;

@RestController
@CrossOrigin(origins = "*")
public class FlowersController {
	
	@Autowired
	private FlowersService flowersService;
	
	@PostMapping(value = "/add")
	public BodyBuilder saveFlowerImage(@RequestParam("flowerImage") MultipartFile flower, 
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("price") String price) {
		flowersService.saveImage(flower, category, price, description);
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllFlowers" )
	public List<FlowersModel> getImage() {
		try {
			return flowersService.getImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return new ResponseEntity<List<FlowersModel>>(HttpStatus.CONFLICT);
		return null;
	}

}
