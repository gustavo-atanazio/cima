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

import com.cima.DTO.UnitDTO;
import com.cima.models.Unit;
import com.cima.repositories.UnitRepository;

@RestController @RequestMapping("/units")
public class UnitController {
  @Autowired
  private UnitRepository repository;

  @GetMapping
  public ResponseEntity<List<UnitDTO>> getAll() {
    List<Unit> units = repository.findAll();

    return ResponseEntity.ok()
      .body(units.stream()
        .map(UnitDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UnitDTO> getById(@PathVariable Integer id) {
    Unit unit = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Unidade não encontrada para o ID: " + id))
    ;

    return ResponseEntity.ok(new UnitDTO(unit));
  }

  @PostMapping
  public ResponseEntity<UnitDTO> create(@RequestBody Unit unit) {
    Unit savedUnit = repository.save(unit);
    return ResponseEntity.ok(new UnitDTO(savedUnit));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody Unit unitDetails
  ) {
    Unit unit = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Unidade não encontrada para o ID: " + id))
    ;

    unit.setCEP(unitDetails.getCEP());
    unit.setNumber(unitDetails.getNumber());
    unit.setEmployees(unitDetails.getEmployees());

    repository.save(unit);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    Unit unit = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Unidade não encontrada para o ID: " + id))
    ;

    repository.delete(unit);
    return ResponseEntity.noContent().build();
  }
}