package com.cima.models;

public class Employee {
  private int id;
  private int unitID;
  private String name;
  private int accessLevel;
  private String token;

  public Employee(int id, int unitID, String name, int accessLevel, String token) {
    this.id = id;
    this.unitID = unitID;
    this.name = name;
    this.accessLevel = accessLevel;
    this.token = token;
  }

  public int getId() { return id; }

  public int getUnitID() { return unitID; }

  public String getName() { return name; }

  public int getAccessLevel() { return accessLevel; }

  public String getToken() { return token; }
}