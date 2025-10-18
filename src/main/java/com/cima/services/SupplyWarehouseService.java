package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.SupplyWarehouse.CreateSupplyWarehouseDTO;
import com.cima.DTO.SupplyWarehouse.UpdateSupplyWarehouseDTO;
import com.cima.models.SupplyWarehouse;
import com.cima.models.Unit;
import com.cima.repositories.SupplyWarehouseRepository;

@Service
public class SupplyWarehouseService {
  @Autowired
  private SupplyWarehouseRepository repository;

  @Autowired
  private UnitService unitService;

  @Transactional(readOnly = true)
  public List<SupplyWarehouse> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public SupplyWarehouse findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Almoxarifado não encontrado para o ID: " + id))
    ;
  }

  @Transactional
  public SupplyWarehouse create(CreateSupplyWarehouseDTO supplyWarehouse) {
    Unit unit = unitService.findById(supplyWarehouse.unitID());
    SupplyWarehouse newSupplyWarehouse = new SupplyWarehouse();

    newSupplyWarehouse.setUnit(unit);

    return repository.save(newSupplyWarehouse);
  }

  @Transactional
  public SupplyWarehouse update(Integer id, UpdateSupplyWarehouseDTO supplyWarehouseDetails) {
    SupplyWarehouse supplyWarehouse = findById(id);
    Unit unit = unitService.findById(supplyWarehouseDetails.unitID());

    supplyWarehouse.setUnit(unit);

    return repository.save(supplyWarehouse);
  }

  @Transactional
  public void delete(Integer id) {
    SupplyWarehouse supplyWarehouse = findById(id);
    repository.delete(supplyWarehouse);
  }
}