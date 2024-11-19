package com.gerenciador.model.dao.impl;

import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDao {
    private Connection conn;

    public ProdutoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {
        String sql = "{CALL CadastrarProdutos(?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, obj.getNomeProduto());
            stmt.setString(2, obj.getDescricao());
            stmt.setInt(3, obj.getQtdEstoque());
            stmt.setDouble(4, obj.getPrecoDeCompra());
            stmt.setDouble(5, obj.getPrecoDeVenda());


            // Executar a procedure
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto usando stored procedure: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Produto obj) {

    }

    @Override
    public void deleteByName(Produto obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Produto findById(Integer id) {
        return null;
    }

    @Override
    public List<Produto> findByNome(String nome) {
        return List.of();
    }

    @Override
    public List<Produto> findAll() {
        return List.of();
    }

    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        return List.of();
    }

    @Override
    public List<Produto> findByQtdEstoque(Integer qtdEstoque) {
        return List.of();
    }
}
