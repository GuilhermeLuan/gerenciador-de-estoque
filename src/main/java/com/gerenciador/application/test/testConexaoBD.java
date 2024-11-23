package com.gerenciador.application.test;

import com.gerenciador.db.DB;

import java.sql.Connection;

public class testConexaoBD {
    public static void main(String[] args) {
        Connection connection = DB.getConnection();
        System.out.println(connection);

    }
}