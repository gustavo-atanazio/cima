package com.cima.DTO;

import java.util.List;

import com.cima.models.Employee;
import com.cima.models.Unit;

public record UnitDTO(Integer id, String CEP, Integer number, List<Employee> employees) {
  public UnitDTO(Unit unit) {
    this(
      unit.getId(),
      unit.getCEP(),
      unit.getNumber(),
      unit.getEmployees()
    );
  } 
}