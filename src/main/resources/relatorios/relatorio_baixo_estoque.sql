DELIMITER //
CREATE PROCEDURE RelatorioBaixoEstoque(IN estoqueMinimo INT)
BEGIN
    SELECT IdProduto   AS 'ID',
           NomeProduto AS 'Nome do Produto',
           QtdEstoque  AS 'Quantidade em Estoque'
    FROM Produto
    WHERE QtdEstoque < limite;
END //
DELIMITER ;