package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.entities.Produto;
import com.gerenciador.service.Relatorio;

import java.util.List;
import java.util.Scanner;

import static com.gerenciador.utils.Assertions.assertThatProdutoListIsNotEmpty;

public class RelatorioManager {
    // Instância de Relatorio obtida via DaoFactory
    private static final Relatorio relatorio = DaoFactory.createRelatorio();

    // Método principal que exibe o menu de relatórios
    public static void gerarRelatorios(Scanner scanner) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("GERAÇÃO DE RELATÓRIOS:");
        System.out.println("1. Relatório de Produtos Cadastrados");
        System.out.println("2. Relatório de Movimentação de Estoque");
        System.out.println("3. Relatório de Produtos com Baixo Estoque");
        System.out.println("4. Relatório de Vendas e Lucro");
        System.out.println("5. Voltar");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> gerarRelatorioProdutos();
            case 2 -> gerarRelatorioMovimentacao();
            case 3 -> gerarRelatorioBaixoEstoque();
            case 4 -> gerarRelatorioVendasLucro();
            case 5 -> System.out.println("Voltando ao menu principal...");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    // Relatório de produtos cadastrados
    private static void gerarRelatorioProdutos() {
        System.out.println("Relatório de Produtos Cadastrados");

        // Busca a lista de produtos cadastrados
        List<Produto> produtos = relatorio.produtosCadastrados();

        // Verifica se a lista não está vazia
        if (assertThatProdutoListIsNotEmpty(produtos)) return;

        // Imprime os produtos no console
        produtos.forEach(System.out::println);
    }

    // Relatório de movimentação de estoque
    private static void gerarRelatorioMovimentacao() {
        System.out.println("Relatório de movimentação de estoque (entradas e saídas)");

        // Realiza a lógica de movimentação via serviço de relatórios
        relatorio.movimentacaoEstoque();
    }

    // Relatório de produtos com baixo estoque
    private static void gerarRelatorioBaixoEstoque() {
        int limiteEstoque = 100;

        // Gera o relatório com base no limite
        relatorio.relatorioBaixoEstoque(limiteEstoque);
    }

    // Relatório de vendas e lucros
    private static void gerarRelatorioVendasLucro() {
        System.out.println("Relatório de vendas e lucro (baseado no preço de compra e venda)");

        // Realiza a lógica de cálculo de vendas e lucros
        relatorio.vendasELucros();
    }
}
