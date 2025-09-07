package br.com.totem.services;

import br.com.totem.infra.FakeDatabase;
import br.com.totem.models.StockEntry;
import br.com.totem.models.StockExit;
import br.com.totem.models.Employee;
import br.com.totem.models.SupplyMovement;
import br.com.totem.models.Supply;
import br.com.totem.models.Totem;
import br.com.totem.models.Unit;

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