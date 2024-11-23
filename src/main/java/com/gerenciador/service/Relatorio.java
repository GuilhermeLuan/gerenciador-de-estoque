package com.gerenciador.service;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private final Connection conn; // Conexão com o banco de dados

    public Relatorio(Connection conn) {
        this.conn = conn;
    }

    /**
     * Gera uma lista de produtos cadastrados no sistema chamando uma stored procedure.
     */
    public List<Produto> produtosCadastrados() {
        String sql = "{CALL RelatorioDeProdutos()}"; // Chama a stored procedure.

        List<Produto> produtos = new ArrayList<>();  // Lista para armazenar os produtos

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery(); // Executa a query.

            while (rs.next()) {
                // Recupera os dados do produto.
                int produtoID = rs.getInt("IdProduto");
                String nome = rs.getString("NomeProduto");
                String descricao = rs.getString("Descricao");
                int quantidade = rs.getInt("QtdEstoque");
                Double precoDeCompra = rs.getDouble("PrecoDeCompra");
                Double precoDeVenda = rs.getDouble("PrecoDeVenda");

                // Cria um objeto Produto e adiciona à lista.
                Produto produto = new Produto(produtoID, nome, descricao, quantidade, precoDeCompra, precoDeVenda);

                produtos.add(produto);

            }
            return produtos;
        } catch (SQLException e) {
            // Lança uma exceção em caso de erro na execução da stored procedure.
            throw new RuntimeException("Erro ao executar Relatorio de Produtos Cadastrados: " + e.getMessage(), e);
        }
    }

    /**
     * Gera um relatório de movimentações de estoque.
     */
    public void movimentacaoEstoque() {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

            // Consulta SQL para obter as movimentações de estoque.
            String sql = """
                    SELECT m.idMovimentacao   AS IDMovimentação,
                           p.NomeProduto      AS Produto,
                           m.EntradaDeProduto AS Entrada,
                           m.SaidaDeProduto   AS Saída,
                           m.QtdEstoque       AS EstoqueAtual,
                           m.Movimentacaocol  AS Detalhes
                    FROM Movimentacao m
                             JOIN
                         Produto p ON m.idProduto = p.IdProduto
                    ORDER BY m.idMovimentacao DESC;""";

            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            // Verifica se há resultados na consulta.
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma movimentação de estoque encontrada!");
                return;
            }

            // Exibe os dados de cada movimentação.
            while (rs.next()) {
                int idMovimentacao = rs.getInt("IDMovimentação");
                String produto = rs.getString("Produto");
                Integer entrada = rs.getInt("Entrada");
                Integer saida = rs.getInt("Saída");
                Integer estoqueAtual = rs.getInt("EstoqueAtual");
                String detalhes = rs.getString("Detalhes");


                System.out.println("===============================================");
                System.out.println("ID Movimentação: " + idMovimentacao);
                System.out.println("Produto: " + produto);
                System.out.println("Entrada: " + entrada);
                System.out.println("Saída: " + saida);
                System.out.println("Estoque Atual: " + estoqueAtual);
                System.out.println("Detalhes: " + detalhes);
                System.out.println("===============================================");

            }
        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            // Fecha os recursos abertos (ResultSet e Statement).
            DB.closeResultSet(rs);
            DB.closeStatement(preparedStatement);
        }

    }

    /**
     * Gera um relatório de produtos com baixo estoque chamando uma stored procedure.
     */
    public void relatorioBaixoEstoque(int estoqueMinimo) {

        String sql = "{CALL RelatorioBaixoEstoque(?)}"; // Chama a stored procedure

        ResultSet rs = null;

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, estoqueMinimo);
            rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum produto encontrado!");
                return;
            }

            System.out.println("======== Relatório de Produtos com Baixo Estoque ========");
            while (rs.next()) {
                int idProduto = rs.getInt("ID");
                String nomeProduto = rs.getString("Nome do Produto");
                int qtdEstoque = rs.getInt("Quantidade em Estoque");

                System.out.println("-----------------------------------------------");
                System.out.println("ID Produto: " + idProduto);
                System.out.println("Nome Produto: " + nomeProduto);
                System.out.println("Quantidade em Estoque: " + qtdEstoque);
                System.out.println("-----------------------------------------------");
            }

        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    /**
     * Gera um relatório de vendas e lucros chamando uma stored procedure.
     */
    public void vendasELucros() {
        String sql = "{CALL RelatorioVendasELucro()}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma venda foi encontrada!");
                return;
            }

            while (rs.next()) {
                int produtoID = rs.getInt("ProdutoID");
                String nome = rs.getString("Nome");
                int quantidadeVendida = rs.getInt("QuantidadeVendida");
                double totalVendas = rs.getDouble("TotalVendas");
                double custoTotal = rs.getDouble("CustoTotal");
                double lucroTotal = rs.getDouble("LucroTotal");

                System.out.printf("ProdutoID: %d, Nome: %s, Quantidade Vendida: %d, Total Vendas: %.2f, Custo Total: %.2f, Lucro Total: %.2f%n",
                        produtoID, nome, quantidadeVendida, totalVendas, custoTotal, lucroTotal);
            }

        } catch (SQLException e) {
            throw new DbExecption("Erro ao executar RelatorioVendasELucro: " + e.getMessage());
        }
    }
}
