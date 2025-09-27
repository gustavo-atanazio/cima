package com.cima.controllers;

import java.util.List;
import java.util.Optional;

import com.cima.DAO.StockMovementDAO;
import com.cima.models.StockMovement;

public class StockMovementController {
  private final StockMovementDAO StockMovementDAO = new StockMovementDAO();

  public List<StockMovement> getAll() {
    return StockMovementDAO.findAll();
  }

  public Optional<StockMovement> getById(int id) {
    return StockMovementDAO.findById(id);
  }

  public StockMovement post(StockMovement movement) {
    StockMovement createdStockMovement = StockMovementDAO.create(movement);
    return createdStockMovement;
  }

  public void delete(int id) {
    StockMovementDAO.delete(id);
  }
}