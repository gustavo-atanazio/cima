package br.com.totem.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Unidade {
  private int id;
  private String nome;
  private String endereco;
  private Map<Insumo, Integer> estoque = new HashMap<>();

  public Unidade(int id, String nome, String endereco) {
    this.id = id;
    this.nome = nome;
    this.endereco = endereco;
  }

  public void adicionarInsumo(Insumo insumo, int quantidade) {
    estoque.put(insumo, estoque.getOrDefault(insumo, 0) + quantidade);
  }

  public void removerInsumo(Insumo insumo, int quantidade) {
    int atual = estoque.getOrDefault(insumo, 0);
    if (quantidade > atual) {
      throw new IllegalArgumentException("Quantidade insuficiente no estoque para " + insumo.getNome());
    }
    int novoValor = atual - quantidade;
    if (novoValor > 0) {
      estoque.put(insumo, novoValor);
    } else {
      estoque.remove(insumo);
    }
  }

  public Map<Insumo, Integer> getEstoque() {
    return Collections.unmodifiableMap(estoque);
  }

  public int getQuantidadeDe(Insumo insumo) {
    return estoque.getOrDefault(insumo, 0);
  }

  public int getId() {
    return id;
  }

  public void exibirInsumos() {
    if (estoque.isEmpty()) {
      System.out.println("Nenhum insumo registrado.");
      return;
    }

    System.out.println("\nEstoque atual da unidade:");
    for (Map.Entry<Insumo, Integer> entry : estoque.entrySet()) {
      System.out.printf("- %s (código %d): %d unidades\n", entry.getKey().getNome(), entry.getKey().getId(), entry.getValue());
    }
  }
}