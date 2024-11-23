package com.gerenciador.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EstoqueManager {
    public static void movimentacaoEstoque(Scanner scanner) {
        int opcao = 0;
        do {
            try {
                System.out.println("-------------------------------------------------------------");
                System.out.println("MOVIMENTAÇÃO DE ESTOQUE:");
                System.out.println("1. Registrar Entrada de Produto");
                System.out.println("2. Registrar Saída de Produto");
                System.out.println("3. Voltar");
                System.out.println("-------------------------------------------------------------");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> registrarEntrada(scanner);
                    case 2 -> registrarSaida(scanner);
                    case 3 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (opcao != 3);
    }

    private static void registrarEntrada(Scanner scanner) {
        // Código existente aqui
    }

    private static void registrarSaida(Scanner scanner) {
        // Código existente aqui
    }
}
