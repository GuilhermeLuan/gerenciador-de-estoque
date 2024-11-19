package com.gerenciador.model.entities;

import java.util.Objects;

public class Produto {
    private Integer idProduto;
    private String nomeProduto;
    private String descricao;
    private Integer qtdEstoque;
    private Double precoDeCompra;
    private Double precoDeVenda;

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


    public static final class ProdutoBuilder {
        private Integer idProduto;
        private String nomeProduto;
        private String descricao;
        private Integer qtdEstoque;
        private Double precoDeCompra;
        private Double precoDeVenda;

        private ProdutoBuilder() {}

        public static ProdutoBuilder aProduto() {return new ProdutoBuilder();}

        public ProdutoBuilder withIdProduto(Integer idProduto) {
            this.idProduto = idProduto;
            return this;
        }

        public ProdutoBuilder withNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
            return this;
        }

        public ProdutoBuilder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public ProdutoBuilder withQtdEstoque(Integer qtdEstoque) {
            this.qtdEstoque = qtdEstoque;
            return this;
        }

        public ProdutoBuilder withPrecoDeCompra(Double precoDeCompra) {
            this.precoDeCompra = precoDeCompra;
            return this;
        }

        public ProdutoBuilder withPrecoDeVenda(Double precoDeVenda) {
            this.precoDeVenda = precoDeVenda;
            return this;
        }

        public Produto build() {
            Produto produto = new Produto();
            produto.setIdProduto(idProduto);
            produto.setNomeProduto(nomeProduto);
            produto.setDescricao(descricao);
            produto.setQtdEstoque(qtdEstoque);
            produto.setPrecoDeCompra(precoDeCompra);
            produto.setPrecoDeVenda(precoDeVenda);
            return produto;
        }
    }

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
