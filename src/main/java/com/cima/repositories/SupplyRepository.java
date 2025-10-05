package com.cima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cima.models.Supply;

public interface SupplyRepository extends JpaRepository<Supply, Integer> {}