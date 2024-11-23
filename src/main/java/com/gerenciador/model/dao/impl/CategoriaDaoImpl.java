package com.gerenciador.model.dao.impl;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface CategoriaDao.
 * Esta classe gerencia as operações CRUD relacionadas à Categoria.
 */
public class CategoriaDaoImpl implements CategoriaDao {

    // Conexão com o banco de dados
    private Connection conn;

    public CategoriaDaoImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere uma nova Categoria no banco de dados utilizando uma Stored Procedure.
     */
    @Override
    public void insert(Categoria obj) {
        String sql = "{CALL CadastrarCategoria(?, ?)}"; // Chama a stored procedur

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, obj.getNomeCategoria());
            stmt.setString(2, obj.getDescricao());

            stmt.execute(); // Executa a stored procedure
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto usando stored procedure: " + e.getMessage(), e);
        }
    }

    /**
     * Atualiza os dados de uma Categoria.
     */
    @Override
    public void update(Categoria obj) {
        PreparedStatement ps = null;
        try {
            // Query SQL para atualizar os dados da categoria
            String sql =
                    """
                             UPDATE Categoria
                             SET NomeCategoria = ?,
                                 Descricao = ?
                             WHERE idCategoria = ?;
                            """;

            // Prepara a instrução SQL
            ps = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            // Define os valores dos parâmetros

            ps.setString(1, obj.getNomeCategoria());
            ps.setString(2, obj.getDescricao());
            ps.setInt(3, obj.getIdCategoria());

            // Executa a atualização

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    /**
     * Remove uma Categoria com base no ID fornecido.
     */
    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Categoria WHERE idCategoria = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // Define o ID da categoria a ser deletada
            ps.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    /**
     * Busca uma Categoria com base no ID fornecido.
     */
    @Override
    public Categoria findById(Integer id) {
        String sql = "SELECT * FROM Categoria WHERE idCategoria = ?";
        Categoria categoria = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // Define o ID da categoria
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = instantiateCategoria(rs); // Instancia a categoria com os dados retornados
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return categoria;
    }

    /**
     * Busca uma Categoria com base no Nome.
     */
    @Override
    public Categoria findByName(String name) {
        String sql = "SELECT * FROM Categoria WHERE NomeCategoria = ?";
        Categoria categoria = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = instantiateCategoria(rs);
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return categoria;
    }

    /**
     * Busca todas as Categorias.
     */
    @Override
    public List<Categoria> findAll() {
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Itera sobre os resultados e adiciona as categorias à lista
            while (rs.next()) {
                categorias.add(instantiateCategoria(rs));
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return categorias;
    }

    /**
     * Método auxiliar para instanciar um objeto Categoria a partir de um ResultSet.
     */
    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("idCategoria"));
        categoria.setNomeCategoria(rs.getString("NomeCategoria"));
        categoria.setDescricao(rs.getString("Descricao"));
        return categoria;
    }
}
