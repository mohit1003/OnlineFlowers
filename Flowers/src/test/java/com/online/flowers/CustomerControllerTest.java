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

import com.online.flowers.controller.CustomerController;
import com.online.flowers.controller.NoAuthController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@MockBean
	private NoAuthController _noAuthController;
	
	@MockBean
	private CustomerController _flowersController;

	@BeforeAll
	public static void setUp() {
		TestData.setUp();
	}

	
//	@Test
//	public void getCustomerByEmailTest() {
//		when(_controller.getCustomerByEmail("mohit@gmail.com")).thenReturn(
//				new Optional<ResponseEntity<Customer>>());
//		assertEquals((ResponseEntity) new ResponseEntity<Customer>(TestData.getCustomer(), HttpStatus.OK), _controller.getCustomerByEmail("mohit@gmail.com"));
//	}

	
//	@Test
//	public void getImageTest() {
//		when(_controller.getImage(22)).thenReturn(Optional<FlowersModel>(TestData.getFlowerModel()))
//	}
//	
	

	
	@Test
	public void makeTransactionTest() {
		when(_flowersController.makeTransaction(TestData.getSales())).thenReturn(new ResponseEntity<String>("Payment Success", HttpStatus.CREATED));
		
		assertEquals(new ResponseEntity<String>("Payment Success", HttpStatus.CREATED), _flowersController.makeTransaction(TestData.getSales()));
	}
	

	@AfterAll
	public static void cleanUp() {
		TestData.cleanUp();
	}
}
