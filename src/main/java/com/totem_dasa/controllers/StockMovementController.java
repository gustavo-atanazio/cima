package com.totem_dasa.controllers;

import java.util.List;
import java.util.Optional;

import com.totem_dasa.DAO.StockMovementDAO;
import com.totem_dasa.models.StockMovement;

public class StockMovementController {
  private final StockMovementDAO StockMovementDAO = new StockMovementDAO();

  public List<StockMovement> getAll() {
    return StockMovementDAO.findAll();
  }

  public Optional<StockMovement> getById(int id) {
    return StockMovementDAO.findById(id);
  }

  public void post(StockMovement movement) {
    StockMovementDAO.create(movement);
  }

  public void delete(int id) {
    StockMovementDAO.delete(id);
  }
}