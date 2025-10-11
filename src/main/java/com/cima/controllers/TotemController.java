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

import com.cima.DTO.Totem.CreateTotemDTO;
import com.cima.DTO.Totem.TotemDTO;
import com.cima.DTO.Totem.UpdateTotemDTO;
import com.cima.models.Totem;
import com.cima.services.TotemService;

@RestController @RequestMapping("/totems")
public class TotemController {
  @Autowired
  private TotemService service;

  @GetMapping
  public ResponseEntity<List<TotemDTO>> getAll() {
    List<Totem> totems = service.findAll();

    return ResponseEntity.ok()
      .body(totems.stream()
        .map(TotemDTO::new)
        .toList()
      )
    ;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TotemDTO> getById(@PathVariable Integer id) {
    Totem totem = service.findById(id);

    return ResponseEntity.ok(new TotemDTO(totem));
  }

  @PostMapping
  public ResponseEntity<TotemDTO> create(@RequestBody CreateTotemDTO totem) {
    Totem savedTotem = service.create(totem);

    return ResponseEntity.ok(new TotemDTO(savedTotem));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Integer id,
    @RequestBody UpdateTotemDTO totemDetails
  ) {
    service.update(id, totemDetails);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}