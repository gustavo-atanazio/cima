package br.com.totem.models;

public class Supply {
  private int id;
  private String name;

  public Supply(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() { return id; }
  
  public String getName() { return name; }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    
    Supply supply = (Supply) object;
    return id == supply.id;
  }

  @Override
  public int hashCode() { return Integer.hashCode(id); }
}