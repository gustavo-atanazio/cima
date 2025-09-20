package com.totem_dasa.tests;

import java.util.Optional;

import com.totem_dasa.controllers.StockMovementController;
import com.totem_dasa.models.StockMovement;

public class StockMovementTest {
  private static final StockMovementController controller = new StockMovementController();

  public static void testAll() {
    System.out.println("----- INÍCIO DOS TESTES DE MOVIMENTAÇÃO DE ESTOQUE -----");

    controller.getAll().forEach(movement -> System.out.println(movement.toString()));

    Optional<StockMovement> movementOne = controller.getById(1);

    if (movementOne.isPresent()) System.out.println("Movimentação encontrada: " + movementOne.get().toString());
    else System.out.println("Movimentação não encontrada.");

    Optional<StockMovement> movementTwo = controller.getById(1000);

    if (movementTwo.isPresent()) System.out.println("Movimentação encontrada: " + movementTwo.get().toString());
    else System.out.println("Movimentação não encontrada.");

    System.out.println("----- FIM DOS TESTES DE MOVIMENTAÇÃO DE ESTOQUE -----");  
  }
}