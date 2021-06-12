package com.online.flowers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.flowers.dto.CustomerRegionWiseReport;
import com.online.flowers.model.CustomerModel;

public interface CustomerRepo extends JpaRepository<CustomerModel, String> {

	public CustomerModel findByEmail(String email);
	
	@Query(value = "SELECT COUNT(email) as emailCount, city as city FROM customer_model GROUP BY city", nativeQuery = true)
	public List<CustomerRegionWiseReport> getCustomersGroupByCity();

}
