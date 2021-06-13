package com.online.flowers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.online.flowers.model.CustomerModel;
import com.online.flowers.repo.CustomerRepo;

@SpringBootApplication
public class FlowersApplication {
	
	@Autowired
	private CustomerRepo customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlowersApplication.class, args);
	}

//	@PostConstruct
//	public void initUsers() {
//		List<CustomerModel> customers = Stream.of(
//			new CustomerModel("1@2", "MR", "Mohit", "Kulkarni", "123 Main street", "Pune city", "India", "password", "9921918100")
//				).collect(Collectors.toList());
//		customerRepo.saveAll(customers);
//	}
}
