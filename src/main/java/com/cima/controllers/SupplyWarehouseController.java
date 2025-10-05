package com.cima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cima.DTO.SupplyWarehouseDTO;
import com.cima.models.SupplyWarehouse;
import com.cima.repositories.SupplyWarehouseRepository;

@RestController @RequestMapping("/supply-warehouses")
public class SupplyWarehouseController {
  @Autowired
  private SupplyWarehouseRepository repository;

  @GetMapping
  public ResponseEntity<List<SupplyWarehouseDTO>> getAll() {
    List<SupplyWarehouse> supplyWarehouses = repository.findAll();

    return ResponseEntity.ok()
      .body(supplyWarehouses.stream()
        .map(SupplyWarehouseDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplyWarehouseDTO> getById(Integer id) {
    SupplyWarehouse supplyWarehouse = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Almoxarifado não encontrado para o ID: " + id))
    ;

    return ResponseEntity.ok(new SupplyWarehouseDTO(supplyWarehouse));
  }

  @PostMapping
  public ResponseEntity<SupplyWarehouseDTO> create(SupplyWarehouse supplyWarehouse) {
    SupplyWarehouse savedSupplyWarehouse = repository.save(supplyWarehouse);
    return ResponseEntity.ok(new SupplyWarehouseDTO(savedSupplyWarehouse));
  }

  @PutMapping("/{id}")
  public ResponseEntity<SupplyWarehouseDTO> update(Integer id, SupplyWarehouse supplyWarehouseDetails) {
    SupplyWarehouse supplyWarehouse = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Almoxarifado não encontrado para o ID: " + id))
    ;

    supplyWarehouse.setUnit(supplyWarehouseDetails.getUnit());

    repository.save(supplyWarehouse);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SupplyWarehouseDTO> delete(Integer id) {
    SupplyWarehouse supplyWarehouse = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Almoxarifado não encontrado para o ID: " + id))
    ;

    repository.delete(supplyWarehouse);
    return ResponseEntity.noContent().build();
  }
}