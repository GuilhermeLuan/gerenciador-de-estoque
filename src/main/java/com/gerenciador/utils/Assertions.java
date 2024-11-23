package com.gerenciador.utils;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

public class Assertions {
    public static boolean assertProdutoExitsById(ProdutoDao produtoDao, int id) {
        if (produtoDao.findById(id) == null) {
            System.out.println("Produto não encontado");
            return true;
        }
        return false;
    }

    public static boolean assertProdutoExistByName(Produto produto) {
        if(produto == null) {
            System.out.println("Produto não encontrado!");
            return true;
        }
        return false;
    }

    public static boolean assertCategoriaExits(CategoriaDao categoriaDao, int idCategoria) {
        if (categoriaDao.findById(idCategoria) == null) {
            System.out.println("Categoria não encontrada!");
            return true;
        }
        return false;
    }

    public static boolean assertCategoriaExistByName(Categoria categoria) {
        if(categoria == null) {
            System.out.println("Categoria não encontrada!");
            return true;
        }
        return false;
    }

    public static boolean assertThatDescriptionIsNotEmpty(String descricao) {
        if (descricao.trim().isEmpty()) {
            System.out.println("A descrição não pode estar vazio.");
            return true;
        }
        return false;
    }

    public static boolean assertThatNameIsNotEmpty(String nome) {
        if (nome.trim().isEmpty()) {
            System.out.println("O nome não pode estar vazio.");
            return true;
        }
        return false;
    }

    public static boolean assertThatValueIsHigherThanZero(double value) {
        if (value < 0) {
            System.out.println("O valor inserido não pode ser menor do que zero");
            return true;
        }
        return false;
    }

    public static boolean assertThatProdutoListIsNotEmpty(List<Produto> produtos) {
        if(produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado!");
            return true;
        }
        return false;
    }

    public static boolean assertThatCetegoriaListIsNotEmpty(List<Categoria> categorias) {
        if(categorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada!");
            return true;
        }
        return false;
    }
}
