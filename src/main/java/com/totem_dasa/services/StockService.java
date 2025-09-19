package com.totem_dasa.services;

import com.totem_dasa.infra.FakeDatabase;
import com.totem_dasa.models.StockEntry;
import com.totem_dasa.models.StockExit;
import com.totem_dasa.models.Employee;
import com.totem_dasa.models.SupplyMovement;
import com.totem_dasa.models.Supply;
import com.totem_dasa.models.Totem;
import com.totem_dasa.models.Unit;

public class StockService {
  private final Totem totem;
  private final Unit unit;
  private final FakeDatabase database;
  private static final byte EMPLOYEE_ACCESS_LEVEL = 1;

  public StockService(int totemID) {
    this.database = new FakeDatabase();
    this.totem = database.getTotemById(totemID);

    if (this.totem == null) {
      throw new IllegalArgumentException("Totem não encontrado para o ID: " + totemID);
    }

    this.unit = database.getUnitByTotemID(totem.getUnitID());
    if (this.unit == null) {
      throw new IllegalArgumentException("Unidade não encontrada para o totem ID: " + totemID);
    }
  }

  public Totem getTotem() { return totem; }

  public Unit getUnit() { return unit; }

  public boolean authEmployee(String token) {
    Employee employee = database.getEmployeeByToken(token);
    return employee != null && employee.getAccessLevel() >= EMPLOYEE_ACCESS_LEVEL;
  }

  public Employee getEmployeeByToken(String token) { return database.getEmployeeByToken(token); }

  public Supply getSupplyByCode(int code) { return database.getSupplyByCode(code); }

  public void registerEntry(StockEntry entry) {
    for (SupplyMovement movement: entry.getEntries()) {
      unit.addSupply(movement.getSupply(), movement.getQuantity());
    }

    database.saveUnit(unit);
    entry.register();
  }

  public void registerExit(StockExit exit) {
    for (SupplyMovement movement: exit.getExits()) {
      unit.removeSupply(movement.getSupply(), movement.getQuantity());
    }

    database.saveUnit(unit);
    exit.register();
  }
}