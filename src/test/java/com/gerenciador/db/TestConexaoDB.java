package com.gerenciador.db;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes JUnit para validar conexões com o banco de dados.
 * Inclui testes de sucesso e falha de conexão.
 */
public class TestConexaoDB {

    @Test
    @DisplayName("Deve estabelecer conexão com o banco de dados")
    public void testConexaoComBancoDeDados() {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            
            assertNotNull(connection, "A conexão com o banco de dados não deve ser nula");
            assertFalse(connection.isClosed(), "A conexão deve estar aberta");
            
            System.out.println("✓ Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            fail("Falha ao conectar com o banco de dados: " + e.getMessage());
        } finally {
            DB.closeConnection();
        }
    }

    @Test
    @DisplayName("Deve falhar ao conectar com credenciais inválidas")
    public void testFalhaConexaoCredenciaisInvalidas() {
        // Tentar conectar com senha incorreta
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "senha_errada");
        
        String url = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false";
        
        assertThrows(SQLException.class, () -> {
            DriverManager.getConnection(url, properties);
        }, "Deve lançar SQLException ao usar credenciais inválidas");
        
        System.out.println("✓ Teste de falha de credenciais passou!");
    }

    @Test
    @DisplayName("Deve falhar ao conectar com banco de dados inexistente")
    public void testFalhaConexaoBancoInexistente() {
        // Tentar conectar com banco de dados que não existe
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");
        
        String url = "jdbc:mysql://localhost:3306/banco_inexistente?allowPublicKeyRetrieval=true&useSSL=false";
        
        assertThrows(SQLException.class, () -> {
            DriverManager.getConnection(url, properties);
        }, "Deve lançar SQLException ao tentar conectar com banco inexistente");
        
        System.out.println("✓ Teste de banco inexistente passou!");
    }

    @Test
    @DisplayName("Deve falhar ao conectar com host inválido")
    public void testFalhaConexaoHostInvalido() {
        // Tentar conectar com host que não existe
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");
        
        String url = "jdbc:mysql://host_invalido:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false&connectTimeout=2000";
        
        assertThrows(SQLException.class, () -> {
            DriverManager.getConnection(url, properties);
        }, "Deve lançar SQLException ao tentar conectar com host inválido");
        
        System.out.println("✓ Teste de host inválido passou!");
    }
}
