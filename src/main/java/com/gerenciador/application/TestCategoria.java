package com.gerenciador.application;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Categoria;

import java.util.List;

public class TestCategoria {
    public static void main(String[] args) {
        CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();

        System.out.println("----- Test Procedure Cadastro Categoria -----");

        Categoria categoria = new Categoria(
                "Teste",
                "Teste"
        );
        categoriaDao.insert(categoria);


        System.out.println("----- Test Update Categoria -----");
        System.out.println();
        Categoria categoriaToUpdate = new Categoria(
                5,
                "Categoria Atualizada",
                "Teste"
        );
        categoriaDao.update(categoriaToUpdate);

        System.out.println();


        System.out.println("----- Test Delete Categoria -----");
        //categoriaDao.deleteById(1);
        //DELETE ON CASCATE
        System.out.println();


        System.out.println("----- Test FindById Categoria -----");

        System.out.println();

        System.out.println("----- Test FindByName Categoria -----");

        System.out.println();


        System.out.println("----- Test FindAll Categoria -----");

        System.out.println();


        System.out.println("----- Test FindByCategoria Categoria -----");

        System.out.println();


        System.out.println("----- Test FindByQtd Estoque -----");

        System.out.println();
    }
}
