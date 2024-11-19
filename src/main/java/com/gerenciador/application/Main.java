package com.gerenciador.application;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

public class Main {
    public static void main(String[] args) {
        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        System.out.println("----- Test Procedure Cadastro Produto -----");

        Produto produto = new Produto(
                "Notebook",
                "Notebook Dell Inspiron 15 com 16GB de RAM e 512GB SSD",
                50,
                3500.00,
                4500.00
        );
        produtoDao.insert(produto);

        System.out.println("----- Test Procedure Cadastro Categoria -----");

        Categoria categoria = new Categoria(
                "Produtos de higiene",
                "Produtos para higiene pessoal"
        );
        categoriaDao.insert(categoria);

    }
}
