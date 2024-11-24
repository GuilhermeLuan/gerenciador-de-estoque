package com.gerenciador.model.entities;

/**
 * Classe que representa uma Categoria no Banco de Dados.
 */
public class Categoria {
    // Atributos da classe

    private int idCategoria;
    private String nomeCategoria;
    private String descricao;

    //Construtores
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

    // Métodos getter e setter
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

    /**
     * Sobrescreve o método toString para fornecer uma representação textual.
     */
    @Override
    public String toString() {
        return System.lineSeparator() +
               "    ID: " + idCategoria + "," + System.lineSeparator() +
               "    Nome: '" + nomeCategoria + "'," + System.lineSeparator() +
               "    Descrição: '" + descricao + "'" + System.lineSeparator();
    }

    /**
     * Equals e HasCode para comprar objetos
     */
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
