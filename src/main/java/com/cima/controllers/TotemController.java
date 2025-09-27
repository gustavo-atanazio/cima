package com.cima.controllers;

import java.util.List;
import java.util.Optional;

import com.cima.DAO.TotemDAO;
import com.cima.models.Totem;

public class TotemController {
  private final TotemDAO totemDAO = new TotemDAO();

  public List<Totem> getAll() {
    return totemDAO.findAll();
  }

  public Optional<Totem> getById(int id) {
    return totemDAO.findById(id);
  }

  public Totem post(Totem totem) {
    Totem createdTotem = totemDAO.create(totem);
    return createdTotem;
  }

  public void delete(int id) {
    totemDAO.delete(id);
  }
}