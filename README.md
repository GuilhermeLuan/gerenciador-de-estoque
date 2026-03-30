# Sistema de Cadastro de Usuários

O **Sistema de Cadastro de Usuários** é uma aplicação desenvolvida em Java para fins acadêmicos, demonstrando o uso do padrão de projeto DAO (Data Access Object) para gerenciar usuários em um banco de dados MySQL.

---

## 🛠️ Funcionalidades

1. **Gerenciamento de Usuários**
    - Cadastro de usuários com nome e email
    - Consulta, atualização e exclusão de usuários
    - Validação de email único no sistema

---

## 🧰 Tecnologias Utilizadas

- **Java 21**: Linguagem de programação
- **JDBC**: Acesso ao banco de dados relacional
- **Docker**: Criação do banco de dados em ambiente de desenvolvimento
- **MySQL 9.0**: Banco de dados utilizado para armazenar os dados da aplicação
- **Maven**: Gerenciamento de dependências e build
- **JUnit Jupiter**: Framework para testes unitários
- **Design Patterns**: Aplicação do padrão DAO (Data Access Object) e Factory

---

## 🏗️ Estrutura do Projeto

### 📁 `com.gerenciador.application`
Camada de aplicação (reservada para futuras implementações).

### 📁 `com.gerenciador.db`
Configurações de conexão com o banco de dados e tratamento de exceções específicas.

### 📁 `com.gerenciador.model`
Classes e interfaces responsáveis pela modelagem e persistência dos dados:
- **`entities`**: Representação da entidade Usuario
- **`dao`**: Interface UsuarioDao e implementação UsuarioDaoImpl para acesso aos dados

### 📁 `com.gerenciador.utils`
Utilitários para validações e tratamento de dados.

### 📁 `src/test`
Testes unitários utilizando JUnit Jupiter para validar a conexão com o banco de dados.

---

## ⚙️ Como Configurar o Projeto

### Pré-requisitos
- Java 21 ou superior
- Docker e Docker Compose instalados
- Maven instalado

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/GuilhermeLuan/gerenciador-de-estoque
   cd gerenciador-de-estoque
   ```

2. Inicie o banco de dados MySQL com Docker Compose:
   ```bash
   docker-compose up -d
   ```
   
   O banco de dados será criado automaticamente com a tabela `Usuario` através do script `sql.sql`.

3. Configure o arquivo de propriedades (já configurado por padrão):
   O arquivo `src/main/resources/db.properties` contém as credenciais:
   ```properties
   user=root
   password=admin
   dburl=jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false
   useSSL=false
   ```

4. Compile e execute os testes:
   ```bash
   ./mvnw clean test
   ```

5. Para buildar o projeto:
   ```bash
   ./mvnw clean install
   ```

---

## 📊 Estrutura do Banco de Dados

### Tabela Usuario
```sql
CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
```

---

## 🧪 Testes

O projeto inclui um teste JUnit para validar a conexão com o banco de dados:
- **TestConexaoDB**: Verifica se a conexão com o banco MySQL é estabelecida corretamente

Para executar os testes:
```bash
./mvnw test
```

---

## 📝 Uso do Padrão DAO

O projeto demonstra o uso do padrão DAO através de:
- **UsuarioDao** (Interface): Define os métodos CRUD
- **UsuarioDaoImpl** (Implementação): Implementa os métodos usando JDBC
- **DaoFactory**: Factory para criação de instâncias DAO

Exemplo de uso:
```java
// Criar uma instância do DAO
UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

// Inserir um novo usuário
Usuario usuario = new Usuario("João Silva", "joao@email.com");
usuarioDao.insert(usuario);

// Buscar todos os usuários
List<Usuario> usuarios = usuarioDao.findAll();
```

### Executar a aplicação de exemplo

Para executar a aplicação de demonstração que mostra todas as operações CRUD:

```bash
./mvnw compile exec:java -Dexec.mainClass="com.gerenciador.application.Main"
```

---

## 🎯 Objetivo Acadêmico

Este projeto foi desenvolvido para demonstrar:
- Implementação do padrão de projeto DAO
- Uso de JDBC para acesso a banco de dados
- Organização de código em camadas (Model, DAO, Utils)
- Criação de testes unitários com JUnit
- Uso de Docker para ambiente de desenvolvimento