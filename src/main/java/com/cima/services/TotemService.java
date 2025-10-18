package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.Totem.CreateTotemDTO;
import com.cima.DTO.Totem.UpdateTotemDTO;
import com.cima.errors.InvalidReferenceException;
import com.cima.models.SupplyWarehouse;
import com.cima.models.Totem;
import com.cima.repositories.TotemRepository;

@Service
public class TotemService {
  @Autowired
  private TotemRepository repository;

  @Autowired
  private SupplyWarehouseService supplyWarehouseService;

  @Transactional(readOnly = true)
  public List<Totem> findAll() { return repository.findAll(); }

  @Transactional(readOnly = true)
  public Totem findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Totem não encontrado para o ID: " + id))
    ;
  }

  @Transactional
  public Totem create(CreateTotemDTO totem) {
    SupplyWarehouse supplyWarehouse = supplyWarehouseService.findById(totem.supplyWarehouseID());
    boolean warehouseAlreadyUsed = repository.existsBySupplyWarehouse(supplyWarehouse);
    Totem newTotem = new Totem();

    newTotem.setSupplyWarehouse(supplyWarehouse, warehouseAlreadyUsed);

    return repository.save(newTotem);
  }

  @Transactional
  public Totem update(Integer id, UpdateTotemDTO totemDetails) throws InvalidReferenceException {
    Totem totem = findById(id);
    SupplyWarehouse supplyWarehouse;

    try {
      supplyWarehouse = supplyWarehouseService.findById(totemDetails.supplyWarehouseID());
    } catch (EntityNotFoundException exception) {
      throw new InvalidReferenceException("O almoxarifado informado não existe.");
    }

    boolean warehouseAlreadyUsed = repository.existsBySupplyWarehouse(supplyWarehouse);
    totem.setSupplyWarehouse(supplyWarehouse, warehouseAlreadyUsed);

    return repository.save(totem);
  }

  @Transactional
  public void delete(Integer id) {
    Totem totem = findById(id);
    repository.delete(totem);
  }
}