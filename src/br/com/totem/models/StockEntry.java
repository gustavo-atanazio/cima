package br.com.totem.models;

import java.util.ArrayList;
import java.util.List;

public class StockEntry extends StockMovement {
  private List<SupplyMovement> entries;

  public StockEntry(Employee employee, Totem totem) {
    super(employee, totem);
    this.entries = new ArrayList<>();
  }

  public List<SupplyMovement> getEntries() { return entries; }

  @Override
  public void register() {
    /*
      Implementação futura da lógica para persistir as entradas no banco de dados
      ou outro armazenamento
    */
    System.out.println(getSummary());
  }

  @Override
  public String getSummary() {
    StringBuilder builder = new StringBuilder("Entrada por " + employee.getName() + ":\n");

    for (SupplyMovement ent : entries) builder.append(ent.getSummary()).append("\n");

    return builder.toString();
  }

  @Override
  public void addMovement(SupplyMovement movement) { entries.add(movement); }
}