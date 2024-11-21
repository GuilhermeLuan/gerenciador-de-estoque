package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Categoria;

import java.util.List;

public interface CategoriaDao {
    void insert(Categoria obj);

    void update(Categoria obj);

    void deleteById(Integer id);

    Categoria findById(Integer id);

    Categoria findByName(String name);

    List<Categoria> findAll();
}
