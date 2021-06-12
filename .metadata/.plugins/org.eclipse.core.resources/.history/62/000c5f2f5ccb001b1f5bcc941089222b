package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.CustomerModel;

public interface CustomerRepo extends JpaRepository<CustomerModel, String> {

	public CustomerModel findByEmail(String email);

}
