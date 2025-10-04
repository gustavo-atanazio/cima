package com.cima.DTO;

import java.util.List;

import com.cima.models.Employee;
import com.cima.models.Unit;

public record EmployeeDTO(Integer id, String name, Integer accessLevel, List<Unit> units) {
  public EmployeeDTO(Employee employee) {
    this(
      employee.getId(),
      employee.getName(),
      employee.getAccessLevel(),
      employee.getUnits()
    );
  }
}