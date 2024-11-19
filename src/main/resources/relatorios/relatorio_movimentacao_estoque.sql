SELECT m.idMovimentacao   AS IDMovimentação,
       p.NomeProduto      AS Produto,
       m.EntradaDeProduto AS Entrada,
       m.SaidaDeProduto   AS Saída,
       m.QtdEstoque       AS EstoqueAtual,
       m.Movimentacaocol  AS Detalhes
FROM Movimentacao m
         JOIN
     Produto p ON m.idProduto = p.IdProduto
ORDER BY m.idMovimentacao DESC;