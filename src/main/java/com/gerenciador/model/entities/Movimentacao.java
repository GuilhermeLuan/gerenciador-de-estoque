package com.gerenciador.model.entities;
import java.util.Objects;

public class Movimentacao {
    private Integer idMovimentacao;
    private Integer idProduto;
    private Integer qtdEstoque;
    private Integer entradaDeProduto;
    private Integer saidaDeProduto;
    private String movimentacaoCol;

    public Movimentacao() {
    }

    public Movimentacao(Integer idMovimentacao, Integer idProduto, Integer qtdEstoque, Integer entradaDeProduto, Integer saidaDeProduto, String movimentacaoCol) {
        this.idMovimentacao = idMovimentacao;
        this.idProduto = idProduto;
        this.qtdEstoque = qtdEstoque;
        this.entradaDeProduto = entradaDeProduto;
        this.saidaDeProduto = saidaDeProduto;
        this.movimentacaoCol = movimentacaoCol;
    }

    public Movimentacao(Integer idProduto, Integer qtdEstoque, Integer entradaDeProduto, Integer saidaDeProduto, String movimentacaoCol) {
        this.idProduto = idProduto;
        this.qtdEstoque = qtdEstoque;
        this.entradaDeProduto = entradaDeProduto;
        this.saidaDeProduto = saidaDeProduto;
        this.movimentacaoCol = movimentacaoCol;
    }

    public Integer getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(Integer idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getEntradaDeProduto() {
        return entradaDeProduto;
    }

    public void setEntradaDeProduto(Integer entradaDeProduto) {
        this.entradaDeProduto = entradaDeProduto;
    }

    public Integer getSaidaDeProduto() {
        return saidaDeProduto;
    }

    public void setSaidaDeProduto(Integer saidaDeProduto) {
        this.saidaDeProduto = saidaDeProduto;
    }

    public String getMovimentacaoCol() {
        return movimentacaoCol;
    }

    public void setMovimentacaoCol(String movimentacaoCol) {
        this.movimentacaoCol = movimentacaoCol;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "idMovimentacao=" + idMovimentacao +
                ", idProduto=" + idProduto +
                ", qtdEstoque=" + qtdEstoque +
                ", entradaDeProduto=" + entradaDeProduto +
                ", saidaDeProduto=" + saidaDeProduto +
                ", movimentacaoCol='" + movimentacaoCol + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movimentacao that)) return false;

        return Objects.equals(getIdMovimentacao(), that.getIdMovimentacao());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIdMovimentacao());
    }
}
