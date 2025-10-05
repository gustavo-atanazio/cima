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

import com.cima.DTO.SupplyWarehouse.CreateSupplyWarehouseDTO;
import com.cima.DTO.SupplyWarehouse.SupplyWarehouseDTO;
import com.cima.models.SupplyWarehouse;
import com.cima.services.SupplyWarehouseService;

@RestController @RequestMapping("/supply-warehouses")
public class SupplyWarehouseController {
  @Autowired
  private SupplyWarehouseService service;

  @GetMapping
  public ResponseEntity<List<SupplyWarehouseDTO>> getAll() {
    List<SupplyWarehouse> supplyWarehouses = service.findAll();

    return ResponseEntity.ok()
      .body(supplyWarehouses.stream()
        .map(SupplyWarehouseDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplyWarehouseDTO> getById(@PathVariable Integer id) {
    SupplyWarehouse supplyWarehouse = service.findById(id);

    return ResponseEntity.ok(new SupplyWarehouseDTO(supplyWarehouse));
  }

  @PostMapping
  public ResponseEntity<SupplyWarehouseDTO> create(@RequestBody CreateSupplyWarehouseDTO supplyWarehouse) {
    SupplyWarehouse savedSupplyWarehouse = service.create(supplyWarehouse);

    return ResponseEntity.ok(new SupplyWarehouseDTO(savedSupplyWarehouse));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody SupplyWarehouse supplyWarehouseDetails
  ) {
    service.update(id, supplyWarehouseDetails);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}