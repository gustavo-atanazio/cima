package com.cima.controllers;

import java.net.URI;
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

import com.cima.DTO.Unit.CreateUnitDTO;
import com.cima.DTO.Unit.UnitDTO;
import com.cima.DTO.Unit.UpdateUnitDTO;
import com.cima.models.Unit;
import com.cima.services.UnitService;

@RestController @RequestMapping("/units")
public class UnitController {
  @Autowired
  private UnitService service;

  @GetMapping
  public ResponseEntity<List<UnitDTO>> getAll() {
    List<Unit> units = service.findAll();

    return ResponseEntity.ok()
      .body(units.stream()
        .map(UnitDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UnitDTO> getById(@PathVariable Integer id) {
    Unit unit = service.findById(id);

    return ResponseEntity.ok(new UnitDTO(unit));
  }

  @PostMapping
  public ResponseEntity<UnitDTO> create(@RequestBody CreateUnitDTO unit) {
    Unit savedUnit = service.create(unit);

    URI location = URI.create(String.format("/units/%d", savedUnit.getId()));

    return ResponseEntity.created(location).body(new UnitDTO(savedUnit));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody UpdateUnitDTO unitDetails
  ) {
    service.update(id, unitDetails);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}