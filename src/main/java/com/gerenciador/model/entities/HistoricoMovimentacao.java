package com.gerenciador.model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class HistoricoMovimentacao {
    private Integer idHistorico;
    private Integer idProduto;
    private String nomeProduto;
    private Integer qtdAntes;
    private Integer qtdDepois;
    private String tipoMovimentacao; // Entrada ou Saída
    private Integer quantidade;
    private LocalDateTime dataMovimentacao;

    public HistoricoMovimentacao() {
    }

    public HistoricoMovimentacao(Integer idHistorico, Integer idProduto, String nomeProduto, Integer qtdAntes, Integer qtdDepois, String tipoMovimentacao, Integer quantidade, LocalDateTime dataMovimentacao) {
        this.idHistorico = idHistorico;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdAntes = qtdAntes;
        this.qtdDepois = qtdDepois;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
    }

    public HistoricoMovimentacao(Integer idProduto, String nomeProduto, Integer qtdAntes, Integer qtdDepois, String tipoMovimentacao, Integer quantidade, LocalDateTime dataMovimentacao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdAntes = qtdAntes;
        this.qtdDepois = qtdDepois;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
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

    public Integer getQtdAntes() {
        return qtdAntes;
    }

    public void setQtdAntes(Integer qtdAntes) {
        this.qtdAntes = qtdAntes;
    }

    public Integer getQtdDepois() {
        return qtdDepois;
    }

    public void setQtdDepois(Integer qtdDepois) {
        this.qtdDepois = qtdDepois;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    @Override
    public String toString() {
        return "HistoricoMovimentacao{" +
                "idHistorico=" + idHistorico +
                ", idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", qtdAntes=" + qtdAntes +
                ", qtdDepois=" + qtdDepois +
                ", tipoMovimentacao='" + tipoMovimentacao + '\'' +
                ", quantidade=" + quantidade +
                ", dataMovimentacao=" + dataMovimentacao +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoricoMovimentacao that)) return false;

        return Objects.equals(getIdHistorico(), that.getIdHistorico());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdHistorico());
    }
}
