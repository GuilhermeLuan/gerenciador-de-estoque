package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

public interface ProdutoDao {
    void insert(Produto obj);
    void update(Produto obj);
    void deleteByName(Produto obj);
    void deleteById(Integer id);
    Produto findById(Integer id);
    List<Produto> findByNome(String nome);
    List<Produto> findAll();
    List<Produto> findByCategoria(Categoria categoria);
    List<Produto> findByQtdEstoque(Integer qtdEstoque);
}
