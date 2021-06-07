package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.CategoryWiseReportModel;

public interface CategoryWiseReportsRepo extends JpaRepository<CategoryWiseReportModel, String> {
	
	public CategoryWiseReportModel findByCategory(String category);

}
