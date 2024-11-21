package com.gerenciador.application;

import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;
import com.gerenciador.service.Relatorio;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeEstoque {
    private static final Relatorio relatorio = DaoFactory.createRelatorio();

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
                    case 1 -> gerenciarProdutos(scanner);
                    case 2 -> gerenciarCategorias(scanner);
                    case 3 -> movimentacaoEstoque(scanner);
                    case 4 -> gerarRelatorios(scanner);
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

    private static void gerenciarProdutos(Scanner scanner) {
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
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (opcao != 5);
    }


    private static void gerenciarCategorias(Scanner scanner) {
        int opcao = 0;
        do {
            try {
                System.out.println("-------------------------------------------------------------");
                System.out.println("GERENCIAMENTO DE CATEGORIAS:");
                System.out.println("1. Cadastrar Categoria");
                System.out.println("2. Editar Categoria");
                System.out.println("3. Excluir Categoria");
                System.out.println("4. Consultar Categoria");
                System.out.println("5. Voltar");
                System.out.println("-------------------------------------------------------------");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1 -> cadastrarCategoria(scanner);
                    case 2 -> editarCategoria(scanner);
                    case 3 -> excluirCategoria(scanner);
                    case 4 -> consultarCategoria(scanner);
                    case 5 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer
            }
        } while (opcao != 5);
    }

    private static void movimentacaoEstoque(Scanner scanner) {
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

    private static void gerarRelatorios(Scanner scanner) {
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

    private static void cadastrarProduto(Scanner scanner) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();

        try {
            System.out.println("----- Cadastrar Produto -----");
            System.out.print("Digite o nome do produto: ");
            scanner.nextLine(); 
            String nomeProduto = scanner.nextLine();

            System.out.print("Digite a descrição do produto: ");
            String descricao = scanner.nextLine();

            System.out.print("Digite a quantidade em estoque: ");
            int qtdEstoque = scanner.nextInt();

            System.out.print("Digite o preço de compra: ");
            double precoDeCompra = scanner.nextDouble();

            System.out.print("Digite o preço de venda: ");
            double precoDeVenda = scanner.nextDouble();

            System.out.print("Digite o ID da categoria: ");
            int idCategoria = scanner.nextInt();

            Produto produto = new Produto(nomeProduto, descricao, qtdEstoque, precoDeCompra, precoDeVenda);
            produtoDao.insert(produto);

            System.out.println("Produto cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira valores corretos.");
            scanner.nextLine(); // Limpa o buffer para evitar looping
        } catch (DbExecption e) {
            System.out.println(e.getMessage());
        }
    }


    private static void editarProduto(Scanner scanner) {
        try {
            ProdutoDao produtoDao = DaoFactory.createProdutoDao();

            System.out.println("----- Editar Produto -----");
            System.out.print("Digite o ID do produto a ser editado: ");
            int idProduto = scanner.nextInt();

            System.out.print("Digite o novo nome do produto: ");
            scanner.nextLine(); 
            String nomeProduto = scanner.nextLine();

            System.out.print("Digite a nova descrição do produto: ");
            String descricao = scanner.nextLine();

            System.out.print("Digite a nova quantidade em estoque: ");
            int qtdEstoque = scanner.nextInt();

            System.out.print("Digite o novo preço de compra: ");
            double precoDeCompra = scanner.nextDouble();

            System.out.print("Digite o novo preço de venda: ");
            double precoDeVenda = scanner.nextDouble();

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

            produtoDao.deleteById(idProduto);

            System.out.println("Produto excluído com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
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
                    Produto produto = produtoDao.findById(idProduto);
                    System.out.println(produto);
                }
                case 2 -> {
                    System.out.print("Digite o nome do produto: ");
                    scanner.nextLine(); 
                    String nomeProduto = scanner.nextLine();
                    List<Produto> produtos = produtoDao.findByNome(nomeProduto);
                    produtos.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Digite o nome do categoria: ");
                    scanner.nextLine(); 
                    String nomeCategoria = scanner.nextLine();

                    Categoria categoria = categoriaDao.findByName(nomeCategoria);

                    if(categoria == null) {
                        System.out.println("Categoria não encontrada!");
                        return;
                    }

                    List<Produto> produtos = produtoDao.findByCategoria(categoria);

                    if(produtos.isEmpty()) {
                        System.out.printf("Nenhum produto encontrado da categoria %s!\n", categoria.getNomeCategoria());
                    }

                    produtos.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Digite a quantiade em estoque: ");
                    int qtdEstoque = scanner.nextInt();
                    List<Produto> produtos = produtoDao.findByQtdEstoque(qtdEstoque);
                    produtos.forEach(System.out::println);
                }
                case 5 -> {
                    List<Produto> produtos = produtoDao.findAll();
                    produtos.forEach(System.out::println);
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
        }
    }


    private static void cadastrarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            System.out.println("----- Cadastrar Categoria -----");
            System.out.print("Digite o nome da categoria: ");
            scanner.nextLine(); 
            String nomeCategoria = scanner.nextLine();

            System.out.print("Digite a descrição da categoria: ");
            String descricao = scanner.nextLine();

            Categoria categoria = new Categoria(nomeCategoria, descricao);
            categoriaDao.insert(categoria);

            System.out.println("Categoria cadastrada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
        }
    }


    private static void editarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            System.out.println("----- Editar Categoria -----");
            System.out.print("Digite o ID da categoria a ser editada: ");
            int idCategoria = scanner.nextInt();

            System.out.print("Digite o novo nome da categoria: ");
            scanner.nextLine(); 
            String nomeCategoria = scanner.nextLine();

            System.out.print("Digite a nova descrição da categoria: ");
            String descricao = scanner.nextLine();

            Categoria categoria = new Categoria(idCategoria, nomeCategoria, descricao);
            categoriaDao.update(categoria);

            System.out.println("Categoria atualizada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
        }
    }


    private static void excluirCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
        try {
            System.out.println("Função de excluir categoria ainda não implementada.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void consultarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
        try {
            System.out.println("Função de consultar categoria ainda não implementada.");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o ID do produto: ");
                    int idProduto = scanner.nextInt();
                    List<Categoria> categoria = categoriaDao.findAll();
                    categoria.forEach(System.out::println);
                }
                default -> System.out.println("Opção inválida.");
            }


        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void registrarEntrada(Scanner scanner) {
        System.out.println("Função de registrar entrada ainda não implementada.");
    }

    private static void registrarSaida(Scanner scanner) {
        System.out.println("Função de registrar saída ainda não implementada.");
    }

    private static void gerarRelatorioProdutos() {
        System.out.println("Relatório de Produtos Cadastrados");
        relatorio.produtosCadastrados();
    }

    private static void gerarRelatorioMovimentacao() {
        System.out.println("Relatório de movimentação de estoque (entradas e saídas)");
        relatorio.movimentacaoEstoque();
    }

    private static void gerarRelatorioBaixoEstoque() {
        System.out.println("Relatório de produtos com baixo estoque!");
        relatorio.relatorioBaixoEstoque(15);
    }

    private static void gerarRelatorioVendasLucro() {
        System.out.println("Relatório de vendas e lucro (baseado no preço de compra e venda)");
        relatorio.vendasELucros();
    }
}
