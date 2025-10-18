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

@Entity @Table(name = "totem")
@NoArgsConstructor @AllArgsConstructor @Getter
public class Totem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "supply_warehouse_id", foreignKey = @ForeignKey(name = "fk_totem_supply_warehouse"))
  private SupplyWarehouse supplyWarehouse;

  public void setSupplyWarehouse(SupplyWarehouse newSupplyWarehouse, boolean warehouseAlreadyUsed) {
    if (this.supplyWarehouse != null && this.supplyWarehouse.getId().equals(newSupplyWarehouse.getId())) {
      throw new BusinessRuleException("O totem já está vinculado a este almoxarifado.");
    }
    
    if (warehouseAlreadyUsed) {
      throw new BusinessRuleException("Não é possível atribuir o totem a um almoxarifado que já possui outro totem.");
    }

    this.supplyWarehouse = newSupplyWarehouse;
  }
}