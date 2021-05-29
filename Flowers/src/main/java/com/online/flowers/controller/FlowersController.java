package com.online.flowers.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.dto.Message;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.service.ClaudinaryService;
import com.online.flowers.service.FlowersService;
import com.online.flowers.service.ImageService;
import com.online.flowers.util.AuthRequest;
import com.online.flowers.util.JwtUtil;

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

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping(value = "/demo")
	public String home() {
		return "Hello home";
	}

	@PostMapping(value = "/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid username/password");
		}

		return jwtutil.generateToken(authRequest.getEmail());
	}

	@PostMapping(value = "/add")
	public ResponseEntity<String> saveFlowerImage(@RequestParam("flowerImage") MultipartFile flower,
			@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("category") String category, @RequestParam("price") String price) {

		String message = flowersService.saveImage(flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>(new Message("Image is uploaded"), HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateFlower(@RequestParam("id") String id,
			@RequestParam("flowerImage") MultipartFile flower, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("price") String price) {

		String message = flowersService.updateFlower(id, flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>(new Message("Image is uploaded"), HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/updateWithoutImage")
	public ResponseEntity<String> updateFlowerWithoutImage(@RequestBody FlowersModel flower) {
		String message = flowersService.updateFlowerWithoutImage(flower);

		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>(new Message("Image is uploaded/modified"), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/getAllFlowers")
	public ResponseEntity<List<FlowersModel>> list() {
		List<FlowersModel> list = imageService.list();
		return new ResponseEntity<List<FlowersModel>>(list, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete")
	public ResponseEntity<String> delete(@RequestBody Map<String, String> id) {
		String flowerId = id.get("id");
		if (!id.get("id").isEmpty()) {
			try {
				cloudinaryService.delete(flowersService.getPublicIdById(flowerId));
				imageService.delete(flowerId);
				return new ResponseEntity<String>(new Message("Image deleted"), HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<String>(new Message("Error deleting image"), HttpStatus.CONFLICT);

	}

	public Optional<FlowersModel> getImage(Integer id) {
		return _repo.findById(id);
	}

}
