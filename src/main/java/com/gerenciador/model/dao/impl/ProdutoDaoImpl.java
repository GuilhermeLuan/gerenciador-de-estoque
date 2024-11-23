package com.gerenciador.model.dao.impl;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.dao.ProdutoDao;
import com.gerenciador.model.entities.Categoria;
import com.gerenciador.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface ProdutoDao.
 * Esta classe gerencia as operações CRUD relacionadas à Produto.
 */
public class ProdutoDaoImpl implements ProdutoDao {

    // Conexão com o banco de dados
    private Connection conn;

    public ProdutoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere um novo Produto no banco de dados utilizando um Stored Procedure.
     */
    @Override
    public void insert(Produto obj) {
        String sql = "{CALL CadastrarProdutos(?, ?, ?, ?, ?)}"; // Chama a stored procedure

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

    /**
     * Atualiza os dados de um Produto.
     */
    @Override
    public void update(Produto obj) {
        PreparedStatement ps = null;
        try {
            // Query SQL para atualizar os dados do produto
            String sql =
                    """
                                    UPDATE mydb.Produto
                                    SET NomeProduto   = ?,
                                        Descricao     = ?,
                                        QtdEstoque    = ?,
                                        PrecoDeCompra = ?,
                                        PrecoDeVenda  = ?
                                    WHERE IdProduto = ?;
                            """;

            // Prepara a instrução SQL

            ps = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            // Define os valores dos parâmetros

            ps.setString(1, obj.getNomeProduto());
            ps.setString(2, obj.getDescricao());
            ps.setInt(3, obj.getQtdEstoque());
            ps.setDouble(4, obj.getPrecoDeCompra());
            ps.setDouble(5, obj.getPrecoDeVenda());
            ps.setInt(6, obj.getIdProduto());

            // Executa a atualização

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteByName(Produto obj) {

    }

    /**
     * Remove um Produto com base no ID fornecido.
     */
    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "DELETE FROM Produto\n" +
                    "WHERE IdProduto = ?");
            ps.setInt(1, id); // Define o ID da produto a ser deletada
            ps.executeUpdate(); // Executa a exclusão

        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }
    
    /**
     * Busca um Produto com base no ID fornecido.
     */
    @Override
    public Produto findById(Integer id) {
        String sql = "SELECT * FROM Produto WHERE IdProduto = ?";
        Produto produto = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) { 
            ps.setInt(1, id); // Define o ID da produto
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto = instantiateProduto(rs); // Instancia a produto com os dados retornados
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return produto;
    }
    
    /**
     * Busca um Produto com base no Nome.
     */
    @Override
    public List<Produto> findByNome(String nome) {
        String sql = "SELECT * FROM Produto WHERE NomeProduto LIKE ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    produtos.add(instantiateProduto(rs));
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return produtos;
    }

    /**
     * Busca todas as Categorias.
     */
    @Override
    public List<Produto> findAll() {
        String sql = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Itera sobre os resultados e adiciona os produtos à lista

            while (rs.next()) {
                produtos.add(instantiateProduto(rs));
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return produtos;
    }


    /**
     * Busca um Produto com base na Categoria.
     */
    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        String sql = """
                 SELECT p.*\s
                 FROM Produto p
                 JOIN Produto_has_Categoria pc ON p.IdProduto = pc.Produto_IdProduto
                 WHERE pc.Categoria_idCategoria = ?;
                \s""";

        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoria.getIdCategoria());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    produtos.add(instantiateProduto(rs));
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return produtos;
    }

    /**
     * Busca um Produto com base no Nome.
     */
    @Override
    public List<Produto> findByQtdEstoque(Integer qtdEstoque) {
        String sql = "SELECT * FROM Produto WHERE QtdEstoque <= ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qtdEstoque);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    produtos.add(instantiateProduto(rs));
                }
            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        }

        return produtos;
    }

    /**
     * Método auxiliar para instanciar um objeto Produto a partir de um ResultSet.
     */
    private Produto instantiateProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setIdProduto(rs.getInt("IdProduto"));
        produto.setNomeProduto(rs.getString("NomeProduto"));
        produto.setDescricao(rs.getString("Descricao"));
        produto.setQtdEstoque(rs.getInt("QtdEstoque"));
        produto.setPrecoDeCompra(rs.getDouble("PrecoDeCompra"));
        produto.setPrecoDeVenda(rs.getDouble("PrecoDeVenda"));
        return produto;
    }
}
