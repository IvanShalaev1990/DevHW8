package org.example;

import lombok.SneakyThrows;
import org.example.db.Database;

import java.sql.Connection;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        System.out.println(connection.isValid(10));
    }
}