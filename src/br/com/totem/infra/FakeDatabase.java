package br.com.totem.infra;

import java.util.HashMap;
import java.util.Map;

import br.com.totem.models.Employee;
import br.com.totem.models.Supply;
import br.com.totem.models.Totem;
import br.com.totem.models.Unit;

public class FakeDatabase {
  private Map<Integer, Unit> units = new HashMap<>();
  private Map<Integer, Totem> totems = new HashMap<>();

  public FakeDatabase() {
    Unit unidade = new Unit(1, "Unidade Teste", "Endereço Teste");
    unidade.addSupply(getSupplyByCode(1), 100);
    units.put(1, unidade);

    totems.put(1, new Totem(1, unidade.getId()));
  }

  public Unit getUnitByTotemID(int totemID) {
    Totem totem = getTotemById(totemID);
    if (totem == null) return null;

    Unit unit = units.get(totem.getUnitID());
    if (unit == null) return null;
    
    return unit;
  }

  public Unit getUnitByID(int id) { return units.get(id); }

  public void saveUnit(Unit unidade) { units.put(unidade.getId(), unidade); }

  public Employee getEmployeeByToken(String token) {
    if ("valid-token".equals(token)) {
      return new Employee(1, 1, "Funcionário Teste", 1, token);
    }

    return null;
  }

  public Supply getSupplyByCode(int code) {
    switch (code) {
      case 1:
        return new Supply(code, "Seringa");

      case 2:
        return new Supply(code, "Gaze");

      case 3:
        return new Supply(code, "Luvas");

      case 4:
        return new Supply(code, "Álcool Gel");
    
      default:
        return null;
    }
  }

  public Totem getTotemById(int totemID) { return totems.get(totemID); }
}