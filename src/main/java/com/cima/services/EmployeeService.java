package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cima.models.Employee;
import com.cima.repositories.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository repository;

  public List<Employee> findAll() { return repository.findAll(); }

  public Employee findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Funcionário não encontrado para o ID: " + id))
    ;
  }

  public Employee create(Employee employee) { return repository.save(employee); }

  public Employee update(Integer id, Employee employeeDetails) {
    Employee employee = findById(id);

    employee.setName(employeeDetails.getName());
    employee.setAccessLevel(employeeDetails.getAccessLevel());
    employee.setUnits(employeeDetails.getUnits());

    return repository.save(employee);
  }

  public void delete(Integer id) {
    Employee employee = findById(id);
    repository.delete(employee);
  }
}