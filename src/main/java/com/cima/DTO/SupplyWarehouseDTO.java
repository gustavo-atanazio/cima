package com.cima.DTO;

import com.cima.models.SupplyWarehouse;
import com.cima.models.Unit;

public record SupplyWarehouseDTO(Integer id, Unit unit) {
  public SupplyWarehouseDTO(SupplyWarehouse supplyWarehouse) {
    this(supplyWarehouse.getId(), supplyWarehouse.getUnit());
  }
}