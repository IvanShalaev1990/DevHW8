package org.example.db.init;

import lombok.SneakyThrows;
import org.example.db.Database;
import org.example.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static final String INIT_DB_PATH = "sql/init_db.sql";

    @SneakyThrows
    public static void main(String[] args) {
        Statement statement = Database.getInstance()
                .getConnection()
                .createStatement();
        Util util = new Util();
        util.listSQLCommandsFromFile(INIT_DB_PATH).forEach(it -> {
            try {
                statement.execute(it);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        Database.getInstance().closeConnection();
    }
}
