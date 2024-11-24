package com.gerenciador.application;

import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.gerenciador.utils.Assertions.*;

public class ProdutoManager {

    // Método principal que exibe o menu de gerenciamento de produtos
    public static void gerenciarProdutos(Scanner scanner) {
        int opcao = 0;
        do {
            try {
                System.out.println("-------------------------------------------------------------");
                System.out.println("GERENCIAMENTO DE PRODUTOS:");
                System.out.println("1. Cadastrar Produto");
                System.out.println("2. Editar Produto");
                System.out.println("3. Excluir Produto");
                System.out.println("4. Consultar Produto");
                System.out.println("5. Voltar");
                System.out.println("-------------------------------------------------------------");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> cadastrarProduto(scanner);
                    case 2 -> editarProduto(scanner);
                    case 3 -> excluirProduto(scanner);
                    case 4 -> consultarProduto(scanner);
                    case 5 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                // Trata entradas inválidas, como letras ou caracteres especiais

                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();
            }
        } while (opcao != 5);
    }

    // Método para cadastrar um novo produto
    private static void cadastrarProduto(Scanner scanner) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            // Captura os dados do produto

            System.out.println("----- Cadastrar Produto -----");
            System.out.print("Digite o nome do produto: ");
            scanner.nextLine();
            String nomeProduto = scanner.nextLine();

            if (assertThatNameIsNotEmpty(nomeProduto)) return; // Valida o nome

            System.out.print("Digite a descrição do produto: ");
            String descricao = scanner.nextLine();

            if (assertThatDescriptionIsNotEmpty(descricao)) return; // Valida a descrição

            System.out.print("Digite a quantidade em estoque: ");
            int qtdEstoque = scanner.nextInt();

            if (assertThatValueIsHigherThanZero(qtdEstoque)) return; // Valida a quantidade

            System.out.print("Digite o preço de compra: ");
            double precoDeCompra = scanner.nextDouble();

            if (assertThatValueIsHigherThanZero(precoDeCompra)) return; // Valida o preço de compra

            System.out.print("Digite o preço de venda: ");
            double precoDeVenda = scanner.nextDouble();

            if (assertThatValueIsHigherThanZero(precoDeVenda)) return; // Valida o preço de venda

            // Captura ou cria a categoria associada

            System.out.print("Digite o nome da categoria: ");
            scanner.nextLine();
            String nomeDaCategoria = scanner.nextLine();

            if (assertThatNameIsNotEmpty(nomeDaCategoria)) return;


            // Busca a categoria pelo nome
            Categoria categoria = categoriaDao.findByName(nomeDaCategoria);

            if (categoria == null) {

                System.out.println("Categoria não encontrada. Vamos criar uma nova categoria para o produto.");


                System.out.print("Digite a descrição da nova categoria: ");
                String descricaoCategoria = scanner.nextLine();

                if (assertThatDescriptionIsNotEmpty(nomeDaCategoria)) return;

                categoria = new Categoria(nomeDaCategoria, descricaoCategoria);

                categoria = categoriaDao.insert(categoria);

                System.out.printf("Nova categoria '%s' criada com sucesso!%n", categoria.getNomeCategoria());
            }


            Produto produto = new Produto(nomeProduto, descricao, qtdEstoque, precoDeCompra, precoDeVenda);
            produtoDao.insert(produto, categoria);

            System.out.println("Produto cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira valores corretos.");
            scanner.nextLine();
        } catch (DbExecption e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos editarProduto, excluirProduto e consultarProduto seguem lógica semelhante:
    // - Validam entradas do usuário.
    // - Interagem com o banco via DAOs.
    // - Lidam com exceções específicas.

    private static void editarProduto(Scanner scanner) {
        try {
            ProdutoDao produtoDao = DaoFactory.createProdutoDao();

            System.out.println("----- Editar Produto -----");
            System.out.print("Digite o ID do produto a ser editado: ");
            int idProduto = scanner.nextInt();

            if (assertThatValueIsHigherThanZero(idProduto)) return;


            System.out.print("Digite o novo nome do produto: ");
            scanner.nextLine();
            String nomeProduto = scanner.nextLine();

            if (assertThatNameIsNotEmpty(nomeProduto)) return;

            System.out.print("Digite a nova descrição do produto: ");
            String descricao = scanner.nextLine();

            if (assertThatDescriptionIsNotEmpty(nomeProduto)) return;

            System.out.print("Digite a nova quantidade em estoque: ");
            int qtdEstoque = scanner.nextInt();

            if (assertThatValueIsHigherThanZero(qtdEstoque)) return;


            System.out.print("Digite o novo preço de compra: ");
            double precoDeCompra = scanner.nextDouble();

            if (assertThatValueIsHigherThanZero(precoDeCompra)) return;


            System.out.print("Digite o novo preço de venda: ");
            double precoDeVenda = scanner.nextDouble();

            if (assertThatValueIsHigherThanZero(precoDeVenda)) return;


            Produto produto = new Produto(idProduto, nomeProduto, descricao, qtdEstoque, precoDeCompra, precoDeVenda);
            produtoDao.update(produto);

            System.out.println("Produto atualizado com sucesso!");
        } catch (DbExecption e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirProduto(Scanner scanner) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();

        try {
            System.out.println("----- Excluir Produto -----");
            System.out.print("Digite o ID do produto a ser excluído: ");
            int idProduto = scanner.nextInt();

            if (assertThatValueIsHigherThanZero(idProduto)) return;

            produtoDao.deleteById(idProduto);

            System.out.println("Produto excluído com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }

    private static void consultarProduto(Scanner scanner) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            System.out.println("----- Consultar Produto -----");
            System.out.println("1. Consultar por ID");
            System.out.println("2. Consultar por Nome");
            System.out.println("3. Consultar por Categoria ");
            System.out.println("4. Consultar por Quantidade em estoque.");
            System.out.println("5. Listar todos");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do produto: ");
                    int idProduto = scanner.nextInt();

                    if (assertThatValueIsHigherThanZero(idProduto)) return;
                    if (assertProdutoExitsById(produtoDao, idProduto)) return;

                    Produto produto = produtoDao.findById(idProduto);
                    System.out.println(produto);
                }
                case 2 -> {
                    System.out.print("Digite o nome do produto: ");
                    scanner.nextLine();
                    String nomeProduto = scanner.nextLine();

                    if (assertThatNameIsNotEmpty(nomeProduto)) return;


                    List<Produto> produtos = produtoDao.findByNome(nomeProduto);

                    assertThatProdutoListIsNotEmpty(produtos);

                    produtos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Digite o nome do categoria: ");
                    scanner.nextLine();
                    String nomeCategoria = scanner.nextLine();

                    if (assertThatNameIsNotEmpty(nomeCategoria)) return;

                    Categoria categoria = categoriaDao.findByName(nomeCategoria);

                    if (assertCategoriaExistByName(categoria)) return;

                    List<Produto> produtos = produtoDao.findByCategoria(categoria);

                    if (assertThatProdutoListIsNotEmpty(produtos)) return;

                    produtos.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Digite a quantiade em estoque: ");
                    int qtdEstoque = scanner.nextInt();

                    if (assertThatValueIsHigherThanZero(qtdEstoque)) return;


                    List<Produto> produtos = produtoDao.findByQtdEstoque(qtdEstoque);

                    if (assertThatProdutoListIsNotEmpty(produtos)) return;

                    produtos.forEach(System.out::println);
                }
                case 5 -> {
                    List<Produto> produtos = produtoDao.findAll();

                    if (assertThatProdutoListIsNotEmpty(produtos)) return;

                    produtos.forEach(System.out::println);
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }
}
