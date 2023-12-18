package org.example.db;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class Database {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String USER_NAME = "your_user_name";
    private static final String PASSWORD = "your_password";
    private static Database database;
    private Connection connection;

    private Database() {

    }

    public static Database getInstance() {
        if (Objects.isNull(database)) {
            database = new Database();
            return database;
        }
        return database;
    }

    @SneakyThrows
    public Connection getConnection() {
        if (Objects.isNull(connection)) {
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            return connection;
        }
        return connection;
    }
    @SneakyThrows
    public void closeConnection(){
        connection.close();
    }
}
