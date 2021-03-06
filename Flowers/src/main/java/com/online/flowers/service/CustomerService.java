package com.online.flowers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.flowers.dto.Customer;
import com.online.flowers.dto.CustomerDto;
import com.online.flowers.dto.CustomerRegionWiseReport;
import com.online.flowers.model.CustomerModel;
import com.online.flowers.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo _customerRepo;

	@Autowired
	private DtoMapperService _mapper;

	public Customer ConvertToCustomerDtoAndSend(String customerEmail) {

		return _mapper.convertToCustomerDto(_customerRepo.findById(customerEmail));
	}

	public List<CustomerDto> getAllCustomers() {
		List<CustomerModel> customers = _customerRepo.findAll();
		List<CustomerDto> customersToSend = customers.stream().map(_mapper::convertToCustomerDtoList)
				.collect(Collectors.toList());

		return customersToSend;
	}

	public List<CustomerRegionWiseReport> getCustomerRegionWiseData() {
		List<CustomerRegionWiseReport> customersCityWise = _customerRepo.getCustomersGroupByCity();

		return customersCityWise;

	}

}
