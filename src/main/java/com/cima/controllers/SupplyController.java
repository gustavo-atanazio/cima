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
import com.cima.services.SupplyService;

@RestController @RequestMapping("/supplies")
public class SupplyController {
  @Autowired
  private SupplyService service;

  @GetMapping
  public ResponseEntity<List<SupplyDTO>> getAll() {
    List<Supply> supplies = service.findAll();

    return ResponseEntity.ok()
      .body(supplies.stream()
        .map(SupplyDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplyDTO> getById(@PathVariable Integer id) {
    Supply supply = service.findById(id);

    return ResponseEntity.ok(new SupplyDTO(supply));
  }

  @PostMapping
  public ResponseEntity<SupplyDTO> create(@RequestBody Supply supply) {
    Supply savedSupply = service.create(supply);

    return ResponseEntity.ok(new SupplyDTO(savedSupply));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody Supply supplyDetails
  ) {
    service.update(id, supplyDetails);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}