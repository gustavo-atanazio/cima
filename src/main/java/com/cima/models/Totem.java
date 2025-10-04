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
import lombok.Setter;

@Entity @Table(name = "totem") @AllArgsConstructor @NoArgsConstructor
public class Totem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  private Integer id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "supply_warehouse_id", foreignKey = @ForeignKey(name = "fk_totem_supply_warehouse"))
  @Getter @Setter
  private SupplyWarehouse supplyWarehouse;
}