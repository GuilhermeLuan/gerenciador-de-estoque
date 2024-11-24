package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Categoria;

import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Categoria.
 */
public interface CategoriaDao {

    /**
     * Insere uma categoria no banco de dados.
     */
    Categoria insert(Categoria obj);


    /**
     * Atualiza uma categoria no banco de dados.
     */
    void update(Categoria obj);

    /**
     * Deleta uma categoria no banco de dados com base no seu ID.
     */
    void deleteById(Integer id);

    /**
     * Busca uma categoria no banco de dados com base no seu ID.
     */
    Categoria findById(Integer id);

    /**
     * Busca uma categoria no banco de dados com base no seu Nome.
     */
    Categoria findByName(String name);

    /**
     * Busca todas as Categorias Existente do banco de dados.
     */
    List<Categoria> findAll();
}
