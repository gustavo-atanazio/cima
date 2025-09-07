package br.com.totem.models;

public class SupplyMovement {
  private Supply supply;
  private int quantity;

  public SupplyMovement(Supply supply, int quantity) {
    this.supply = supply;
    this.quantity = quantity;
  }

  public Supply getSupply() { return supply; }

  public int getQuantity() { return quantity; }

  public String getSummary() { return supply.getName() + " - " + quantity + " unidade(s)"; }
}