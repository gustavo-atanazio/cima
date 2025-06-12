package br.com.totem;

import java.util.Scanner;

import br.com.totem.application.EstoqueService;
import br.com.totem.domain.*;

public class App {
    public static void main(String[] args) {
        // Configuração do ambiente
        System.out.println("Bem-vindo ao Sistema de Almoxarifado!");
        int totemId = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        System.out.println("Totem ID: " + totemId);
        if (totemId <= 0) {
            System.err.println("❌ ID do totem inválido. O programa será encerrado.");
            return;
        }
        System.out.println("Iniciando o sistema de controle de estoque...");
        Scanner scanner = new Scanner(System.in);
        EstoqueService estoqueService = new EstoqueService(totemId);
        Unidade unidade = estoqueService.getUnidade();
        if (unidade == null) {
            System.err.println("❌ Unidade não encontrada para o totem ID: " + totemId);
            scanner.close();
            return;
        }
        Totem totem = estoqueService.getTotem();
        if (totem == null) {
            System.err.println("❌ Totem não encontrado para o ID: " + totemId);
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
            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            if (opcao == 4) {
                System.out.println("Saindo...");
                break;
            }

            if (opcao == 3) {
                unidade.exibirInsumos();
                continue;
            }

            // Autenticação
            System.out.print("Aproxime o cartão: (Digite o token do funcionário) ");
            String token = scanner.nextLine();

            if (!estoqueService.autenticarFuncionario(token)) {
                System.err.println("Autenticação falhou.");
                continue;
            }

            // Criar alteração (entrada ou saída)
            Alteracao alteracao;
            if (opcao == 1) {
                alteracao = new AlteracaoEntrada(estoqueService.getFuncionarioByToken(token), totem);
            } else if (opcao == 2) {
                alteracao = new AlteracaoSaida(estoqueService.getFuncionarioByToken(token), totem);
            } else {
                System.err.println("Opção inválida.");
                continue;
            }

            // Loop para múltiplas movimentações
            while (true) {
                System.out.print("Código do insumo: ");
                int codigo = scanner.nextInt();
                System.out.print("Quantidade: ");
                int qtd = scanner.nextInt();
                scanner.nextLine(); // consumir quebra de linha

                Insumo insumo = estoqueService.getInsumoByCodigo(codigo);
                MovimentacaoInsumo mov = new MovimentacaoInsumo(insumo, qtd);
                alteracao.adicionarMovimentacao(mov);

                System.out.print("Deseja adicionar outro insumo à "
                        + (alteracao instanceof AlteracaoEntrada ? "entrada" : "saída") + "? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                if (!resposta.equals("s")) {
                    break;
                }
            }

            // Registrar alteração
            if (alteracao instanceof AlteracaoEntrada) {
                estoqueService.registrarEntrada((AlteracaoEntrada) alteracao);
            } else if (alteracao instanceof AlteracaoSaida) {
                try {
                    estoqueService.registrarSaida((AlteracaoSaida) alteracao);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }

            }

        }

        scanner.close();
    }
}
