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

import com.cima.errors.BusinessRuleException;

@Entity @Table(name = "employee")
@NoArgsConstructor @AllArgsConstructor @Getter
public class Employee {
  public static final byte MIN_ACCESS_LEVEL = 1;
  public static final byte MAX_ACCESS_LEVEL = 3;

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false) @Setter
  private String name;

  @Column(name = "access_level", nullable = false)
  private Integer accessLevel;

  @ManyToMany(mappedBy = "employees") @Setter
  private List<Unit> units;

  public void setAccessLevel(Integer accessLevel) {
    if (accessLevel < MIN_ACCESS_LEVEL || accessLevel > MAX_ACCESS_LEVEL) {
      throw new BusinessRuleException("Nível de acesso inválido. Deve ser entre 1 e 3.");
    }
    
    this.accessLevel = accessLevel;
  }
}