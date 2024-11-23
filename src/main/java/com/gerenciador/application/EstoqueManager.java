package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.MovimentacaoEstoqueDAO;
import com.gerenciador.model.entities.MovimentacaoEstoque;
import com.gerenciador.utils.Assertions;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.gerenciador.utils.Assertions.*;

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
                scanner.nextLine();
            }
        } while (opcao != 3);
    }

    private static void registrarEntrada(Scanner scanner) {
        MovimentacaoEstoqueDAO movimentacaoEstoque = DaoFactory.createMovimentacaoEstoque();
        try {
            System.out.println("----- Registrar Entrada -----");
            System.out.print("Digite o ID do produto: ");
            int produtoId = scanner.nextInt();
            scanner.nextLine();
            assertThatValueIsHigherThanZero(produtoId);


            System.out.print("Digite a quantidade de entrada: ");
            int quantidade = scanner.nextInt();

            // Valida se a quantidade é positiva
            assertThatValueIsHigherThanZero(quantidade);

            // Registra a movimentação de entrada
            movimentacaoEstoque.registrarMovimentacao(produtoId,
                    String.valueOf(MovimentacaoEstoque.TipoMovimentacao.ENTRADA), quantidade);

            System.out.println("Entrada registrada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira números válidos.");
            scanner.nextLine();
        } catch (RuntimeException e) {
            System.out.println("Erro ao registrar a entrada: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void registrarSaida(Scanner scanner) {
        MovimentacaoEstoqueDAO movimentacaoEstoque = DaoFactory.createMovimentacaoEstoque();


        try {
            System.out.println("----- Registrar Saída -----");
            System.out.print("Digite o ID do produto: ");
            int produtoId = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha deixada pelo nextInt()

            System.out.print("Digite a quantidade de saída: ");
            int quantidade = scanner.nextInt();

            // Valida se a quantidade é positiva
            if (quantidade <= 0) {
                System.out.println("A quantidade deve ser maior que zero.");
                return;
            }

            // Registra a movimentação de saída
            movimentacaoEstoque.registrarMovimentacao(produtoId,
                    String.valueOf(MovimentacaoEstoque.TipoMovimentacao.SAIDA), quantidade);

            System.out.println("Saída registrada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira números válidos.");
            scanner.nextLine(); // Limpa o buffer de entrada
        } catch (RuntimeException e) {
            System.out.println("Erro ao registrar a saída: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
