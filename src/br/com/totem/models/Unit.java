package br.com.totem.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Unit {
  private int id;
  private String name;
  private String address;
  private Map<Supply, Integer> stock = new HashMap<>();

  public Unit(int id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public int getId() { return id; }

  public String getName() { return name; }

  public String getAddress() { return address; }

  public Map<Supply, Integer> getStock() { return Collections.unmodifiableMap(stock); }

  public void addSupply(Supply supply, int quantity) {
    stock.put(supply, stock.getOrDefault(supply, 0) + quantity);
  }

  public void removeSupply(Supply supply, int quantity) {
    int atual = stock.getOrDefault(supply, 0);
    int newValue = atual - quantity;

    if (quantity > atual) throw new IllegalArgumentException("Quantidade insuficiente no estoque para " + supply.getName());

    if (newValue > 0) stock.put(supply, newValue);
    else stock.remove(supply);
  }

  public int getQuantityOf(Supply supply) { return stock.getOrDefault(supply, 0); }

  public void showSupplies() {
    if (stock.isEmpty()) {
      System.out.println("Nenhum insumo registrado.");
      return;
    }

    System.out.println("\nEstoque atual da unidade:");

    for (Map.Entry<Supply, Integer> entry : stock.entrySet()) {
      System.out.printf(
        "- %s (código %d): %d unidades\n",
        entry.getKey().getName(),
        entry.getKey().getId(),
        entry.getValue()
      );
    }
  }
}