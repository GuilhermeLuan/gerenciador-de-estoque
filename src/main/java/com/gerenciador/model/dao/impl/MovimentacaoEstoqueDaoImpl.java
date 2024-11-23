package com.gerenciador.model.dao.impl;

import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.MovimentacaoEstoqueDAO;
import com.gerenciador.model.entities.MovimentacaoEstoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueDaoImpl implements MovimentacaoEstoqueDAO {

    // Conexão com o banco de dados
    private Connection conn;

    public MovimentacaoEstoqueDaoImpl(Connection conn) {
        this.conn = conn;
    }


    /**
     * Insere uma nova Movimentação de Estoque no banco de dados.
     */

    @Override
    public void registrarMovimentacao(int produtoId, String tipoMovimentacao, int quantidade) {
        String sql = "{CALL RegistrarMovimentacao(?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, produtoId);
            stmt.setString(2, tipoMovimentacao);
            stmt.setInt(3, quantidade);
            stmt.execute();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    @Override
    public void insert(MovimentacaoEstoque obj) {
        String sql = "INSERT INTO MovimentacaoEstoque (produto_id, tipo, quantidade, data) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Define os valores dos parâmetros
            ps.setInt(1, obj.getProdutoID());
            ps.setString(2, obj.getTipoMovimentacao().toString());
            ps.setInt(3, obj.getQuantidade());
            ps.setTimestamp(4, Timestamp.valueOf(obj.getDataMovimentacao().toString()));

            // Executa a inserção
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    /**
     * Atualiza os dados de uma Movimentação de Estoque existente no banco de dados.
     */
    @Override
    public void update(MovimentacaoEstoque obj) {
        String sql = "UPDATE MovimentacaoEstoque SET produto_id = ?, tipo = ?, quantidade = ?, data = ? WHERE idMovimentacao = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Define os valores dos parâmetros
            ps.setInt(1, obj.getProdutoID());
            ps.setString(2, obj.getTipoMovimentacao().toString());
            ps.setInt(3, obj.getQuantidade());
            ps.setTimestamp(4, Timestamp.valueOf(obj.getDataMovimentacao().toString()));
            ps.setInt(5, obj.getIdMovimentacao());

            // Executa a atualização
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    /**
     * Remove uma Movimentação de Estoque com base no ID fornecido.
     */
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM MovimentacaoEstoque WHERE idMovimentacao = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Define o ID da movimentação de estoque a ser deletada
            ps.setInt(1, id);
            ps.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    /**
     * Busca uma Movimentação de Estoque com base no ID fornecido.
     */
    @Override
    public MovimentacaoEstoque findById(Integer id) {
        String sql = "SELECT * FROM MovimentacaoEstoque WHERE idMovimentacao = ?";
        MovimentacaoEstoque movimentacao = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // Define o ID da movimentação de estoque
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movimentacao = instantiateMovimentacaoEstoque(rs); // Instancia a movimentação com os dados retornados
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return movimentacao;
    }

    /**
     * Busca todas as Movimentações de Estoque.
     */
    @Override
    public List<MovimentacaoEstoque> findAll() {
        String sql = "SELECT * FROM MovimentacaoEstoque";
        List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Itera sobre os resultados e adiciona as movimentações à lista
            while (rs.next()) {
                movimentacoes.add(instantiateMovimentacaoEstoque(rs));
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return movimentacoes;
    }

    /**
     * Método auxiliar para instanciar um objeto MovimentacaoEstoque a partir de um ResultSet.
     */
    private MovimentacaoEstoque instantiateMovimentacaoEstoque(ResultSet rs) throws SQLException {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setIdMovimentacao(rs.getInt("idMovimentacao"));
        movimentacao.setProdutoID(rs.getInt("produto_id"));
        movimentacao.setTipoMovimentacao(MovimentacaoEstoque.TipoMovimentacao.valueOf(rs.getString("tipo")));
        movimentacao.setQuantidade(rs.getInt("quantidade"));
        movimentacao.setDataMovimentacao(rs.getTimestamp("data").toLocalDateTime());
        return movimentacao;
    }
}
