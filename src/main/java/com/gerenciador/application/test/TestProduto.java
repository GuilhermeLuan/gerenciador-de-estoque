package com.gerenciador.application.test;

import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

public class TestProduto {
    public static void main(String[] args) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();

        Categoria objCategoria = new Categoria(
                "Teste",
                "Teste"
        );

        System.out.println("----- Test Procedure Cadastro Produto -----");

        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell Inspiron 15 com 16GB de RAM e 512GB SSD",
                50,
                3500.00,
                4500.00
        );
        produtoDao.insert(produto, objCategoria);
        System.out.println();

        System.out.println("----- Test Update Produto -----");

        try {
            Produto produtoToUpdate = new Produto(
                    5,
                    "Notebook Editado",
                    "Notebook Dell Inspiron 15 com 16GB de RAM e 512GB SSD",
                    11,
                    3500.00,
                    4500.00
            );
            produtoDao.update(produtoToUpdate);
            System.out.println(produtoToUpdate.getNomeProduto() + " Atualizado com sucesso!");
        } catch (DbExecption e) {
            System.out.println(e.getMessage());
        }


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
        List<Produto> produtosByQtdEstoque = produtoDao.findByQtdEstoque(10);
        produtosByQtdEstoque.forEach(System.out::println);
        System.out.println();
    }
}
