package com.cima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.Unit.CreateUnitDTO;
import com.cima.models.Unit;
import com.cima.repositories.UnitRepository;

@Service
public class UnitService {
  @Autowired
  private UnitRepository repository;

  @Transactional(readOnly = true)
  public List<Unit> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public Unit findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada para o ID: " + id))
    ;
  }

  @Transactional
  public Unit create(CreateUnitDTO unit) {
    Unit newUnit = new Unit();

    newUnit.setCEP(unit.CEP());
    newUnit.setNumber(unit.number());
    newUnit.setEmployees(new ArrayList<>());

    return repository.save(newUnit);
  }

  @Transactional
  public Unit update(Integer id, Unit unitDetails) {
    Unit unit = findById(id);

    unit.setCEP(unitDetails.getCEP());
    unit.setNumber(unitDetails.getNumber());
    unit.setEmployees(unitDetails.getEmployees());

    return repository.save(unit);
  }

  @Transactional
  public void delete(Integer id) {
    Unit unit = findById(id);
    repository.delete(unit);
  }
}