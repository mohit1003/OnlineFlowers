package com.online.flowers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.FlowersModel;

public interface FlowersRepo extends JpaRepository<FlowersModel, Integer> {
	List<FlowersModel> findByOrderById();
}
