package com.totem_dasa.models;

public class Totem {
  private int id;
  private int unitID;

  public Totem(int id, int unitID) {
    this.id = id;
    this.unitID = unitID;
  }

  public int getId() { return id; }

  public int getUnitID() { return unitID; }
}