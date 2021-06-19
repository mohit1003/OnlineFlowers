package com.online.flowers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.flowers.controller.NoAuthController;

public class NoAuthControllerTest {
	
	@MockBean
	private NoAuthController _noAuthController;
	
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

}
