package com.cima.DTO.SupplyMovement;

import java.time.LocalDateTime;
import com.cima.models.SupplyMovement;

public record SupplyMovementDTO(
  Integer id,
  LocalDateTime date,
  Integer totemID,
  Integer employeeID,
  Integer supplyID,
  Integer quantity
) {
  public SupplyMovementDTO(SupplyMovement supplyMovement) {
    this(
      supplyMovement.getId(),
      supplyMovement.getDate(),
      supplyMovement.getTotem().getId(),
      supplyMovement.getEmployee().getId(),
      supplyMovement.getSupply().getId(),
      supplyMovement.getQuantity()
    );
  }
}