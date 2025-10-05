package com.cima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cima.models.SupplyWarehouse;

public interface SupplyWarehouseRepository extends JpaRepository<SupplyWarehouse, Integer> {}