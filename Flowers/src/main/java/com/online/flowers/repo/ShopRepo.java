package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.ShopModel;

public interface ShopRepo extends JpaRepository<ShopModel, Integer> {

}
