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
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Produto_has_Categoria_Categoria1`
        FOREIGN KEY (`Categoria_idCategoria`)
            REFERENCES `mydb`.`Categoria` (`idCategoria`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Movimentacao`
(
    `idMovimentacao`   INT         NOT NULL AUTO_INCREMENT,
    `idProduto`        INT         NOT NULL,
    `QtdEstoque`       INT         NOT NULL,
    `EntradaDeProduto` INT         NOT NULL,
    `SaidaDeProduto`   INT         NOT NULL,
    `Movimentacaocol`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idMovimentacao`)
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS HistoricoMovimentacao
(
    idHistorico      INT AUTO_INCREMENT PRIMARY KEY,
    idProduto        INT          NOT NULL,
    NomeProduto      VARCHAR(100) NOT NULL,
    QtdAntes         INT          NOT NULL,
    QtdDepois        INT          NOT NULL,
    TipoMovimentacao VARCHAR(20)  NOT NULL, -- Entrada ou Saída
    Quantidade       INT          NOT NULL,
    DataMovimentacao DATETIME DEFAULT CURRENT_TIMESTAMP
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

-- III - Relatório de produtos com baixo estoque;
DELIMITER //
CREATE PROCEDURE RelatorioBaixoEstoque(IN estoqueMinimo INT )
BEGIN
    SELECT IdProduto   AS 'ID',
           NomeProduto AS 'Nome do Produto',
           QtdEstoque  AS 'Quantidade em Estoque'
    FROM Produto
    WHERE QtdEstoque < estoqueMinimo;
END //
DELIMITER ;

-- CALL RelatorioProdutosBaixoEstoque(10);
-- CHAMAMENTO DA PROCEDURE COM LIMITE DE 10 UNDS


-- IV - Relatório de vendas e lucro (baseado no preço de compra e venda).
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

-- chamando a procedure
-- call CadastrarCategoria('Produtos de higiene', 'Produtos para higiene pessoal');
-- conferindo a inserção
# select *
# from Categoria;


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

UPDATE Produto
SET QtdEstoque = 1
WHERE IdProduto = 1;



-- Trigger - Entradas e Saidas
DELIMITER $$

CREATE TRIGGER after_movimentacao_insert
    AFTER INSERT ON Movimentacao
    FOR EACH ROW
BEGIN
    DECLARE qtd_antes INT;

    -- Buscar quantidade de estoque antes da movimentação
    SELECT QtdEstoque INTO qtd_antes
    FROM Produto
    WHERE IdProduto = NEW.idProduto;

    -- Inserir no histórico
    INSERT INTO HistoricoMovimentacao (
        idProduto,
        NomeProduto,
        QtdAntes,
        QtdDepois,
        TipoMovimentacao,
        Quantidade
    )
    VALUES (
               NEW.idProduto,
               (SELECT NomeProduto FROM Produto WHERE IdProduto = NEW.idProduto),
               qtd_antes,
               NEW.QtdEstoque,
               CASE
                   WHEN NEW.EntradaDeProduto > 0 THEN 'Entrada'
                   ELSE 'Saída'
                   END,
               CASE
                   WHEN NEW.EntradaDeProduto > 0 THEN NEW.EntradaDeProduto
                   ELSE NEW.SaidaDeProduto
                   END
           );
END$$

DELIMITER ;

