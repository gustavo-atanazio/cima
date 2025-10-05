package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cima.models.SupplyWarehouse;
import com.cima.repositories.SupplyWarehouseRepository;

@Service
public class SupplyWarehouseService {
  @Autowired
  private SupplyWarehouseRepository repository;

  public List<SupplyWarehouse> findAll() { return repository.findAll(); }

  public SupplyWarehouse findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Almoxarifado não encontrado para o ID: " + id))
    ;
  }

  public SupplyWarehouse create(SupplyWarehouse supplyWarehouse) { return repository.save(supplyWarehouse); }

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