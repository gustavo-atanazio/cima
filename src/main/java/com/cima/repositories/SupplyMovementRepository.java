package com.cima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cima.models.SupplyMovement;

public interface SupplyMovementRepository extends JpaRepository<SupplyMovement, Integer> {}