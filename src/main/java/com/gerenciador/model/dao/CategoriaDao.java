package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

public interface CategoriaDao {
    void insert(Categoria obj);
    void update(Categoria obj);
    void deleteByName(Categoria obj);
    void deleteById(Integer id);
    Categoria findById(Integer id);
    List<Categoria> findByNome(String nome);
    List<Categoria> findAll();
    List<Produto> findByProduto(Produto produto);
}
