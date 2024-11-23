package com.gerenciador.model.dao;

import com.gerenciador.db.DB;
import com.gerenciador.model.dao.impl.CategoriaDaoImpl;
import com.gerenciador.model.dao.impl.ProdutoDaoImpl;
import com.gerenciador.model.dao.impl.ProdutoHasCategoriaDaoImpl;
import com.gerenciador.service.Relatorio;

/**
 * Esta classe fornece métodos para instanciar implementações
 * das interfaces DAO utilizadas no sistema.
 */
public class DaoFactory {

    /**
     * Cria e retorna uma instância de ProdutoDao.
     */
    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoImpl(DB.getConnection());
    }

    /**
     * Cria e retorna uma instância de CategoriaDao.
     */
    public static CategoriaDao createCategoriaDao() {
        return new CategoriaDaoImpl(DB.getConnection());
    }

    public static ProdutoHasCategoriaDao createProdutoHasCategoriaDao() {
        return new ProdutoHasCategoriaDaoImpl(DB.getConnection());
    }

    /**
     * Cria e retorna uma instância de RelatorioDao.
     */
    public static Relatorio createRelatorio() {
        return new Relatorio(DB.getConnection());
    }
}