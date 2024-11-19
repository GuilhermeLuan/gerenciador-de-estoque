package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Produto;

import java.sql.SQLOutput;

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
                50,
                3500.00,
                4500.00
        );
        produtoDao.update(produtoToUpdate);



    }
}
