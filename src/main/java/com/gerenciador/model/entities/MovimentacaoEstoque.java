package com.gerenciador.model.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class MovimentacaoEstoque {

    private int idMovimentacao;
    private int produtoID;
    private int quantidade;
    private LocalDateTime dataMovimentacao;
    private TipoMovimentacao tipoMovimentacao;

    // Enum para tipo de movimentação
    public enum TipoMovimentacao {
        ENTRADA, SAIDA
    }


    // Construtor da classe

    public MovimentacaoEstoque() {
    }

    public MovimentacaoEstoque(int produtoID, int quantidade, TipoMovimentacao tipoMovimentacao) {
        this.produtoID = produtoID;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public MovimentacaoEstoque(int idMovimentacao, int produtoID, int quantidade, LocalDateTime dataMovimentacao, TipoMovimentacao tipoMovimentacao) {
        this.idMovimentacao = idMovimentacao;
        this.produtoID = produtoID;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    // Getters e Setters


    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    // Método toString para exibição
    @Override
    public String toString() {
        return "MovimentacaoEstoque" +
               "idMovimentacao=" + idMovimentacao +
               ", produto=" + produtoID +
               ", quantidade=" + quantidade +
               ", dataMovimentacao=" + dataMovimentacao +
               ", tipoMovimentacao=" + tipoMovimentacao;
    }
}

