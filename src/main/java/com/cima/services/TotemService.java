package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.cima.DTO.Totem.CreateTotemDTO;
import com.cima.DTO.Totem.UpdateTotemDTO;
import com.cima.errors.BusinessRuleException;
import com.cima.models.SupplyWarehouse;
import com.cima.models.Totem;
import com.cima.repositories.TotemRepository;

@Service
public class TotemService {
  @Autowired
  private TotemRepository repository;

  @Autowired
  private SupplyWarehouseService supplyWarehouseService;

  public List<Totem> findAll() { return repository.findAll(); }

  public Totem findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Totem não encontrado para o ID: " + id))
    ;
  }

  public Totem create(CreateTotemDTO totem) {
    SupplyWarehouse supplyWarehouse = supplyWarehouseService.findById(totem.supplyWarehouseID());
    Totem newTotem = new Totem();

    newTotem.setSupplyWarehouse(supplyWarehouse);

    return repository.save(newTotem);
  }

  public Totem update(Integer id, UpdateTotemDTO totemDetails) {
    Totem totem = findById(id);
    SupplyWarehouse supplyWarehouse = supplyWarehouseService.findById(totemDetails.supplyWarehouseID());

    boolean isSameWarehouse = totem.getSupplyWarehouse().getId().equals(supplyWarehouse.getId());
    if (isSameWarehouse) throw new BusinessRuleException("O totem já está vinculado a este almoxarifado.");

    boolean warehouseAlreadyUsed = repository.existsBySupplyWarehouse(supplyWarehouse);
    if (warehouseAlreadyUsed) throw new BusinessRuleException("Não é possível atribuir o totem a um almoxarifado que já possui outro totem.");

    totem.setSupplyWarehouse(supplyWarehouse);

    return repository.save(totem);
  }

  public void delete(Integer id) {
    Totem totem = findById(id);
    repository.delete(totem);
  }
}