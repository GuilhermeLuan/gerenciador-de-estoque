package com.gerenciador.application;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.entities.Categoria;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.gerenciador.utils.Assertions.*;

/**
 * Classe responsável pelo gerenciamento de categorias no sistema.
 * Permite operações de CRUD através de um menu.
 */
public class CategoriaManager {

    /**
     * Método principal para gerenciar categorias.
     * Exibe o menu para o usuário e redireciona para as operações correspondentes.
     */
    public static void gerenciarCategorias(Scanner scanner) {
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
                scanner.nextLine();
            }
        } while (opcao != 5);
    }

    /**
     * Método para cadastrar uma nova categoria.
     */
    private static void cadastrarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            System.out.println("----- Cadastrar Categoria -----");
            System.out.print("Digite o nome da categoria: ");
            scanner.nextLine();
            String nomeCategoria = scanner.nextLine();

            // Valida se o nome não está vazio
            if (assertThatNameIsNotEmpty(nomeCategoria)) return;

            System.out.print("Digite a descrição da categoria: ");
            String descricao = scanner.nextLine();

            // Valida se a descrição não está vazia
            if (assertThatDescriptionIsNotEmpty(descricao)) return;

            // Cria e insere a nova categoria
            Categoria categoria = new Categoria(nomeCategoria, descricao);
            categoriaDao.insert(categoria);

            System.out.println("Categoria cadastrada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }

    /**
     * Método para editar uma categoria existente.
     */
    private static void editarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        try {
            System.out.println("----- Editar Categoria -----");
            System.out.print("Digite o ID da categoria a ser editada: ");
            int idCategoria = scanner.nextInt();

            // Valida o ID e verifica se a categoria existe
            if (assertThatValueIsHigherThanZero(idCategoria)) return;
            if (assertCategoriaExits(categoriaDao, idCategoria)) return;

            System.out.print("Digite o novo nome da categoria: ");
            scanner.nextLine();
            String nomeCategoria = scanner.nextLine();

            if (assertThatNameIsNotEmpty(nomeCategoria)) return;

            System.out.print("Digite a nova descrição da categoria: ");
            String descricao = scanner.nextLine();

            if (assertThatDescriptionIsNotEmpty(descricao)) return;

            // Atualiza a categoria no banco de dados
            Categoria categoria = new Categoria(idCategoria, nomeCategoria, descricao);
            categoriaDao.update(categoria);

            System.out.println("Categoria atualizada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }

    /**
     * Método para excluir uma categoria existente.
     */
    private static void excluirCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
        try {
            System.out.println("----- Excluir Categoria -----");
            System.out.print("Digite o ID da categoria a ser excluida: ");
            int idCategoria = scanner.nextInt();

            if (assertThatValueIsHigherThanZero(idCategoria)) return;
            if (assertCategoriaExits(categoriaDao, idCategoria)) return;

            // Exclui a categoria
            System.out.println("Categoria excluida com sucesso!");
            categoriaDao.deleteById(idCategoria);

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }

    /**
     * Método para consultar categorias.
     * Permite consultar por nome ou listar todas as categorias.
     */
    private static void consultarCategoria(Scanner scanner) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
        try {

            System.out.println("----- Consultar Categoria -----");
            System.out.println("1. Consultar por nome");
            System.out.println("2. Listar todos");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o nome da categoria: ");
                    scanner.nextLine();
                    String nomeProduto = scanner.nextLine();
                    Categoria categoriaDaoByName = categoriaDao.findByName(nomeProduto);

                    if (assertCategoriaExistByName(categoriaDaoByName)) return;

                    System.out.println(categoriaDaoByName);
                }
                case 2 -> {
                    List<Categoria> categoria = categoriaDao.findAll();

                    if (assertThatCetegoriaListIsNotEmpty(categoria)) return;

                    categoria.forEach(System.out::println);
                }
                default -> System.out.println("Opção inválida.");
            }


        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Por favor, insira um número.");
            scanner.nextLine();
        }
    }
}
