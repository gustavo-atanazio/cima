package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cima.models.Supply;
import com.cima.repositories.SupplyRepository;

@Service
public class SupplyService {
  @Autowired
  private SupplyRepository repository;

  public List<Supply> findAll() { return repository.findAll(); }

  public Supply findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Insumo não encontrado para o ID: " + id))
    ;
  }

  public Supply create(Supply supply) { return repository.save(supply); }

  public Supply update(Integer id, Supply supplyDetails) {
    Supply supply = findById(id);

    supply.setName(supplyDetails.getName());
    supply.setLotNumber(supplyDetails.getLotNumber());
    supply.setQuantity(supplyDetails.getQuantity());
    supply.setSupplyWarehouses(supplyDetails.getSupplyWarehouses());
    supply.setSupplyMovements(supplyDetails.getSupplyMovements());

    return repository.save(supply);
  }

  public void delete(Integer id) {
    Supply supply = findById(id);
    repository.delete(supply);
  }
}