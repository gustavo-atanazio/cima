package com.cima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cima.DTO.EmployeeDTO;
import com.cima.models.Employee;
import com.cima.repositories.EmployeeRepository;

@RestController @RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  private EmployeeRepository repository;

  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getAll() {
    List<Employee> employees = repository.findAll();

    return ResponseEntity.ok()
      .body(employees.stream()
        .map(EmployeeDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDTO> getById(Integer id) {
    Employee employee = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para o ID: " + id))
    ;

    return ResponseEntity.ok(new EmployeeDTO(employee));
  }

  @PostMapping
  public ResponseEntity<EmployeeDTO> create(Employee employee) {
    Employee savedEmployee = repository.save(employee);
    return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDTO> update(Integer id, Employee employeeDetails) {
    Employee employee = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para o ID: " + id))
    ;

    employee.setName(employeeDetails.getName());
    employee.setAccessLevel(employeeDetails.getAccessLevel());
    employee.setUnits(employeeDetails.getUnits());

    repository.save(employee);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EmployeeDTO> delete(Integer id) {
    Employee employee = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para o ID: " + id))
    ;

    repository.delete(employee);
    return ResponseEntity.noContent().build();
  }
}