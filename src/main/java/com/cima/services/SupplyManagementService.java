package com.cima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cima.DTO.Supply.UpdateSupplyDTO;
import com.cima.DTO.SupplyMovement.CreateSupplyMovementDTO;
import com.cima.models.Supply;
import com.cima.models.SupplyMovement;

@Service
public class SupplyManagementService {
  @Autowired
  private SupplyService supplyService;

  @Autowired
  private SupplyMovementService supplyMovementService;

  @Transactional
  public SupplyMovement createMovement(CreateSupplyMovementDTO movementDTO) {
    return supplyMovementService.create(movementDTO);
  }

  @Transactional
  public Supply updateSupplyWithMovements(Integer id, UpdateSupplyDTO supplyDetails) {
    return supplyService.update(id, supplyDetails);
  }
}