package com.online.flowers.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.online.flowers.dto.Customer;
import com.online.flowers.dto.CustomerDto;
import com.online.flowers.model.CustomerModel;

@Component
public class DtoMapperService {

	private ModelMapper _mapper = new ModelMapper();

	public Customer convertToCustomerDto(Optional<CustomerModel> customer) {
		CustomerModel customerToConvert = customer.get();
		Customer customerToSend = _mapper.map(customerToConvert, Customer.class);
		return customerToSend;
	}

	public CustomerDto convertToCustomerDtoList(CustomerModel customer) {
		return _mapper.map(customer, CustomerDto.class);
	}

}
