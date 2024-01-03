package org.example.constance;

public class Constance {
    public static final String DB_URL = "db.url";
    public static final String DB_USER_NAME = "db.username";
    public static final String DB_PASSWORD = "db.password";
    public static final String CREATE_CLIENT = "INSERT INTO client (name) VALUES (?);";
    public static final String GET_CLIENT_NAME_BY_ID = "SELECT name FROM client WHERE id = ?;";
    public static final String GET_CLIENT_BY_ID = "SELECT id FROM client WHERE id = ?;";
    public static final String GET_CLIENT_WITH_MAX_ID = "SELECT max(id) FROM client;";
    public static final String GET_ALL_CLIENT = "SELECT * FROM client;";
    public static final String UPDATE_CLIENT_NAME = "UPDATE client SET name = ? WHERE id = ?;";
    public static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE id = ?;";
}
