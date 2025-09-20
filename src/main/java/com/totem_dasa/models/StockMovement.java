package com.totem_dasa.models;

import java.time.LocalDateTime;

public class StockMovement {
  private int id;
  private int employeeID;
  private int totemID;
  private LocalDateTime date;

  public StockMovement(int id, int employeeID, int totemID, LocalDateTime date) {
    this.id = id;
    this.employeeID = employeeID;
    this.totemID = totemID;
    this.date = date;
  }

  public StockMovement(int employeeID, int totemID, LocalDateTime date) {
    this.employeeID = employeeID;
    this.totemID = totemID;
    this.date = date;
  }

  public int getId() { return id; }
  public int getEmployeeID() { return employeeID; }
  public int getTotemID() { return totemID; }
  public LocalDateTime getDate() { return date; }

  @Override
  public String toString() {
    return "Alteração | ID: " + id + ", ID do funcionário: " + employeeID + ", ID do totem: " + totemID + ", Data: " + date;
  }
}