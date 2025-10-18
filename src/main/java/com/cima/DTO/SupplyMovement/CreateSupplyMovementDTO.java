package com.cima.DTO.SupplyMovement;

public record CreateSupplyMovementDTO(
  Integer totemID,
  Integer employeeID,
  Integer supplyID,
  Integer quantity
) {}