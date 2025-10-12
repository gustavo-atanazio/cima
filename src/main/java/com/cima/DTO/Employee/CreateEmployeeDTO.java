package com.cima.DTO.Employee;

import java.util.List;

public record CreateEmployeeDTO(
  String name,
  Integer accessLevel,
  List<Integer> unitIDs
) {}