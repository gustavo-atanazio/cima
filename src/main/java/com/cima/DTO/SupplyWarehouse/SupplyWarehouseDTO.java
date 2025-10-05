package com.cima.DTO.SupplyWarehouse;

import com.cima.DTO.UnitDTO;
import com.cima.models.SupplyWarehouse;

public record SupplyWarehouseDTO(Integer id, UnitDTO unit) {
  public SupplyWarehouseDTO(SupplyWarehouse supplyWarehouse) {
    this(supplyWarehouse.getId(), new UnitDTO(supplyWarehouse.getUnit()));
  }
}