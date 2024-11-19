package com.gerenciador.model.dao.impl;

import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriaDaoImpl implements CategoriaDao {

    private Connection conn;

    public CategoriaDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Categoria obj) {
        String sql = "{CALL CadastrarCategoria(?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, obj.getNomeCategoria());
            stmt.setString(2, obj.getDescricao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto usando stored procedure: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Categoria obj) {

    }

    @Override
    public void deleteByName(Categoria obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Categoria findById(Integer id) {
        return null;
    }

    @Override
    public List<Categoria> findByNome(String nome) {
        return List.of();
    }

    @Override
    public List<Categoria> findAll() {
        return List.of();
    }

    @Override
    public List<Produto> findByProduto(Produto produto) {
        return List.of();
    }
}
