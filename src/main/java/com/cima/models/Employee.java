package com.cima.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "employee")
@NoArgsConstructor @AllArgsConstructor @Getter
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false) @Setter
  private String name;

  @Column(name = "access_level", nullable = false) @Setter
  private Integer accessLevel;

  @ManyToMany(mappedBy = "employees") @Setter
  private List<Unit> units;
}