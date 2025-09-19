package com.totem_dasa.models;

import java.time.LocalDateTime;

public abstract class StockMovement {
  protected int id;
  protected Employee employee;
  protected Totem totem;
  protected LocalDateTime date;

  public StockMovement(Employee employee, Totem totem) {
    this.employee = employee;
    this.totem = totem;
    this.date = LocalDateTime.now();
  }

  public abstract void register();

  public abstract String getSummary();

  public abstract void addMovement(SupplyMovement movement);
}