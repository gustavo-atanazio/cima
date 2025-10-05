package com.cima.DTO;

import java.util.List;

import com.cima.models.Supply;
import com.cima.models.SupplyMovement;
import com.cima.models.SupplyWarehouse;

public record SupplyDTO(
  Integer id,
  String name,
  Integer lotNumber,
  Integer quantity,
  List<SupplyWarehouse> supplyWarehouses,
  List<SupplyMovement> supplyMovements
) {
  public SupplyDTO(Supply supply) {
    this(
      supply.getId(),
      supply.getName(),
      supply.getLotNumber(),
      supply.getQuantity(),
      supply.getSupplyWarehouses(),
      supply.getSupplyMovements()
    );
  }
}