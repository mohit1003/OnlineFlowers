package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.SalesModel;

public interface SalesRepo extends JpaRepository<SalesModel, Integer>{

}
