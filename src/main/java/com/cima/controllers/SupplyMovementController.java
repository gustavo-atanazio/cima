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

import com.cima.DTO.SupplyMovementDTO;
import com.cima.models.SupplyMovement;
import com.cima.repositories.SupplyMovementRepository;

@RestController @RequestMapping("/supply-movements")
public class SupplyMovementController {
  @Autowired
  private SupplyMovementRepository repository;

  @GetMapping
  public ResponseEntity<List<SupplyMovementDTO>> getAll() {
    List<SupplyMovement> movements = repository.findAll();

    return ResponseEntity.ok()
      .body(movements.stream()
        .map(SupplyMovementDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplyMovementDTO> getById(@PathVariable Integer id) {
    SupplyMovement movement = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Movimentação de insumo não encontrada para o ID: " + id))
    ;

    return ResponseEntity.ok(new SupplyMovementDTO(movement));
  }

  @PostMapping
  public ResponseEntity<SupplyMovementDTO> create(@RequestBody SupplyMovement movement) {
    SupplyMovement savedMovement = repository.save(movement);
    return ResponseEntity.ok(new SupplyMovementDTO(savedMovement));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody SupplyMovement movementDetails
  ) {
    SupplyMovement movement = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Movimentação de insumo não encontrada para o ID: " + id))
    ;

    movement.setDate(movementDetails.getDate());
    movement.setTotem(movementDetails.getTotem());
    movement.setEmployee(movementDetails.getEmployee());
    movement.setSupply(movementDetails.getSupply());
    movement.setQuantity(movementDetails.getQuantity());

    repository.save(movement);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    SupplyMovement supply = repository
      .findById(id)
      .orElseThrow(() -> new RuntimeException("Movimentação de insumo não encontrada para o ID: " + id))
    ;

    repository.delete(supply);
    return ResponseEntity.noContent().build();
  }
}