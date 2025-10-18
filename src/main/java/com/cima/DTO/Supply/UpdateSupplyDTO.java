package com.cima.DTO.Supply;

import java.util.List;

public record UpdateSupplyDTO(
  String name,
  Integer lotNumber,
  Integer quantity,
  List<Integer> supplyWarehouseIDs,
  List<Integer> supplyMovementIDs
) {}