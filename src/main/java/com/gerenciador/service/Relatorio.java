package com.gerenciador.service;

import com.gerenciador.model.entities.Produto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Relatorio {

    private final Connection conn;

    public Relatorio(Connection conn) {
        this.conn = conn;
    }

    public void produtosCadastrados() {
        String sql = "{CALL RelatorioDeProdutos()}";

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

                System.out.println(produto);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar Relatorio de Produtos Cadastrados: " + e.getMessage(), e);
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
