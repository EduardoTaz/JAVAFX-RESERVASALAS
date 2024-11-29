package com.example.reservasala.model.database;

public class DatabaseFactory {
    // CLASES DO TIPO STATIC NÃO SÃO INSTANCIADAS

    public static Database getDatabase(String nome) {
        if (nome.equals("postgreesql")) {
            return new DatabasePostGreeSQL();
        } else {
            return new DatabaseMySQL();
        }
    }
}
