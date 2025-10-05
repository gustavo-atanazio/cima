package com.cima.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "supply")
@NoArgsConstructor @AllArgsConstructor @Getter
public class Supply {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 250) @Setter
  private String name;

  @Column(name = "lot_number", nullable = false) @Setter
  private Integer lotNumber;

  @Column(nullable = false) @Setter
  private Integer quantity;

  @ManyToMany(mappedBy = "supplies") @Setter
  private List<SupplyWarehouse> supplyWarehouses;

  @OneToMany(mappedBy = "supply") @Setter
  private List<SupplyMovement> supplyMovements;
}