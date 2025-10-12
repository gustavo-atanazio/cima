package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.models.SupplyMovement;
import com.cima.repositories.SupplyMovementRepository;

@Service
public class SupplyMovementService {
  @Autowired
  private SupplyMovementRepository repository;

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
  public SupplyMovement create(SupplyMovement movement) { return repository.save(movement); }

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