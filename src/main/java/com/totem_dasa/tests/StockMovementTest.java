package com.totem_dasa.tests;

import java.time.LocalDateTime;
import java.util.Optional;

import com.totem_dasa.controllers.StockMovementController;
import com.totem_dasa.models.StockMovement;

public class StockMovementTest {
  private static final StockMovementController controller = new StockMovementController();

  public static void testAll() {
    System.out.println("----- INÍCIO DOS TESTES DE ALTERAÇÃO -----");

    // ---
    System.out.println("--- Lista de alterações: ---");

    controller.getAll().forEach(movement -> System.out.println(movement.toString()));

    System.out.println("--- ---");

    // ---
    System.out.println("--- Busca por ID: ---");

    Optional<StockMovement> movementOne = controller.getById(1);

    if (movementOne.isPresent()) System.out.println("Alteração encontrada: " + movementOne.get().toString());
    else System.out.println("Alteração não encontrada.");

    Optional<StockMovement> movementTwo = controller.getById(1000);

    if (movementTwo.isPresent()) System.out.println("Alteração encontrada: " + movementTwo.get().toString());
    else System.out.println("Alteração não encontrada.");

    System.out.println("--- ---");

    // ---
    System.out.println("--- Criação de alteração: ---");

    StockMovement createdMovement = controller.post(new StockMovement(1, 1, LocalDateTime.now()));
    System.out.println("Totem criado: " + createdMovement.toString());

    System.out.println("---");

    // ---
    System.out.println("--- Exclusão de alteração: ---");

    controller.delete(createdMovement.getId());
    System.out.println("Movimentação com ID " + createdMovement.getId() + " excluída:");
    controller.getAll().forEach(totem -> System.out.println(totem.toString()));

    System.out.println("--- ---");

    System.out.println("----- FIM DOS TESTES DE ALTERAÇÃO -----");  
  }
}