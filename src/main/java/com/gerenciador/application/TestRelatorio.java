package com.gerenciador.application;

import com.gerenciador.model.dao.DaoFactory;
import com.gerenciador.service.Relatorio;

public class TestRelatorio {
    public static void main(String[] args) {
        Relatorio relatorio = DaoFactory.createRelatorio();

        System.out.println("Relatório de Produtos Cadastrados");
        relatorio.produtosCadastrados();

        System.out.println("Relatório de vendas e lucro (baseado no preço de compra e venda)");
        relatorio.vendasELucros();
    }
}
