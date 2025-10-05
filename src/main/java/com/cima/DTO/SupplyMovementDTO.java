package com.cima.DTO;

import java.time.LocalDateTime;

import com.cima.models.Employee;
import com.cima.models.Supply;
import com.cima.models.SupplyMovement;
import com.cima.models.Totem;

public record SupplyMovementDTO(
  Integer id,
  LocalDateTime date,
  Totem totem,
  Employee employee,
  Supply supply,
  Integer quantity
) {
  public SupplyMovementDTO(SupplyMovement supplyMovement) {
    this(
      supplyMovement.getId(),
      supplyMovement.getDate(),
      supplyMovement.getTotem(),
      supplyMovement.getEmployee(),
      supplyMovement.getSupply(),
      supplyMovement.getQuantity()
    );
  }
}