package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.flowers.model.ReportsModel;
import com.online.flowers.model.SalesModel;

public interface ReportRepo extends JpaRepository<ReportsModel, Integer> {
	
	@Query(value = "SELECT * FROM reports_model WHERE flower_id_fk = ?1", nativeQuery = true)
	public ReportsModel findByFlowerId(int flowerId);
	
	@Query(value = "SELECT * , SUM(price) AS total "
			+ "FROM reports_model "
			+ "GROUP BY id "
			+ "ORDER BY total DESC LIMIT 1 ", nativeQuery = true)
	public ReportsModel findMostSoldProduct();
	
	@Query(value = "SELECT * , SUM(price) AS total "
			+ "FROM reports_model "
			+ "GROUP BY id "
			+ "ORDER BY total LIMIT 1 ", nativeQuery = true)
	public ReportsModel findLeastSoldProduct();
}
