package com.cima.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.cima.errors.BusinessRuleException;

@Entity @Table(name = "supply_warehouse")
@NoArgsConstructor @AllArgsConstructor @Getter
public class SupplyWarehouse {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "unit_id", foreignKey = @ForeignKey(name = "fk_supply_warehouse_unit"))
  private Unit unit;

  public void setUnit(Unit newUnit) {
    if (this.unit != null && this.unit.getId().equals(newUnit.getId())) {
      throw new BusinessRuleException("O almoxarifado já está vinculado a esta unidade.");
    }

    this.unit = newUnit;
  }
}