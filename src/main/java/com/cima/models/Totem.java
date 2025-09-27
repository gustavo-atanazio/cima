package com.cima.models;

public class Totem {
  private int id;
  private int unitID;

  public Totem(int id, int unitID) {
    this.id = id;
    this.unitID = unitID;
  }

  public Totem(int unitID) {
    this.unitID = unitID;
  }

  public int getId() { return id; }

  public int getUnitID() { return unitID; }

  @Override
  public String toString() {
    return "Totem | ID: " + id + ", ID da unidade: " + unitID;
  }
}