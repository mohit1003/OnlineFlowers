package com.online.flowers.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.flowers.model.FlowersModel;

@RestController
@CrossOrigin(origins = "*")
public class FlowersController {
	
	@PostMapping(value = "/add")
	public void saveFlowerImage(@RequestBody FlowersModel flower) {
		System.out.println(flower);
	}

}
