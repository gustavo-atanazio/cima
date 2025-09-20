package com.totem_dasa;

import java.util.Scanner;

import com.totem_dasa.services.AuthService;
import com.totem_dasa.tests.StockMovementTest;
import com.totem_dasa.tests.SupplyMovementTest;
import com.totem_dasa.tests.TotemTest;

public class App {
  public static void main(String[] args) {
    AuthService authService = new AuthService();
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o token de autenticação: ");
    String token = scanner.nextLine();

    try {
      authService.auth(token);
      System.out.println("Autenticação bem-sucedida.");
      
      scanner.close();

      TotemTest.testAll();
      StockMovementTest.testAll();
      SupplyMovementTest.testAll();
    } catch (SecurityException e) {
      System.out.println("Falha na autenticação: " + e.getMessage());
      return;
    }
  }
}