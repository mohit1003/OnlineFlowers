package com.online.flowers.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.online.flowers.model.SalesModel;

public interface SalesRepo extends JpaRepository<SalesModel, Integer>{
	
	public List<SalesModel> getByDate(LocalDate date);
	
}
