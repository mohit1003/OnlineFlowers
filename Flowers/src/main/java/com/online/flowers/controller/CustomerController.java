package com.online.flowers.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.flowers.dto.Customer;
import com.online.flowers.dto.Sales;
import com.online.flowers.service.CustomerService;
import com.online.flowers.service.SalesService;

@RestController
@RequestMapping(path = "/customer")
@CrossOrigin(origins = "*")
public class CustomerController {


	@Autowired
	private SalesService _salesService;


	@Autowired
	private CustomerService _customerService;


	int registerFlag = 0;

	int loginFlag = 0;

	

	@PostMapping(value = "/getUserByEmail")
	public Optional<ResponseEntity<Customer>> getCustomerByEmail(@RequestBody String email) {
		return Optional.ofNullable(
				new ResponseEntity<Customer>(_customerService.ConvertToCustomerDtoAndSend(email), HttpStatus.OK));
	}



	@PostMapping(value = "/pay")
	public ResponseEntity<String> makeTransaction(@RequestBody Sales flowersPaymentDone) {
		try {
			_salesService.recordTransaction(flowersPaymentDone);
			return new ResponseEntity<String>("Payment Success", HttpStatus.CREATED);
		} catch (SQLException e) {
			return new ResponseEntity<String>("Payment falied", HttpStatus.CONFLICT);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Payment falied", HttpStatus.CONFLICT);
		}

	}

}
