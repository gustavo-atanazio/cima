package com.cima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cima.models.Totem;
import com.cima.repositories.TotemRepository;

@Service
public class TotemService {
  @Autowired
  private TotemRepository repository;

  public List<Totem> findAll() { return repository.findAll(); }

  public Totem findById(Integer id) {
    return repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Totem não encontrado para o ID: " + id))
    ;
  }

  public Totem create(Totem totem) { return repository.save(totem); }

  public Totem update(Integer id, Totem totemDetails) {
    Totem totem = findById(id);

    totem.setSupplyWarehouse(totemDetails.getSupplyWarehouse());

    return repository.save(totem);
  }

  public void delete(Integer id) {
    Totem totem = findById(id);
    repository.delete(totem);
  }
}