package com.gerenciador.model.dao;

import com.gerenciador.db.DB;
import com.gerenciador.model.dao.impl.CategoriaDaoImpl;
import com.gerenciador.model.dao.impl.ProdutoDaoImpl;
import com.gerenciador.model.dao.impl.ProdutoHasCategoriaDaoImpl;
import com.gerenciador.service.Relatorio;

public class DaoFactory {
    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoImpl(DB.getConnection());
    }

    public static CategoriaDao createCategoriaDao() {
        return new CategoriaDaoImpl(DB.getConnection());
    }

    public static ProdutoHasCategoriaDao createProdutoHasCategoriaDao() {
        return new ProdutoHasCategoriaDaoImpl(DB.getConnection());
    }

    public static Relatorio createRelatorio() {
        return new Relatorio(DB.getConnection());
    }
}