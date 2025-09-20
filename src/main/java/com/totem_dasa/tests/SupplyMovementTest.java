package com.totem_dasa.tests;

import java.util.Optional;

import com.totem_dasa.controllers.SupplyMovementController;
import com.totem_dasa.models.SupplyMovement;

public class SupplyMovementTest {
  private static final SupplyMovementController controller = new SupplyMovementController();

  public static void testAll() {
    System.out.println("----- INÍCIO DOS TESTES DE MOVIMENTAÇÃO DE INSUMO -----");

    // ---
    System.out.println("--- Lista de movimentações: ---");

    controller.getAll().forEach(movement -> System.out.println(movement.toString()));

    System.out.println("--- ---");

    // ---
    System.out.println("--- Busca por ID: ---");

    Optional<SupplyMovement> movementOne = controller.getById(1);

    if (movementOne.isPresent()) System.out.println("Movimentação encontrada: " + movementOne.get().toString());
    else System.out.println("Movimentação não encontrada.");

    Optional<SupplyMovement> movementTwo = controller.getById(1000);

    if (movementTwo.isPresent()) System.out.println("Movimentação encontrada: " + movementTwo.get().toString());
    else System.out.println("Movimentação não encontrada.");

    System.out.println("--- ---");

    // ---
    System.out.println("--- Criação de movimentação: ---");

    SupplyMovement createdMovement = controller.post(new SupplyMovement(1, 1, 'E', 6));
    System.out.println("Movimentação criada: " + createdMovement.toString());

    System.out.println("---");

    // ---
    System.out.println("--- Exclusão de movimentação: ---");

    controller.delete(createdMovement.getId());
    System.out.println("Movimentação com ID " + createdMovement.getId() + " excluída:");
    controller.getAll().forEach(movement -> System.out.println(movement.toString()));

    System.out.println("--- ---");

    System.out.println("----- FIM DOS TESTES DE MOVIMENTAÇÃO DE INSUMO -----");  
  }
}