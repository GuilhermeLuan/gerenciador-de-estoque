INSERT INTO Produto (NomeProduto, Descricao, QtdEstoque, PrecoDeCompra, PrecoDeVenda)
VALUES
    ('Sabonete Líquido', 'Sabonete líquido 500ml', 150, 5.50, 8.90),
    ('Shampoo Anticaspa', 'Shampoo 200ml para tratamento de caspa', 80, 15.00, 25.00),
    ('Pasta de Dente', 'Creme dental com flúor 90g', 200, 3.20, 5.50),
    ('Desodorante Roll-on', 'Desodorante 50ml sem álcool', 120, 6.00, 10.00),
    ('Escova de Dentes', 'Escova de dentes com cerdas macias', 300, 2.50, 4.90);

INSERT INTO Categoria (NomeCategoria, Descricao)
VALUES
    ('Higiene Pessoal', 'Produtos destinados ao cuidado diário pessoal'),
    ('Beleza', 'Produtos para cuidados estéticos e cosméticos'),
    ('Limpeza', 'Produtos para limpeza doméstica'),
    ('Saúde', 'Produtos para cuidado com a saúde e bem-estar'),
    ('Acessórios', 'Acessórios diversos para uso cotidiano');

INSERT INTO Produto_has_Categoria (Produto_IdProduto, Categoria_idCategoria)
VALUES
    (1, 1), -- Sabonete Líquido em Higiene Pessoal
    (2, 1), -- Shampoo Anticaspa em Higiene Pessoal
    (3, 1), -- Pasta de Dente em Higiene Pessoal
    (4, 1), -- Desodorante Roll-on em Higiene Pessoal
    (5, 5); -- Escova de Dentes em Acessórios

INSERT INTO Movimentacao (idProduto, QtdEstoque, EntradaDeProduto, SaidaDeProduto, Movimentacaocol)
VALUES
    (1, 150, 50, 0, 'Entrada inicial de sabonetes'),
    (2, 80, 30, 0, 'Recebimento de shampoos'),
    (3, 200, 100, 0, 'Reposição de creme dental'),
    (4, 120, 40, 0, 'Novo lote de desodorantes'),
    (5, 300, 150, 0, 'Chegada de escovas de dente');

INSERT INTO HistoricoMovimentacao (idProduto, NomeProduto, QtdAntes, QtdDepois, TipoMovimentacao, Quantidade, DataMovimentacao)
VALUES
    (1, 'Sabonete Líquido', 100, 150, 'Entrada', 50, '2024-11-19 10:00:00'),
    (2, 'Shampoo Anticaspa', 50, 80, 'Entrada', 30, '2024-11-19 11:00:00'),
    (3, 'Pasta de Dente', 150, 200, 'Entrada', 50, '2024-11-19 12:00:00'),
    (4, 'Desodorante Roll-on', 80, 120, 'Entrada', 40, '2024-11-19 13:00:00'),
    (5, 'Escova de Dentes', 250, 300, 'Entrada', 50, '2024-11-19 14:00:00');