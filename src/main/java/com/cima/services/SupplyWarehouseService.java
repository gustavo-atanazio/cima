package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.SupplyWarehouse.CreateSupplyWarehouseDTO;
import com.cima.models.SupplyWarehouse;
import com.cima.models.Unit;
import com.cima.repositories.SupplyWarehouseRepository;

@Service
public class SupplyWarehouseService {
  @Autowired
  private SupplyWarehouseRepository repository;

  @Autowired
  private UnitService unitService;

  public List<SupplyWarehouse> findAll() { return repository.findAll(); }

  public SupplyWarehouse findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Almoxarifado não encontrado para o ID: " + id))
    ;
  }

  public SupplyWarehouse create(CreateSupplyWarehouseDTO supplyWarehouse) {
    Unit unit = unitService.findById(supplyWarehouse.unitID());
    SupplyWarehouse newSupplyWarehouse = new SupplyWarehouse();

    newSupplyWarehouse.setUnit(unit);

    return repository.save(newSupplyWarehouse);
  }

  public SupplyWarehouse update(Integer id, SupplyWarehouse supplyWarehouseDetails) {
    SupplyWarehouse supplyWarehouse = findById(id);

    supplyWarehouse.setUnit(supplyWarehouseDetails.getUnit());

    return repository.save(supplyWarehouse);
  }

  public void delete(Integer id) {
    SupplyWarehouse supplyWarehouse = findById(id);
    repository.delete(supplyWarehouse);
  }
}