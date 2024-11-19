package com.gerenciador.model.dao.impl;

import com.gerenciador.model.dao.ProdutoHasCategoriaDao;
import com.gerenciador.model.entities.ProdutoHasCategoria;

import java.sql.Connection;
import java.util.List;

public class ProdutoHasCategoriaDaoImpl implements ProdutoHasCategoriaDao {
    private Connection conn;

    public ProdutoHasCategoriaDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(int produtoId, int categoriaId) {

    }

    @Override
    public void deleteByProdutoId(int produtoId) {

    }

    @Override
    public void deleteByCategoriaId(int categoriaId) {

    }

    @Override
    public void delete(int produtoId, int categoriaId) {

    }

    @Override
    public List<Integer> findCategoriasByProdutoId(int produtoId) {
        return List.of();
    }

    @Override
    public List<Integer> findProdutosByCategoriaId(int categoriaId) {
        return List.of();
    }

    @Override
    public List<ProdutoHasCategoria> findAll() {
        return List.of();
    }
}
