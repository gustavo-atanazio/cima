package com.totem_dasa;

import java.util.Scanner;

import com.totem_dasa.models.StockMovement;
import com.totem_dasa.models.StockEntry;
import com.totem_dasa.models.StockExit;
import com.totem_dasa.models.SupplyMovement;
import com.totem_dasa.models.Supply;
import com.totem_dasa.models.Totem;
import com.totem_dasa.models.Unit;
import com.totem_dasa.services.StockService;

public class App {
  public static void main(String[] args) {
    // Configuração do ambiente
    System.out.println("Bem-vindo ao Sistema de Almoxarifado!");
    int totemID = args.length > 0 ? Integer.parseInt(args[0]) : 1;
    System.out.println("Totem ID: " + totemID);

    if (totemID <= 0) {
      System.err.println("❌ ID do totem inválido. O programa será encerrado.");
      return;
    }

    System.out.println("Iniciando o sistema de controle de estoque...");
    Scanner scanner = new Scanner(System.in);
    StockService stockService = new StockService(totemID);
    Unit unit = stockService.getUnit();

    if (unit == null) {
      System.err.println("❌ Unidade não encontrada para o totem ID: " + totemID);
      scanner.close();
      return;
    }

    Totem totem = stockService.getTotem();
    if (totem == null) {
      System.err.println("❌ Totem não encontrado para o ID: " + totemID);
      scanner.close();
      return;
    }

    while (true) {
      System.out.println("\n--- MENU ---");
      System.out.println("1. Adicionar insumos");
      System.out.println("2. Remover insumos");
      System.out.println("3. Ver insumos disponíveis");
      System.out.println("4. Sair");

      System.out.print("Escolha uma opção: ");
      byte option = scanner.nextByte();
      scanner.nextLine();

      if (option == 4) {
        System.out.println("Saindo...");
        break;
      }

      if (option == 3) {
        unit.showSupplies();
        continue;
      }

      // Autenticação
      System.out.print("Aproxime o cartão: (Digite o token do funcionário) ");
      String token = scanner.nextLine();

      if (!stockService.authEmployee(token)) {
        System.err.println("Autenticação falhou.");
        continue;
      }

      // Criar alteração (entrada ou saída)
      StockMovement stockMovement;
      if (option == 1) stockMovement = new StockEntry(stockService.getEmployeeByToken(token), totem);
      else if (option == 2) stockMovement = new StockExit(stockService.getEmployeeByToken(token), totem);
      else {
        System.err.println("Opção inválida.");
        continue;
      }

      // Loop para múltiplas movimentações
      while (true) {
        System.out.print("Código do insumo: ");
        int code = scanner.nextInt();
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        scanner.nextLine();

        Supply supply = stockService.getSupplyByCode(code);
        SupplyMovement supplyMovement = new SupplyMovement(supply, qtd);
        stockMovement.addMovement(supplyMovement);

        System.out.print("Deseja adicionar outro insumo à " + (stockMovement instanceof StockEntry ? "entrada" : "saída") + "? (s/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (!response.equals("s")) break;
      }

      // Registrar alteração
      if (stockMovement instanceof StockEntry) stockService.registerEntry((StockEntry) stockMovement);
      else if (stockMovement instanceof StockExit) {
        try { stockService.registerExit((StockExit) stockMovement);}
        catch (IllegalArgumentException exception) { System.err.println(exception.getMessage()); }
      }
    }

    scanner.close();
  }
}