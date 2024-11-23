package com.gerenciador.model.dao;

import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Protudo.
 */
public interface ProdutoDao {

    /**
     * Insere um produto no banco de dados.
     */
    void insert(Produto obj);


    /**
     * Atualiza um produto no banco de dados.
     */
    void update(Produto obj);

    /**
     * Deleta um produto no banco de dados com base no seu Nome.
     */
    void deleteByName(Produto obj);

    /**
     * Deleta um produto no banco de dados com base no seu ID.
     */
    void deleteById(Integer id);

    /**
     * Busca um produto no banco de dados com base no seu ID.
     */
    Produto findById(Integer id);

    /**
     * Busca um produto no banco de dados com base no seu Nome.
     */
    List<Produto> findByNome(String nome);

    /**
     * Busca todas os Produtos Existente do banco de dados.
     */
    List<Produto> findAll();

    /**
     * Busca Produtos com base na categoria.
     */
    List<Produto> findByCategoria(Categoria produto);

    /**
     * Busca Produtos com base na quantidade em estoque.
     */
    List<Produto> findByQtdEstoque(Integer qtdEstoque);
}
