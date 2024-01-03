package org.example.service;
import org.example.constance.Constance;
import org.example.db.migration.FlywayMigration;
import org.example.exeption.IllegalNameException;
import org.example.util.PropertiesUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientServiceUnitTest {
    private ClientService clientService;
    private Connection connection;
    @BeforeEach
    void setUp() {

        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(Constance.DB_URL),
                    PropertiesUtil.get(Constance.DB_USER_NAME),
                    PropertiesUtil.get(Constance.DB_PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clientService = new ClientService(connection);
    }
    @AfterEach
    void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @ParameterizedTest
    @Order(1)
    @MethodSource("generateStreamString")
    void testThatCreateMethodThrowsIllegalNameExceptionWhenInvalidArgumentsReceived(String input) {
        Assertions.assertThrows(IllegalNameException.class, () -> {
            clientService.create(input);
        });
    }
    @ParameterizedTest
    @Order(2)
    @NullSource
    void testThatCreateMethodThrowsNullPointerExceptionWhenNullGiven(String input) {
        Assertions.assertThrows(NullPointerException.class, () -> {
            clientService.create(input);
        });
    }
    private static Stream<String> generateStreamString() {
        return Stream.of(
                generateString(1),
                generateString(1001)

        );
    }

    private static String generateString(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
