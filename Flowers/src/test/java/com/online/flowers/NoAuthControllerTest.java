package com.online.flowers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.online.flowers.controller.NoAuthController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoAuthControllerTest {
	
	@MockBean
	private NoAuthController _noAuthController;
	
	@BeforeAll
	public static void setUp() {
		TestData.setUp();
	}

	
	@Test
	public void registerUserTest() {
		when(_noAuthController.registerUser(TestData.getCustomerModel()))
				.thenReturn(new ResponseEntity<String>("User is registered", HttpStatus.CREATED));
		assertEquals(new ResponseEntity<String>("User is registered", HttpStatus.CREATED),
				_noAuthController.registerUser(TestData.getCustomerModel()));
	}

	@Test
	public void userLoginTest() {
		when(_noAuthController.userLogin(TestData.getCustomerModel()))
				.thenReturn(new ResponseEntity<String>("user is logged in", HttpStatus.OK));
		assertEquals(new ResponseEntity<String>("user is logged in", HttpStatus.OK),
				_noAuthController.userLogin(TestData.getCustomerModel()));
	}

	@Test
	public void generateTokenTest() throws Exception {
		when(_noAuthController.generateToken(TestData.getAuthRequest()))
				.thenReturn(new ResponseEntity<String>("sadajsd sand sandajsdan", HttpStatus.OK));
		assertEquals(new ResponseEntity<String>("sadajsd sand sandajsdan", HttpStatus.OK),
				_noAuthController.generateToken(TestData.getAuthRequest()));
	}
	
	@Test
	public void getShopTest() {
		when(_noAuthController.getShop()).thenReturn(TestData.getShopList());
		
		assertEquals(TestData.getShopList(), _noAuthController.getShop());
	}

	@AfterAll
	public static void cleanUp() {
		TestData.cleanUp();
	}
}

