package br.com.totem.infra;

import java.util.HashMap;
import java.util.Map;

import br.com.totem.domain.*;

public class FakeDatabase {
  private Map<Integer, Unidade> unidades = new HashMap<>();
  private Map<Integer, Totem> totems = new HashMap<>();

  public FakeDatabase() {
    Unidade unidade = new Unidade(1, "Unidade Teste", "Endereço Teste");
    unidade.adicionarInsumo(getInsumoByCodigo(1), 100);
    unidades.put(1, unidade);

    totems.put(1, new Totem(1, unidade.getId()));
  }

  public Unidade getUnidadeByTotemId(int totemId) {
    Totem totem = getTotemById(totemId);
    if (totem == null)
      return null;
    Unidade unidade = unidades.get(totem.getUnidadeId());
    if (unidade == null)
      return null;
    return unidade;
  }

  public Unidade getUnidadeById(int id) {
    return unidades.get(id);
  }

  public void salvarUnidade(Unidade unidade) {
    unidades.put(unidade.getId(), unidade);
  }

  public Funcionario getFuncionarioByToken(String token) {
    if ("valid-token".equals(token)) {
      return new Funcionario(1, 1, "Funcionario Teste", 1, token);
    }

    return null;
  }

  public Insumo getInsumoByCodigo(int codigo) {
    if (codigo == 1) {
      return new Insumo(codigo, "Seringa");
    }
    if (codigo == 2) {
      return new Insumo(codigo, "Gaze");
    }
    if (codigo == 3) {
      return new Insumo(codigo, "Luvas");
    }
    if (codigo == 4) {
      return new Insumo(codigo, "Álcool Gel");
    }
    return null;
  }

  public Totem getTotemById(int totemId) {
    return totems.get(totemId);
  }
}