package com.cima.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cima.DTO.Employee.CreateEmployeeDTO;
import com.cima.DTO.Employee.EmployeeDTO;
import com.cima.DTO.Employee.UpdateEmployeeDTO;
import com.cima.models.Employee;
import com.cima.services.EmployeeService;

@RestController @RequestMapping("/employees")
public class EmployeeController {
  @Autowired
  private EmployeeService service;

  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getAll() {
    List<Employee> employees = service.findAll();

    return ResponseEntity.ok()
      .body(employees.stream()
        .map(EmployeeDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDTO> getById(@PathVariable Integer id) {
    Employee employee = service.findById(id);

    return ResponseEntity.ok(new EmployeeDTO(employee));
  }

  @PostMapping
  public ResponseEntity<EmployeeDTO> create(@RequestBody CreateEmployeeDTO employee) {
    Employee savedEmployee = service.create(employee);

    URI location = URI.create(String.format("/employees/%d", savedEmployee.getId()));

    return ResponseEntity.created(location).body(new EmployeeDTO(savedEmployee));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody UpdateEmployeeDTO employeeDetails
  ) {
    service.update(id, employeeDetails);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}