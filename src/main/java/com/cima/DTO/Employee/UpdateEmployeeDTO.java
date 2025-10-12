package com.cima.DTO.Employee;

import java.util.List;

public record UpdateEmployeeDTO(
  String name,
  Integer accessLevel,
  List<Integer> unitIDs
) {}