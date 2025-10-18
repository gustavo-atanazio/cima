package com.cima.DTO.SupplyWarehouse;

import com.cima.DTO.Unit.SimpleUnitDTO;
import com.cima.models.SupplyWarehouse;

public record SupplyWarehouseDTO(Integer id, SimpleUnitDTO unit) {
  public SupplyWarehouseDTO(SupplyWarehouse supplyWarehouse) {
    this(
      supplyWarehouse.getId(),
      new SimpleUnitDTO(
        supplyWarehouse.getUnit().getId(),
        supplyWarehouse.getUnit().getCEP(),
        supplyWarehouse.getUnit().getNumber()
      )
    );
  }
}