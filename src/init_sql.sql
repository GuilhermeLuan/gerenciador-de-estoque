-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto` (
                                                `IdProduto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                `NomeProduto` VARCHAR(100) NOT NULL,
                                                `Descricao` TEXT(100) NULL,
                                                `QtdEstoque` INT UNSIGNED NOT NULL,
                                                `PrecoDeCompra` DECIMAL(10,2) NOT NULL,
                                                `PrecoDeVenda` DECIMAL(10,2) NOT NULL,
                                                PRIMARY KEY (`IdProduto`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categoria` (
                                                  `idCategoria` INT  AUTO_INCREMENT,
                                                  `NomeCategoria` VARCHAR(100) NOT NULL,
                                                  `Descricao` TEXT(100) NOT NULL,
                                                  PRIMARY KEY (`idCategoria`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto_has_Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto_has_Categoria` (
                                                              `Produto_IdProduto` INT UNSIGNED NOT NULL,
                                                              `Categoria_idCategoria` INT NOT NULL,
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
                                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Movimentacao` (
                                                     `idMovimentacao` INT NOT NULL AUTO_INCREMENT,
                                                     `idProduto` INT NOT NULL,
                                                     `QtdEstoque` INT NOT NULL,
                                                     `EntradaDeProduto` INT NOT NULL,
                                                     `SaidaDeProduto` INT NOT NULL,
                                                     `Movimentacaocol` VARCHAR(45) NOT NULL,
                                                     PRIMARY KEY (`idMovimentacao`))
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;