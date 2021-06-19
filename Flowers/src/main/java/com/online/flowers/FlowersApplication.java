package com.online.flowers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FlowersApplication {
	

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
