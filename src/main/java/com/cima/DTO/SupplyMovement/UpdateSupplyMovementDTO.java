package com.cima.DTO.SupplyMovement;

import java.time.LocalDateTime;

public record UpdateSupplyMovementDTO(
  LocalDateTime date,
  Integer totemID,
  Integer employeeID,
  Integer supplyID,
  Integer quantity
) {}