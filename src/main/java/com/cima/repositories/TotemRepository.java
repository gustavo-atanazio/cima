package com.cima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cima.models.SupplyWarehouse;
import com.cima.models.Totem;

public interface TotemRepository extends JpaRepository<Totem, Integer> {
  boolean existsBySupplyWarehouse(SupplyWarehouse supplyWarehouse);
}