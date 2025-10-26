package com.cima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.Supply.CreateSupplyDTO;
import com.cima.DTO.Supply.UpdateSupplyDTO;
import com.cima.errors.InvalidReferenceException;
import com.cima.models.Supply;
import com.cima.models.SupplyWarehouse;
import com.cima.repositories.SupplyRepository;

@Service
public class SupplyService {
  @Autowired
  private SupplyRepository repository;

  @Autowired
  private SupplyWarehouseService supplyWarehouseService;

  @Transactional(readOnly = true)
  public List<Supply> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public Supply findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Insumo não encontrado para o ID: " + id))
    ;
  }

  @Transactional
  public Supply create(CreateSupplyDTO supply) {
    Supply newSupply = new Supply();

    newSupply.setName(supply.name());
    newSupply.setLotNumber(supply.lotNumber());
    newSupply.setQuantity(0);
    newSupply.setSupplyWarehouses(new ArrayList<>());
    newSupply.setSupplyMovements(new ArrayList<>());

    return repository.save(newSupply);
  }

  @Transactional
  public Supply update(Integer id, UpdateSupplyDTO supplyDetails) {
    Supply supply = findById(id);
    List<SupplyWarehouse> updatedSupplyWarehouses = new ArrayList<>();

    supply.setName(supplyDetails.name());
    supply.setLotNumber(supplyDetails.lotNumber());
    supply.setQuantity(supplyDetails.quantity());

    supplyDetails.supplyWarehouseIDs().forEach(warehouseID -> {
      try {
        SupplyWarehouse supplyWarehouse = supplyWarehouseService.findById(warehouseID);
        updatedSupplyWarehouses.add(supplyWarehouse);
      } catch (EntityNotFoundException exception) {
        throw new InvalidReferenceException(exception.getMessage());
      }
    });

    supply.setSupplyWarehouses(updatedSupplyWarehouses);

    return repository.save(supply);
  }

  @Transactional
  public void delete(Integer id) {
    Supply supply = findById(id);
    repository.delete(supply);
  }
}