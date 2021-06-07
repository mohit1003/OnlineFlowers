package com.online.flowers.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.flowers.model.DWMReportsModel;

public interface DWMReportRepo extends JpaRepository<DWMReportsModel, Integer> {
	@Query(value = "SELECT * FROM dwmreports_model WHERE flower_id_fk = ?1 && date = ?2", nativeQuery = true)
	public DWMReportsModel findByFlowerId(int flowerId, LocalDate date);
	
	public List<DWMReportsModel> getByDate(LocalDate date);
	
	@Query(value = "SELECT * FROM dwmreports_model WHERE date BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<DWMReportsModel> getByDateFromTo(LocalDate dateFrom,LocalDate dateTo);
}
