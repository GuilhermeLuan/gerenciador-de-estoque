package com.gerenciador.model.dao.impl;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.CategoriaDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement ps = null;
        try {
            String sql =
                    """
                    UPDATE Categoria
                    SET NomeCategoria = ?,
                        Descricao = ?
                    WHERE idCategoria = ?;
                   """;


            ps = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1,obj.getNomeCategoria());
            ps.setString(2,obj.getDescricao());
            ps.setInt(3,obj.getIdCategoria());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM Categoria WHERE idCategoria = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }
    }

    @Override
    public Categoria findById(Integer id) {
        String sql = "SELECT * FROM Categoria WHERE idCategoria = ?";
        Categoria categoria = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
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


    @Override
    public List<Categoria> findAll() {
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorias.add(instantiateCategoria(rs));
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return categorias;
    }



    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("idCategoria"));
        categoria.setNomeCategoria(rs.getString("NomeCategoria"));
        categoria.setDescricao(rs.getString("Descricao"));
        return categoria;
    }
}
