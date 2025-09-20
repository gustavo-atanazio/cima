package com.totem_dasa.controllers;

import java.util.List;
import java.util.Optional;

import com.totem_dasa.DAO.SupplyMovementDAO;
import com.totem_dasa.models.SupplyMovement;

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