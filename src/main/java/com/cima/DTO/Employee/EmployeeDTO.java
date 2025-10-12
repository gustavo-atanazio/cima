package com.cima.DTO.Employee;

import java.util.List;
import com.cima.models.Employee;

public record EmployeeDTO(Integer id, String name, Integer accessLevel, List<Integer> unitIDs) {
  public EmployeeDTO(Employee employee) {
    this(
      employee.getId(),
      employee.getName(),
      employee.getAccessLevel(),
      employee.getUnits().stream().map(unit -> unit.getId()).toList()
    );
  }
}