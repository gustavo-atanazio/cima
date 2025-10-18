package com.cima.DTO.Supply;

import java.util.List;

import com.cima.models.Supply;
import com.cima.models.SupplyMovement;
import com.cima.models.SupplyWarehouse;

public record SupplyDTO(
  Integer id,
  String name,
  Integer lotNumber,
  Integer quantity,
  List<Integer> supplyWarehouseIDs,
  List<Integer> supplyMovementIDs
) {
  public SupplyDTO(Supply supply) {
    this(
      supply.getId(),
      supply.getName(),
      supply.getLotNumber(),
      supply.getQuantity(),
      supply.getSupplyWarehouses().stream()
        .map(SupplyWarehouse::getId)
        .toList(),
      supply.getSupplyMovements().stream()
        .map(SupplyMovement::getId)
        .toList()
    );
  }
}