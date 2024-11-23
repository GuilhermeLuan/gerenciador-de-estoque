package com.gerenciador.model.dao;


import com.gerenciador.model.entities.MovimentacaoEstoque;

import java.sql.SQLException;
import java.util.List;

public interface MovimentacaoEstoqueDAO {

    void registrarMovimentacao(int produtoId, String tipoMovimentacao, int quantidade) throws SQLException;

    /**
     * Insere uma movimentação de estoque no banco de dados.
     */
    void insert(MovimentacaoEstoque obj);

    /**
     * Atualiza uma movimentação de estoque no banco de dados.
     */
    void update(MovimentacaoEstoque obj);

    /**
     * Deleta uma movimentação de estoque do banco de dados com base no seu ID.
     */
    void deleteById(Integer id);

    /**
     * Busca uma movimentação de estoque no banco de dados com base no seu ID.
     */
    MovimentacaoEstoque findById(Integer id);

    /**
     * Busca todas as movimentações de estoque no banco de dados.
     */
    List<MovimentacaoEstoque> findAll();
}

