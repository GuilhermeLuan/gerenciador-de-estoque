package com.gerenciador.utils;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

public class Assertions {

    /**
     * Verifica se um produto com o ID especificado existe no banco de dados.
     */
    public static boolean assertProdutoExitsById(ProdutoDao produtoDao, int id) {
        if (produtoDao.findById(id) == null) {
            System.out.println("Produto não encontado");
            return true;
        }
        return false;
    }



/**
 * Verifica se o objeto Produto fornecido é nulo.
 */
    public static boolean assertProdutoExistByName(Produto produto) {
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return true;
        }
        return false;
    }

    /**
     * Verifica se uma categoria com o ID especificado existe no banco de dados.
     */
    public static boolean assertCategoriaExits(CategoriaDao categoriaDao, int idCategoria) {
        if (categoriaDao.findById(idCategoria) == null) {
            System.out.println("Categoria não encontrada!");
            return true;
        }
        return false;
    }

    /**
     * Verifica se o objeto Categoria fornecido é nulo.
     */
    public static boolean assertCategoriaExistByName(Categoria categoria) {
        if (categoria == null) {
            System.out.println("Categoria não encontrada!");
            return true;
        }
        return false;
    }

    /**
     * Verifica se uma descrição fornecida está vazia.
     */
    public static boolean assertThatDescriptionIsNotEmpty(String descricao) {
        if (descricao.trim().isEmpty()) {
            System.out.println("A descrição não pode estar vazio.");
            return true;
        }
        return false;
    }

    /**
     * Verifica se um nome fornecido está vazio.
     */
    public static boolean assertThatNameIsNotEmpty(String nome) {
        if (nome.trim().isEmpty()) {
            System.out.println("O nome não pode estar vazio.");
            return true;
        }
        return false;
    }

    /**
     * Verifica se o valor fornecido é menor do que zero.
     */
    public static boolean assertThatValueIsHigherThanZero(double value) {
        if (value < 0) {
            System.out.println("O valor inserido não pode ser menor do que zero");
            return true;
        }
        return false;
    }

    /**
     * Verifica se a lista de produtos fornecida está vazia.
     */
    public static boolean assertThatProdutoListIsNotEmpty(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
            return true;
        }
        return false;
    }

    /**
     * Verifica se a lista de categorias fornecida está vazia.
     */
    public static boolean assertThatCetegoriaListIsNotEmpty(List<Categoria> categorias) {
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada!");
            return true;
        }
        return false;
    }
}
