package com.gerenciador.model.entities;

import java.util.Objects;


/**
 * Classe que representa um Produto no Banco de Dados.
 */
public class Produto {
    private Integer idProduto;
    private String nomeProduto;
    private String descricao;
    private Integer qtdEstoque;
    private Double precoDeCompra;
    private Double precoDeVenda;

    //Construtores

    public Produto() {
    }

    public Produto(Integer idProduto, String nomeProduto, String descricao, Integer qtdEstoque, Double precoDeCompra, Double precoDeVenda) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.precoDeCompra = precoDeCompra;
        this.precoDeVenda = precoDeVenda;
    }

    public Produto(String nomeProduto, String descricao, Integer qtdEstoque, Double precoDeCompra, Double precoDeVenda) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.precoDeCompra = precoDeCompra;
        this.precoDeVenda = precoDeVenda;
    }

    // Métodos getter e setter

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Double getPrecoDeCompra() {
        return precoDeCompra;
    }

    public void setPrecoDeCompra(Double precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }

    public Double getPrecoDeVenda() {
        return precoDeVenda;
    }

    public void setPrecoDeVenda(Double precoDeVenda) {
        this.precoDeVenda = precoDeVenda;
    }

    /**
     * Sobrescreve o método toString para fornecer uma representação textual.
     */
    @Override
    public String toString() {
        return System.lineSeparator() +
               "    ID: " + idProduto + "," + System.lineSeparator() +
               "    Nome: '" + nomeProduto + "'," + System.lineSeparator() +
               "    Descrição: '" + descricao + "'," + System.lineSeparator() +
               "    Quantidade em Estoque: " + qtdEstoque + "," + System.lineSeparator() +
               "    Preço de Compra: R$" + precoDeCompra + "," + System.lineSeparator() +
               "    Preço de Venda: R$" + precoDeVenda + System.lineSeparator();
    }


    /**
     * Equals e HasCode para comprar objetos
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;

        return Objects.equals(getIdProduto(), produto.getIdProduto());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdProduto());
    }
}
