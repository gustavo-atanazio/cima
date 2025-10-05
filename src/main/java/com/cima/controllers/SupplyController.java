package com.cima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cima.DTO.SupplyDTO;
import com.cima.models.Supply;
import com.cima.repositories.SupplyRepository;

@RestController @RequestMapping("/supplies")
public class SupplyController {
  @Autowired
  private SupplyRepository repository;

  @GetMapping
  public ResponseEntity<List<SupplyDTO>> getAll() {
    List<Supply> supplies = repository.findAll();

    return ResponseEntity.ok()
      .body(supplies.stream()
        .map(SupplyDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplyDTO> getById(@PathVariable Integer id) {
    Supply supply = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Insumo não encontrado para o ID: " + id))
    ;

    return ResponseEntity.ok(new SupplyDTO(supply));
  }

  @PostMapping
  public ResponseEntity<SupplyDTO> create(@RequestBody Supply supply) {
    Supply savedSupply = repository.save(supply);
    return ResponseEntity.ok(new SupplyDTO(savedSupply));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody Supply supplyDetails
  ) {
    Supply supply = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Insumo não encontrado para o ID: " + id))
    ;

    supply.setName(supplyDetails.getName());
    supply.setLotNumber(supplyDetails.getLotNumber());
    supply.setQuantity(supplyDetails.getQuantity());
    supply.setSupplyWarehouses(supplyDetails.getSupplyWarehouses());
    supply.setSupplyMovements(supplyDetails.getSupplyMovements());

    repository.save(supply);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    Supply supply = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Insumo não encontrado para o ID: " + id))
    ;

    repository.delete(supply);
    return ResponseEntity.noContent().build();
  }
}