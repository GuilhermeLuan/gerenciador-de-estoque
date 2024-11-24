-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto`
(
    `IdProduto`     INT UNSIGNED   NOT NULL AUTO_INCREMENT,
    `NomeProduto`   VARCHAR(100)   NOT NULL,
    `Descricao`     TEXT(100)      NULL,
    `QtdEstoque`    INT UNSIGNED   NOT NULL,
    `PrecoDeCompra` DECIMAL(10, 2) NOT NULL,
    `PrecoDeVenda`  DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (`IdProduto`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria`
(
    `idCategoria`   INT AUTO_INCREMENT,
    `NomeCategoria` VARCHAR(100) NOT NULL,
    `Descricao`     TEXT(100)    NOT NULL,
    PRIMARY KEY (`idCategoria`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto_has_Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto_has_Categoria`
(
    `Produto_IdProduto`     INT UNSIGNED NOT NULL,
    `Categoria_idCategoria` INT          NOT NULL,
    PRIMARY KEY (`Produto_IdProduto`, `Categoria_idCategoria`),
    INDEX `fk_Produto_has_Categoria_Categoria1_idx` (`Categoria_idCategoria` ASC) VISIBLE,
    INDEX `fk_Produto_has_Categoria_Produto_idx` (`Produto_IdProduto` ASC) VISIBLE,
    CONSTRAINT `fk_Produto_has_Categoria_Produto`
        FOREIGN KEY (`Produto_IdProduto`)
            REFERENCES `mydb`.`Produto` (`IdProduto`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_Produto_has_Categoria_Categoria1`
        FOREIGN KEY (`Categoria_idCategoria`)
            REFERENCES `mydb`.`Categoria` (`idCategoria`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.MovimentacaoEstoque
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    produto_id        INT UNSIGNED              NOT NULL,
    tipo_movimentacao ENUM ('ENTRADA', 'SAIDA') NOT NULL,
    quantidade        INT                       NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES Produto (IdProduto)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;


-- I -Relatório de produtos cadastrados (consultando todos os produtos do estoque)
DELIMITER //
CREATE PROCEDURE RelatorioDeProdutos()
BEGIN
    SELECT * FROM Produto;
END //
DELIMITER ;

-- II - Relatório de movimentação de estoque (entradas e saídas)
# SELECT m.Id                as "Id Movimentação",
#        p.NomeProduto       as "Produto",
#        m.tipo_movimentacao as "Tipo Movimentação",
#        m.quantidade        as "Quantidade",
#        m.data_movimentacao as "Data da Movimentação"
# FROM MovimentacaoEstoque m
#          JOIN Produto p ON m.produto_id = p.IdProduto
# ORDER BY m.id DESC;

-- III - Relatório de produtos com baixo estoque;
DELIMITER //
CREATE PROCEDURE RelatorioBaixoEstoque(IN estoqueMinimo INT)
BEGIN
    SELECT IdProduto   AS 'ID',
           NomeProduto AS 'Nome do Produto',
           QtdEstoque  AS 'Quantidade em Estoque'
    FROM Produto
    WHERE QtdEstoque < estoqueMinimo;
END //
DELIMITER ;

-- IV - Relatório de vendas e lucro (baseado no preço de compra e venda).
DELIMITER //

CREATE PROCEDURE RelatorioVendasELucro()
BEGIN
    -- Verificar se há registros de saída na tabela MovimentacaoEstoque
    IF (SELECT COUNT(*) FROM MovimentacaoEstoque WHERE tipo_movimentacao = 'SAIDA') = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Não há movimentações de saída registradas.';
    ELSE
        SELECT p.IdProduto                                                       AS ProdutoID,
               p.NomeProduto                                                     AS Nome,
               IFNULL(SUM(m.quantidade), 0)                                      AS QuantidadeVendida,
               IFNULL(SUM(m.quantidade * p.PrecoDeVenda), 0)                     AS TotalVendas,
               IFNULL(SUM(m.quantidade * p.PrecoDeCompra), 0)                    AS CustoTotal,
               IFNULL(SUM(m.quantidade * (p.PrecoDeVenda - p.PrecoDeCompra)), 0) AS LucroTotal
        FROM Produto p
                 LEFT JOIN
             MovimentacaoEstoque m
             ON
                 p.IdProduto = m.produto_id
        WHERE m.tipo_movimentacao = 'SAIDA' -- Apenas registros com saída de produtos
        GROUP BY p.IdProduto, p.NomeProduto;
    END IF;
END //

DELIMITER ;

CALl RelatorioVendasELucro();


-- Procedure (OBRIGATORIA) para cadastrar os produtos
DELIMITER //
CREATE PROCEDURE CadastrarProdutos(
    IN NomeProduto VARCHAR(100),
    IN Descricao TEXT,
    IN QtdEstoque INT UNSIGNED,
    IN PrecoDeCompra DECIMAL(10, 2),
    IN PrecoDeVenda DECIMAL(10, 2)
)
BEGIN
    INSERT INTO Produto (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda)
    VALUES (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda);
END //
DELIMITER ;

-- Procedure(OBRIGATORIA) para cadastrar categorias
DELIMITER //
CREATE PROCEDURE CadastrarCategoria(
    IN NomeCategoria VARCHAR(100),
    IN Descricao TEXT
)
BEGIN
    INSERT INTO Categoria (NomeCategoria, Descricao)
    VALUES (NomeCategoria, Descricao);
END //
DELIMITER ;

-- Procedure para o registro de movimentação.

DELIMITER //
CREATE PROCEDURE RegistroMovimentacao(
    IN p_produto_id INT,
    IN p_tipo_movimentacao ENUM ('ENTRADA', 'SAIDA'),
    IN p_quantidade INT
)
BEGIN
    IF p_tipo_movimentacao = 'ENTRADA' THEN
        UPDATE Produto
        SET QtdEstoque = QtdEstoque + p_quantidade
        WHERE IdProduto = p_produto_id;

        INSERT INTO MovimentacaoEstoque (produto_id, tipo_movimentacao, quantidade)
        VALUES (p_produto_id, 'ENTRADA', p_quantidade);

    ELSEIF p_tipo_movimentacao = 'SAIDA' THEN
        UPDATE Produto
        SET QtdEstoque = QtdEstoque - p_quantidade
        WHERE IdProduto = p_produto_id;

        INSERT INTO MovimentacaoEstoque (produto_id, tipo_movimentacao, quantidade)
        VALUES (p_produto_id, 'SAIDA', p_quantidade);
    END IF;
END //

DELIMITER ;


-- trigger para Verificar Estoque Baixo: ao atualizar a quantidade em estoque de um Produto, deve ser disparado um alerta se a quantidad estiver abaixo do mínimo.
DELIMITER //

CREATE TRIGGER VerificarEstoque
    AFTER UPDATE
    ON Produto
    FOR EACH ROW
BEGIN
    IF NEW.QtdEstoque < 10 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Esse Produto está com o estoque abaixo do mínimo.';
    END IF;
END //

DELIMITER ;

DELIMITER //
