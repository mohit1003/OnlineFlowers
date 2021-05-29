package com.online.flowers.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.online.flowers.dto.Message;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.service.ClaudinaryService;
import com.online.flowers.service.FlowersService;
import com.online.flowers.service.ImageService;

@RestController
@CrossOrigin(origins = "*")
public class FlowersController {
	
	@Autowired
	private FlowersService flowersService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FlowersRepo _repo;
	
	@Autowired
	private ClaudinaryService cloudinaryService;

	
	@PostMapping(value = "/add")
	public ResponseEntity<?> saveFlowerImage(@RequestParam("flowerImage") MultipartFile flower,
			@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("price") String price) {
		
		String message = flowersService.saveImage(flower,name, category, price, description);
		if(message.equals("Image not valid")) {
			return new ResponseEntity(new Message("Image not valid"), HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity(new Message("Image is uploaded"), HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "/getAllFlowers")
    public ResponseEntity<List<FlowersModel>> list(){
        List<FlowersModel> list = imageService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
	
	@DeleteMapping(value = "delete")
	public ResponseEntity<?> delete(@RequestBody Map<String, String> id){
		String flowerId = id.get("id");
		if(!id.get("id").isEmpty()) {
			try {
				cloudinaryService.delete(flowersService.getPublicIdById(flowerId));
				imageService.delete(flowerId);
				return new ResponseEntity(new Message("Image deleted"), HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity(new Message("Error deleting image"), HttpStatus.CONFLICT);
		
	}
	
	public Optional<FlowersModel> getImage(Integer id) {
		return _repo.findById(id);
	}

}
