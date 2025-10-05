package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cima.models.Unit;
import com.cima.repositories.UnitRepository;

@Service
public class UnitService {
  @Autowired
  private UnitRepository repository;

  public List<Unit> findAll() { return repository.findAll(); }

  public Unit findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Unidade não encontrada para o ID: " + id))
    ;
  }

  public Unit create(Unit unit) { return repository.save(unit); }

  public Unit update(Integer id, Unit unitDetails) {
    Unit unit = findById(id);

    unit.setCEP(unitDetails.getCEP());
    unit.setNumber(unitDetails.getNumber());
    unit.setEmployees(unitDetails.getEmployees());

    return repository.save(unit);
  }

  public void delete(Integer id) {
    Unit unit = findById(id);
    repository.delete(unit);
  }
}