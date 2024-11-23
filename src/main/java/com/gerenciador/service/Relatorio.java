package com.gerenciador.service;

import com.gerenciador.db.DB;
import com.gerenciador.db.DbExecption;
import com.gerenciador.model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private final Connection conn;

    public Relatorio(Connection conn) {
        this.conn = conn;
    }

    public List<Produto> produtosCadastrados() {
        String sql = "{CALL RelatorioDeProdutos()}";

        List<Produto> produtos = new ArrayList<>();

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int produtoID = rs.getInt("IdProduto");
                String nome = rs.getString("NomeProduto");
                String descricao = rs.getString("Descricao");
                int quantidade = rs.getInt("QtdEstoque");
                Double precoDeCompra = rs.getDouble("PrecoDeCompra");
                Double precoDeVenda = rs.getDouble("PrecoDeVenda");

                Produto produto = new Produto(produtoID, nome, descricao, quantidade, precoDeCompra, precoDeVenda);

                produtos.add(produto);

            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar Relatorio de Produtos Cadastrados: " + e.getMessage(), e);
        }
    }

    public void movimentacaoEstoque() {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {

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
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(rs);
        }

    }

    public void relatorioBaixoEstoque(int estoqueMinimo) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            String sql = """
                    SELECT p.IdProduto       AS ID,
                           p.NomeProduto     AS NomeProduto,
                           p.QtdEstoque      AS QtdEstoque
                    FROM Produto p
                    WHERE p.QtdEstoque < ?
                    ORDER BY p.QtdEstoque ASC;""";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, estoqueMinimo);
            rs = preparedStatement.executeQuery();

            System.out.println("======== Relatório de Produtos com Baixo Estoque ========");
            while (rs.next()) {
                int idProduto = rs.getInt("ID");
                String nomeProduto = rs.getString("NomeProduto");
                int qtdEstoque = rs.getInt("QtdEstoque");

                System.out.println("-----------------------------------------------");
                System.out.println("ID Produto: " + idProduto);
                System.out.println("Nome Produto: " + nomeProduto);
                System.out.println("Quantidade em Estoque: " + qtdEstoque);
                System.out.println("-----------------------------------------------");
            }

        } catch (SQLException e) {
            throw new DbExecption(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(rs);
        }
    }


    public void vendasELucros() {
        String sql = "{CALL RelatorioVendasELucro()}";

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            ResultSet rs = stmt.executeQuery();

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
            throw new RuntimeException("Erro ao executar RelatorioVendasELucro: " + e.getMessage(), e);
        }
    }
}
