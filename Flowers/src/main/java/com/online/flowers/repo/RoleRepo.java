package com.online.flowers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.flowers.model.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer> {

}
