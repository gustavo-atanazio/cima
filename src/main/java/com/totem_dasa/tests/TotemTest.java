package com.totem_dasa.tests;

import java.util.Optional;

import com.totem_dasa.controllers.TotemController;
import com.totem_dasa.models.Totem;

public class TotemTest {
  private static final TotemController controller = new TotemController();

  public static void testAll() {
    System.out.println("----- INÍCIO DOS TESTES DE TOTEM -----");

    // ---
    System.out.println("--- Lista de Totens: ---");

    controller.getAll().forEach(totem -> System.out.println(totem.toString()));

    System.out.println("--- ---");

    // ---
    System.out.println("--- Busca por ID: ---");

    Optional<Totem> totemOne = controller.getById(1);

    if (totemOne.isPresent()) System.out.println("Totem encontrado: " + totemOne.get().toString());
    else System.out.println("Totem não encontrado.");

    Optional<Totem> totemTwo = controller.getById(1000);

    if (totemTwo.isPresent()) System.out.println("Totem encontrado: " + totemTwo.get().toString());
    else System.out.println("Totem não encontrado.");

    System.out.println("--- ---");

    // ---
    System.out.println("--- Criação de totem: ---");

    Totem createdTotem = controller.post(new Totem(1));
    System.out.println("Totem criado: " + createdTotem.toString());

    System.out.println("---");

    // ---
    System.out.println("--- Exclusão de totem: ---");

    controller.delete(createdTotem.getId());
    System.out.println("Movimentação com ID " + createdTotem.getId() + " excluída:");
    controller.getAll().forEach(totem -> System.out.println(totem.toString()));

    System.out.println("--- ---");

    System.out.println("----- FIM DOS TESTES DE TOTEM -----");  
  }
}