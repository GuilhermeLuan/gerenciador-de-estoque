package com.gerenciador.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorDeEstoque {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal = 0;

        do {
            try {
                System.out.println("=================== GERENCIADOR DE ESTOQUE ===================");
                System.out.println("1. Gerenciar Produtos");
                System.out.println("2. Gerenciar Categorias");
                System.out.println("3. Movimentação de Estoque");
                System.out.println("4. Relatórios");
                System.out.println("5. Sair");
                System.out.println("=============================================================");
                System.out.print("Escolha uma opção: ");
                opcaoPrincipal = scanner.nextInt();

                switch (opcaoPrincipal) {
                    case 1 -> ProdutoManager.gerenciarProdutos(scanner);
                    case 2 -> CategoriaManager.gerenciarCategorias(scanner);
                    case 3 -> EstoqueManager.movimentacaoEstoque(scanner);
                    case 4 -> RelatorioManager.gerarRelatorios(scanner);
                    case 5 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer para evitar looping infinito
            }
        } while (opcaoPrincipal != 5);

        scanner.close();
    }
}
