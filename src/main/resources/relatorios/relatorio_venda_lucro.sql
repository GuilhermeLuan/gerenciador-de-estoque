DELIMITER //

CREATE PROCEDURE RelatorioVendasELucro()
BEGIN
    SELECT p.IdProduto                                                AS ProdutoID,
           p.NomeProduto                                              AS Nome,
           SUM(m.SaidaDeProduto)                                      AS QuantidadeVendida,
           SUM(m.SaidaDeProduto * p.PrecoDeVenda)                     AS TotalVendas,
           SUM(m.SaidaDeProduto * p.PrecoDeCompra)                    AS CustoTotal,
           SUM(m.SaidaDeProduto * (p.PrecoDeVenda - p.PrecoDeCompra)) AS LucroTotal
    FROM Produto p
             JOIN
         Movimentacao m ON p.IdProduto = m.idProduto
    WHERE m.SaidaDeProduto > 0 -- Considera apenas saídas (vendas)
    GROUP BY p.IdProduto, p.NomeProduto;
END //

DELIMITER ;