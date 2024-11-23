INSERT INTO Produto (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda)
VALUES
    ('Celular Galaxy S23', 'Smartphone da Samsung com 256GB', 50, 3000.00, 4000.00),
    ('Notebook Dell XPS 13', 'Notebook ultrafino com processador i7', 20, 5000.00, 6500.00),
    ('Fone de Ouvido Bluetooth JBL', 'Fone de ouvido sem fio com cancelamento de ruído', 100, 150.00, 300.00);

INSERT INTO Categoria (NomeCategoria, Descricao)
VALUES
    ('Eletronicos', 'Produtos eletrônicos em geral'),
    ('Informatica', 'Acessórios e dispositivos de informática'),
    ('Áudio e Som', 'Equipamentos relacionados ao áudio e som');

INSERT INTO Produto_has_Categoria (Produto_IdProduto, Categoria_idCategoria)
VALUES
    (1, 1), -- Galaxy S23 na categoria Eletrônicos
    (2, 2), -- Notebook Dell na categoria Informática
    (3, 3); -- Fone JBL na categoria Áudio e Som

INSERT INTO MovimentacaoEstoque (produto_id, tipo_movimentacao, quantidade)
VALUES
    (1, 'ENTRADA', 30), -- Entrada de 30 unidades do Galaxy S23
    (2, 'SAIDA', 5),    -- Saída de 5 unidades do Notebook Dell
    (3, 'ENTRADA', 50); -- Entrada de 50 unidades do Fone JBL
