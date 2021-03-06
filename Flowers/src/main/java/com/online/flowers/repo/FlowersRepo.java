package com.online.flowers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.flowers.model.FlowersModel;

public interface FlowersRepo extends JpaRepository<FlowersModel, Integer> {
	List<FlowersModel> findByOrderById();
	
	@Query(value = "SELECT IMAGE_ID FROM FLOWERS_MODEL WHERE id = ?1", nativeQuery = true)
	String findPublicIdById(String id);
}
