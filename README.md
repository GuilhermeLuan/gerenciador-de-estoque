# Gerenciador de estoque

O **Gerenciador de Estoque** é uma aplicação desenvolvida em Java para o controle de produtos, categorias e movimentação de estoque. A aplicação permite gerenciar informações de produtos e categorias, registrar entradas e saídas de estoque, e gerar relatórios detalhados para auxiliar na administração.

---

## 🛠️ Funcionalidades

1. **Gerenciamento de Produtos**
    - Cadastro, edição, exclusão e consulta de produtos.

2. **Gerenciamento de Categorias**
    - Cadastro, edição, exclusão e consulta de categorias.

3. **Movimentação de Estoque**
    - Registro de entrada e saída de produtos no estoque.

4. **Relatórios**
    - Produtos cadastrados.
    - Movimentação de estoque.
    - Produtos com baixo estoque.
    - Vendas e lucros.

---

## 🧰 Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **JDBC**: Acesso ao banco de dados relacional.
- **Docker**: Criação do banco de dados em ambiente de produção.
- **MySQL**: Banco de dados utilizado para armazenar os dados da aplicação.
- **Maven**: Gerenciamento de dependências e build.
- **Design Patterns**: Aplicação de padrões como DAO e Factory.
---

## 🏗️ Estrutura do Projeto

### 📁 `com.gerenciador.application`
Contém a lógica da aplicação e as classes para gerenciar produtos, categorias, estoque e relatórios.

### 📁 `com.gerenciador.db`
Configurações de conexão com o banco de dados e exceções específicas.

### 📁 `com.gerenciador.model`
Classes e interfaces responsáveis pela modelagem e persistência dos dados:
- **`entities`**: Representação das entidades (Produto, Categoria, etc.).
- **`dao`**: Interfaces e implementações para acesso aos dados (DAO).

### 📁 `com.gerenciador.service`
Serviços para gerar relatórios.

### 📁 `com.gerenciador.utils`
Utilitários para validações e tratamento de dados.

---

## ⚙️ Como Configurar o Projeto

### Pré-requisitos
- Java 17 ou superior.
- MySQL configurado.
- Maven instalado.

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/GuilhermeLuan/gerenciador-de-estoque

2. Configure o banco de dados MySQL:
   Atualize o arquivo de configuração de conexão com o banco de dados em `src/main/resources/db.properties` com as credenciais corretas:
    ```bash
    user=root
    password=admin
    dburl=jdbc:mysql://localhost:3306/mydb
    useSSL=false
3. Rode o Maven
   ```bash
   .\mvnw clean install
4. Execute o script `sql.sql` para criação do banco e das tabelas.
5. Inicie a aplicação