package com.gerenciador.model.entities;

public class Categoria {
    private int idCategoria;
    private String nomeCategoria;
    private String descricao;

    public Categoria(int idCategoria, String nomeCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    public Categoria(String nomeCategoria, String descricao) {
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public static final class CategoriaBuilder {
        private int idCategoria;
        private String nomeCategoria;
        private String descricao;

        private CategoriaBuilder() {}

        public static CategoriaBuilder aCategoria() {return new CategoriaBuilder();}

        public CategoriaBuilder withIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
            return this;
        }

        public CategoriaBuilder withNomeCategoria(String nomeCategoria) {
            this.nomeCategoria = nomeCategoria;
            return this;
        }

        public CategoriaBuilder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Categoria build() {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setNomeCategoria(nomeCategoria);
            categoria.setDescricao(descricao);
            return categoria;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;

        return getIdCategoria() == categoria.getIdCategoria();
    }

    @Override
    public int hashCode() {
        return getIdCategoria();
    }
}
