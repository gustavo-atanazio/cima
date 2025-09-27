package com.cima.models;

public class SupplyMovement {
  private int id;
  private int supplyID;
  private int quantity;
  private char type;
  private int stockMovementID;

  public SupplyMovement(int id, int supplyID, int quantity, char type, int stockMovementID) {
    this.id = id;
    this.supplyID = supplyID;
    this.quantity = quantity;
    this.type = type;
    this.stockMovementID = stockMovementID;
  }

  public SupplyMovement(int supplyID, int quantity, char type, int stockMovementID) {
    this.supplyID = supplyID;
    this.quantity = quantity;
    this.type = type;
    this.stockMovementID = stockMovementID;
  }

  public int getId() { return id; }
  public int getSupplyID() { return supplyID; }
  public int getQuantity() { return quantity; }
  public char getType() { return type; }
  public int getStockMovementID() { return stockMovementID; }

  @Override
  public String toString() {
    return "Movimentação de insumo | ID: " + id + ", ID do insumo: " + supplyID + ", Quantidade: " + quantity + ", Tipo: " + type + ", ID da alteração: " + stockMovementID;
  }
}