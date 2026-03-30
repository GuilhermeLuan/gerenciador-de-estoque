package com.gerenciador.db;

import org.junit.jupiter.api.*;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


public class TestConexaoDB {

    @Test
    @DisplayName("Deve estabelecer conexão com o banco de dados")
    public void testConexaoComBancoDeDados() {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            
            assertNotNull(connection, "A conexão com o banco de dados não deve ser nula");
            assertFalse(connection.isClosed(), "A conexão deve estar aberta");
            
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            fail("Falha ao conectar com o banco de dados: " + e.getMessage());
        } finally {
            DB.closeConnection();
        }
    }
}
