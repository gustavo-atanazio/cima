package com.cima.DTO.Totem;

import com.cima.DTO.SupplyWarehouse.SimpleSupplyWarehouseDTO;
import com.cima.models.Totem;

public record TotemDTO(Integer id, SimpleSupplyWarehouseDTO supplyWarehouse) {
  public TotemDTO(Totem totem) {
    this(
      totem.getId(),
      new SimpleSupplyWarehouseDTO(
        totem.getSupplyWarehouse().getId(),
        totem.getSupplyWarehouse().getUnit().getId()
      )
    );
  }
}