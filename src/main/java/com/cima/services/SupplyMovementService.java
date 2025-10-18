package com.cima.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.SupplyMovement.CreateSupplyMovementDTO;
import com.cima.errors.InvalidReferenceException;
import com.cima.models.Employee;
import com.cima.models.Supply;
import com.cima.models.SupplyMovement;
import com.cima.models.Totem;
import com.cima.repositories.SupplyMovementRepository;

@Service
public class SupplyMovementService {
  @Autowired
  private SupplyMovementRepository repository;

  @Autowired
  private TotemService totemService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private SupplyService supplyService;

  @Transactional(readOnly = true)
  public List<SupplyMovement> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public SupplyMovement findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Alteração não encontrada para o ID: " + id))
    ;
  }

  @Transactional
  public SupplyMovement create(CreateSupplyMovementDTO movement) {
    SupplyMovement supplyMovement = new SupplyMovement();
    Totem totem = new Totem();
    Employee employee = new Employee();
    Supply supply = new Supply();

    try {
      totem = totemService.findById(movement.totemID());
      employee = employeeService.findById(movement.employeeID());
      supply = supplyService.findById(movement.supplyID());
    } catch (EntityNotFoundException exception) {
      throw new InvalidReferenceException(exception.getMessage());
    }

    supplyMovement.setDate(LocalDateTime.now());
    supplyMovement.setTotem(totem);
    supplyMovement.setEmployee(employee);
    supplyMovement.setSupply(supply);
    supplyMovement.setQuantity(movement.quantity());

    return repository.save(supplyMovement);
  }

  @Transactional
  public SupplyMovement update(Integer id, SupplyMovement movementDetails) {
    SupplyMovement movement = findById(id);

    movement.setDate(movementDetails.getDate());
    movement.setTotem(movementDetails.getTotem());
    movement.setEmployee(movementDetails.getEmployee());
    movement.setSupply(movementDetails.getSupply());
    movement.setQuantity(movementDetails.getQuantity());

    return repository.save(movement);
  }

  @Transactional
  public void delete(Integer id) {
    SupplyMovement movement = findById(id);
    repository.delete(movement);
  }
}