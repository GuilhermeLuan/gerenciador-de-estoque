package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.sql.SQLOutput;
import java.util.List;

public class TestProduto {
    public static void main(String[] args) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();

        System.out.println("----- Test Procedure Cadastro Produto -----");

        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell Inspiron 15 com 16GB de RAM e 512GB SSD",
                50,
                3500.00,
                4500.00
        );
        produtoDao.insert(produto);
        System.out.println();

        System.out.println("----- Test Update Produto -----");

        Produto produtoToUpdate = new Produto(
                6,
                "Notebook Editado",
                "Notebook Dell Inspiron 15 com 16GB de RAM e 512GB SSD",
                9,
                3500.00,
                4500.00
        );
        produtoDao.update(produtoToUpdate);
        System.out.println();


        System.out.println("----- Test Delete Produto -----");
        produtoDao.deleteById(6);
        System.out.println();


        System.out.println("----- Test FindById Produto -----");
        Produto produto1 = produtoDao.findById(1);
        System.out.println(produto1);
        System.out.println();

        System.out.println("----- Test FindByName Produto -----");
        List<Produto> notebook = produtoDao.findByNome("Sabonete");
        notebook.forEach(System.out::println);
        System.out.println();


        System.out.println("----- Test FindAll Produto -----");
        List<Produto> produtos = produtoDao.findAll();
        produtos.forEach(System.out::println);
        System.out.println();


        System.out.println("----- Test FindByCategoria Produto -----");
        Categoria categoria = new Categoria(5, "Higiene Pessoal", "Produtos destinados ao cuidado diário pessoal");
        List<Produto> produtosByCategoria = produtoDao.findByCategoria(categoria);
        produtosByCategoria.forEach(System.out::println);
        System.out.println();


        System.out.println("----- Test FindByQtd Estoque -----");
        List<Produto> produtosByQtdEstoque = produtoDao.findByQtdEstoque(9);
        produtosByQtdEstoque.forEach(System.out::println);
        System.out.println();
    }
}
