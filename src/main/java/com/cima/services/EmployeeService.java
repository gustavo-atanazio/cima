package com.cima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.Employee.CreateEmployeeDTO;
import com.cima.DTO.Employee.UpdateEmployeeDTO;
import com.cima.errors.BusinessRuleException;
import com.cima.errors.InvalidReferenceException;
import com.cima.models.Employee;
import com.cima.models.Unit;
import com.cima.repositories.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository repository;

  @Autowired
  private UnitService unitService;

  @Transactional(readOnly = true)
  public List<Employee> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public Employee findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado para o ID: " + id))
    ;
  }

  @Transactional
  public Employee create(CreateEmployeeDTO employee) {
    if (employee.accessLevel() < 1 || employee.accessLevel() > 3) {
      throw new BusinessRuleException("Nível de acesso inválido. Deve ser entre 1 e 3.");
    }

    Employee newEmployee = new Employee();
    newEmployee.setUnits(new ArrayList<>());

    try {
      employee.unitIDs().forEach(id -> {
        Unit unit = unitService.findById(id);
        newEmployee.getUnits().add(unit);
      });
    } catch (EntityNotFoundException exception) {
      throw new InvalidReferenceException("Unidade não encontrada para um dos IDs fornecidos.");
    }

    newEmployee.setName(employee.name());
    newEmployee.setAccessLevel(employee.accessLevel());

    return repository.save(newEmployee);
  }

  @Transactional
  public Employee update(Integer id, UpdateEmployeeDTO employeeDetails) {
    Employee employee;

    try { employee = findById(id); }
    catch (EntityNotFoundException exception) {
      throw new InvalidReferenceException("O funcionário informado não existe.");
    }

    if (employeeDetails.accessLevel() < 1 || employeeDetails.accessLevel() > 3) {
      throw new BusinessRuleException("Nível de acesso inválido. Deve ser entre 1 e 3.");
    }

    employee.setUnits(new ArrayList<>());

    try {
      employeeDetails.unitIDs().forEach(unitID -> {
        Unit unit = unitService.findById(unitID);
        employee.getUnits().add(unit);
      });
    } catch (EntityNotFoundException exception) {
      throw new InvalidReferenceException("Unidade não encontrada para um dos IDs fornecidos.");
    }

    employee.setName(employeeDetails.name());
    employee.setAccessLevel(employeeDetails.accessLevel());

    return repository.save(employee);
  }

  @Transactional
  public void delete(Integer id) {
    Employee employee = findById(id);
    repository.delete(employee);
  }
}