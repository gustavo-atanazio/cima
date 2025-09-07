package br.com.totem.models;

import java.util.ArrayList;
import java.util.List;

public class StockExit extends StockMovement {
  private List<SupplyMovement> exits;

  public StockExit(Employee employee, Totem totem) {
    super(employee, totem);
    this.exits = new ArrayList<>();
  }

  public List<SupplyMovement> getExits() { return exits; }

  @Override
  public void register() {
    /*
      Implementação futura da lógica para persistir as saídas no banco de dados ou
      outro armazenamento
    */
    System.out.println(getSummary());
  }

  @Override
  public String getSummary() {
    StringBuilder builder = new StringBuilder("Saída por " + employee.getName() + ":\n");
    
    for (SupplyMovement exit: exits) builder.append(exit.getSummary()).append("\n");

    return builder.toString();
  }

  @Override
  public void addMovement(SupplyMovement movement) { exits.add(movement); }
}