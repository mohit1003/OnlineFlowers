package com.online.flowers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.flowers.controller.AdminController;

public class AdminControllerTest {
	
	@MockBean
	private AdminController _adminController;
	
	@Test
	public void saveFlowerImage() {
		when(_adminController.saveFlowerImage(TestData.getFile(), "Symphy", "Sympathy flowers", "Whity", "300"))
		.thenReturn(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED), _adminController.saveFlowerImage(TestData.getFile(), "Symphy", "Sympathy flowers", "Whity", "300"));
	}
	
	@Test
	public void updateFlowerTest() {
		when(_adminController.updateFlower("23", TestData.getFile(),"Symphy", "Sympathy flowers", "Whity", "300"))
		.thenReturn(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED), _adminController.updateFlower("23", TestData.getFile(),"Symphy", "Sympathy flowers", "Whity", "300"));
	}
	
	@Test
	public void updateFlowerWithoutImageTest() {
		when(_adminController.updateFlowerWithoutImage(TestData.getFlowerModel()))
		.thenReturn(new ResponseEntity<String>("Image is uploaded/modified", HttpStatus.OK));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded/modified", HttpStatus.OK), _adminController.updateFlowerWithoutImage(TestData.getFlowerModel()));
	}
	
	@Test
	public void deleteTest() {
		Map<String, String> id = new HashMap<>();
		id.put("id", "22");
		when(_adminController.delete(id)).thenReturn(new ResponseEntity<String>("Image deleted", HttpStatus.OK));
		
		assertEquals(new ResponseEntity<String>("Image deleted", HttpStatus.OK), _adminController.delete(id));
	}
	
	@Test
	public void addShopTest() {
		when(_adminController.addShop(TestData.getFile(), "deligho", "India", "India", "123, east lane", "open", "9373774634"))
		.thenReturn(new ResponseEntity<String>("Shop added successfully", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Shop added successfully", HttpStatus.CREATED), _adminController.addShop(TestData.getFile(), "deligho", "India", "India", "123, east lane", "open", "9373774634"));
	}

}
