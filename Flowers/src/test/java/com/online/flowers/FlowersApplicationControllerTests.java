package com.online.flowers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.online.flowers.controller.FlowersController;
import com.online.flowers.dto.Customer;
import com.online.flowers.model.FlowersModel;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlowersApplicationControllerTests {

	@MockBean
	private FlowersController _controller;

	@BeforeAll
	public static void setUp() {
		TestData.setUp();
	}

	@Test
	public void registerUserTest() {
		when(_controller.registerUser(TestData.getCustomerModel()))
				.thenReturn(new ResponseEntity<String>("User is registered", HttpStatus.CREATED));
		assertEquals(new ResponseEntity<String>("User is registered", HttpStatus.CREATED),
				_controller.registerUser(TestData.getCustomerModel()));
	}

	@Test
	public void userLoginTest() {
		when(_controller.userLogin(TestData.getCustomerModel()))
				.thenReturn(new ResponseEntity<String>("user is logged in", HttpStatus.OK));
		assertEquals(new ResponseEntity<String>("user is logged in", HttpStatus.OK),
				_controller.userLogin(TestData.getCustomerModel()));
	}

	@Test
	public void generateTokenTest() throws Exception {
		when(_controller.generateToken(TestData.getAuthRequest()))
				.thenReturn(new ResponseEntity<String>("sadajsd sand sandajsdan", HttpStatus.OK));
		assertEquals(new ResponseEntity<String>("sadajsd sand sandajsdan", HttpStatus.OK),
				_controller.generateToken(TestData.getAuthRequest()));
	}

//	@Test
//	public void getCustomerByEmailTest() {
//		when(_controller.getCustomerByEmail("mohit@gmail.com")).thenReturn(
//				new Optional<ResponseEntity<Customer>>());
//		assertEquals((ResponseEntity) new ResponseEntity<Customer>(TestData.getCustomer(), HttpStatus.OK), _controller.getCustomerByEmail("mohit@gmail.com"));
//	}

	@Test
	public void saveFlowerImageTest() {
		when(_controller.saveFlowerImage(TestData.getFile(), "Symphy", "Sympathy flowers", "Whity", "300"))
		.thenReturn(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED), _controller.saveFlowerImage(TestData.getFile(), "Symphy", "Sympathy flowers", "Whity", "300"));
	}
	
	@Test
	public void updateFlowerTest() {
		when(_controller.updateFlower("23", TestData.getFile(),"Symphy", "Sympathy flowers", "Whity", "300"))
		.thenReturn(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED), _controller.updateFlower("23", TestData.getFile(),"Symphy", "Sympathy flowers", "Whity", "300"));
	}
	
	@Test
	public void updateFlowerWithoutImageTest() {
		when(_controller.updateFlowerWithoutImage(TestData.getFlowerModel()))
		.thenReturn(new ResponseEntity<String>("Image is uploaded/modified", HttpStatus.OK));
		
		assertEquals(new ResponseEntity<String>("Image is uploaded/modified", HttpStatus.OK), _controller.updateFlowerWithoutImage(TestData.getFlowerModel()));
	}
	
	@Test
	public void deleteTest() {
		Map<String, String> id = new HashMap<>();
		id.put("id", "22");
		when(_controller.delete(id)).thenReturn(new ResponseEntity<String>("Image deleted", HttpStatus.OK));
		
		assertEquals(new ResponseEntity<String>("Image deleted", HttpStatus.OK), _controller.delete(id));
	}
	
//	@Test
//	public void getImageTest() {
//		when(_controller.getImage(22)).thenReturn(Optional<FlowersModel>(TestData.getFlowerModel()))
//	}
//	
	@Test
	public void addShopTest() {
		when(_controller.addShop(TestData.getFile(), "deligho", "India", "India", "123, east lane", "open", "9373774634"))
		.thenReturn(new ResponseEntity<String>("Shop added successfully", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Shop added successfully", HttpStatus.CREATED), _controller.addShop(TestData.getFile(), "deligho", "India", "India", "123, east lane", "open", "9373774634"));
	}
	
	@Test
	public void getShopTest() {
		when(_controller.getShop()).thenReturn(TestData.getShopList());
		
		assertEquals(TestData.getShopList(), _controller.getShop());
	}
	
	@Test
	public void makeTransactionTest() {
		when(_controller.makeTransaction(TestData.getSales())).thenReturn(new ResponseEntity<String>("Payment Success", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Payment Success", HttpStatus.CREATED), _controller.makeTransaction(TestData.getSales()));
	}
	

	@AfterAll
	public static void cleanUp() {
		TestData.cleanUp();
	}
}
