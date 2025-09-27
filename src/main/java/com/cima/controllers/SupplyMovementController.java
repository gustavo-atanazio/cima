package com.cima.controllers;

import java.util.List;
import java.util.Optional;

import com.cima.DAO.SupplyMovementDAO;
import com.cima.models.SupplyMovement;

public class SupplyMovementController {
  private final SupplyMovementDAO supplyMovementDAO = new SupplyMovementDAO();

  public List<SupplyMovement> getAll() {
    return supplyMovementDAO.findAll();
  }

  public Optional<SupplyMovement> getById(int id) {
    return supplyMovementDAO.findById(id);
  }

  public SupplyMovement post(SupplyMovement totem) {
    SupplyMovement createdTotem = supplyMovementDAO.create(totem);
    return createdTotem;
  }

  public void delete(int id) {
    supplyMovementDAO.delete(id);
  }
}