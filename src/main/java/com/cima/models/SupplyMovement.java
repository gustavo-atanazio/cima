package com.cima.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "supply_movement")
@NoArgsConstructor @AllArgsConstructor @Getter
public class SupplyMovement {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "date", nullable = false) @Setter
  private LocalDateTime date;

  @ManyToOne @JoinColumn(name = "totem_id", nullable = false) @Setter
  private Totem totem;

  @ManyToOne @JoinColumn(name = "employee_id", nullable = false) @Setter
  private Employee employee;

  @ManyToOne @JoinColumn(name = "supply_id", nullable = false) @Setter
  private Supply supply;

  @Column(nullable = false) @Setter
  private Integer quantity;
}