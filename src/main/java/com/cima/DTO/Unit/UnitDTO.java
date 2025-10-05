package com.cima.DTO.Unit;

import java.util.List;

import com.cima.models.Unit;

public record UnitDTO(Integer id, String CEP, Integer number, List<Integer> employeeIDs) {
  public UnitDTO(Unit unit) {
    this(
      unit.getId(),
      unit.getCEP(),
      unit.getNumber(),
      unit.getEmployees().stream().map(employee -> employee.getId()).toList()
    );
  } 
}