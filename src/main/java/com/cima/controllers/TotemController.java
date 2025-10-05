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

import com.cima.DTO.TotemDTO;
import com.cima.models.Totem;
import com.cima.repositories.TotemRepository;

@RestController @RequestMapping("/totems")
public class TotemController {
  @Autowired
  private TotemRepository repository;

  @GetMapping
  public ResponseEntity<List<TotemDTO>> getAll() {
    List<Totem> totems = repository.findAll();

    return ResponseEntity.ok()
      .body(totems.stream()
        .map(TotemDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TotemDTO> getById(@PathVariable Integer id) {
    Totem totem = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Totem não encontrado para o ID: " + id))
    ;

    return ResponseEntity.ok(new TotemDTO(totem));
  }

  @PostMapping
  public ResponseEntity<TotemDTO> create(@RequestBody Totem totem) {
    Totem savedTotem = repository.save(totem);
    return ResponseEntity.ok(new TotemDTO(savedTotem));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody Totem totemDetails
  ) {
    Totem totem = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Totem não encontrado para o ID: " + id))
    ;

    totem.setSupplyWarehouse(totemDetails.getSupplyWarehouse());

    repository.save(totem);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    Totem totem = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Totem não encontrado para o ID: " + id))
    ;

    repository.delete(totem);
    return ResponseEntity.noContent().build();
  }
}