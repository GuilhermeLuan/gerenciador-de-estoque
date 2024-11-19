package com.gerenciador.model.dao;

import com.gerenciador.model.entities.ProdutoHasCategoria;

import java.util.List;

public interface ProdutoHasCategoriaDao {
    void insert(int produtoId, int categoriaId); // Insere uma relação
    void deleteByProdutoId(int produtoId); // Deleta todas as relações de um produto
    void deleteByCategoriaId(int categoriaId); // Deleta todas as relações de uma categoria
    void delete(int produtoId, int categoriaId); // Deleta uma relação específica
    List<Integer> findCategoriasByProdutoId(int produtoId); // Busca categorias por produto
    List<Integer> findProdutosByCategoriaId(int categoriaId); // Busca produtos por categoria
    List<ProdutoHasCategoria> findAll(); // Lista todas as relações
}