package com.cima.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "unit")
@NoArgsConstructor @AllArgsConstructor @Getter
public class Unit {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false) @Setter
  private String CEP;

  @Column(nullable = false) @Setter
  private Integer number;

  @ManyToMany
  @JoinTable(
    name = "unit_employee",
    joinColumns = @JoinColumn(name = "unit_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id")
  )
  @Setter
  private List<Employee> employees;
}