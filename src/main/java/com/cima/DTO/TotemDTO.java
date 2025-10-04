package com.cima.DTO;

import com.cima.models.SupplyWarehouse;
import com.cima.models.Totem;

public record TotemDTO(Integer id, SupplyWarehouse supplyWarehouse) {
  public TotemDTO(Totem totem) {
    this(totem.getId(), totem.getSupplyWarehouse());
  }
}