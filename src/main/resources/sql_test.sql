CREATE TABLE MovimentacaoEstoque
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    produto_id        INT UNSIGNED              NOT NULL,
    tipo_movimentacao ENUM ('ENTRADA', 'SAIDA') NOT NULL,
    quantidade        INT                       NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES Produto (IdProduto)
);

DELIMITER //

CREATE PROCEDURE RegistrarMovimentacao(
    IN p_produto_id INT,
    IN p_tipo_movimentacao ENUM ('ENTRADA', 'SAIDA'),
    IN p_quantidade INT
)
BEGIN
    DECLARE v_novo_estoque INT;

    -- Verifica se o produto existe
    IF (SELECT COUNT(*) FROM Produto WHERE IdProduto = p_produto_id) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Produto não encontrado!';
    END IF;

    -- Calcula o novo estoque com base no tipo de movimentação
    IF p_tipo_movimentacao = 'ENTRADA' THEN
        UPDATE Produto
        SET QtdEstoque = QtdEstoque + p_quantidade
        WHERE IdProduto = p_produto_id;

    ELSEIF p_tipo_movimentacao = 'SAIDA' THEN
        -- Verifica se há estoque suficiente
        SELECT QtdEstoque
        INTO v_novo_estoque
        FROM Produto
        WHERE IdProduto = p_produto_id;

        IF v_novo_estoque < p_quantidade THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Estoque insuficiente!';
        END IF;

        UPDATE Produto
        SET QtdEstoque = QtdEstoque - p_quantidade
        WHERE IdProduto = p_produto_id;
    END IF;

    -- Registra a movimentação na tabela de movimentações
    INSERT INTO MovimentacaoEstoque (produto_id, tipo_movimentacao, quantidade)
    VALUES (p_produto_id, p_tipo_movimentacao, p_quantidade);
END //

DELIMITER ;
