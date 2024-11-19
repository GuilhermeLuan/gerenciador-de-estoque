package com.gerenciador.model.entities;

import java.util.Objects;

public class ProdutoHasCategoria {
    private Integer produtoIdProduto;
    private Integer categoriaIdCategoria;

    public ProdutoHasCategoria(Integer produtoIdProduto, Integer categoriaIdCategoria) {
        this.produtoIdProduto = produtoIdProduto;
        this.categoriaIdCategoria = categoriaIdCategoria;
    }

    public Integer getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(Integer produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public Integer getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(Integer categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
    }


    public static final class ProdutoHasCategoriaBuilder {
        private Integer produtoIdProduto;
        private Integer categoriaIdCategoria;

        private ProdutoHasCategoriaBuilder() {}

        public static ProdutoHasCategoriaBuilder aProdutoHasCategoria() {return new ProdutoHasCategoriaBuilder();}

        public ProdutoHasCategoriaBuilder withProdutoIdProduto(Integer produtoIdProduto) {
            this.produtoIdProduto = produtoIdProduto;
            return this;
        }

        public ProdutoHasCategoriaBuilder withCategoriaIdCategoria(Integer categoriaIdCategoria) {
            this.categoriaIdCategoria = categoriaIdCategoria;
            return this;
        }

        public ProdutoHasCategoria build() {return new ProdutoHasCategoria(produtoIdProduto, categoriaIdCategoria);}
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoHasCategoria that)) return false;

        return Objects.equals(getProdutoIdProduto(), that.getProdutoIdProduto()) && Objects.equals(getCategoriaIdCategoria(), that.getCategoriaIdCategoria());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getProdutoIdProduto());
        result = 31 * result + Objects.hashCode(getCategoriaIdCategoria());
        return result;
    }
}
