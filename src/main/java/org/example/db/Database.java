package org.example.db;

import lombok.SneakyThrows;
import org.example.constance.Constance;
import org.example.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private static final int TIMEOUT = 1000;
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
            setConnection();
            return connection;
        }
        if (!connection.isValid(TIMEOUT)) {
            setConnection();
            return connection;
        }
        return connection;
    }

    @SneakyThrows
    public void closeConnection() {
        connection.close();
    }

    @SneakyThrows
    private void setConnection() {
        connection = DriverManager.getConnection(
                PropertiesUtil.get(Constance.DB_URL),
                PropertiesUtil.get(Constance.DB_USER_NAME),
                PropertiesUtil.get(Constance.DB_PASSWORD));
    }
}
