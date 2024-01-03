package org.example.service;

import org.example.db.migration.FlywayMigration;
import org.example.entity.Client;
import org.example.exeption.NoSuchClientException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientServiceIntegrationTest {
    private static int dynamicPort;
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpassword");
    private ClientService clientService;
    private Connection connection;

    @BeforeEach
    void setUp() {
        postgres.start();
        dynamicPort = postgres.getMappedPort(5432);
         new FlywayMigration(postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword());
        try {
            connection = DriverManager.getConnection(
                    postgres.getJdbcUrl(),
                    postgres.getUsername(),
                    postgres.getPassword());
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
        postgres.stop();
    }


    @Test
    @Order(1)
    public void testWithPostgresContainer() {
        String jdbcUrl = postgres.getJdbcUrl();
        Assertions.assertEquals("jdbc:postgresql://localhost:" + dynamicPort + "/test?loggerLevel=OFF", jdbcUrl);
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"TestName"})
    void testThatCreateMethodCreateCorrectlyWithExistingID(String input) {
        long id = clientService.create(input);
        String getByID = clientService.getById(id);
        Assertions.assertEquals(getByID, input);
    }
    @ParameterizedTest
    @Order(3)
    @ValueSource(longs = {Long.MAX_VALUE-1L, Long.MAX_VALUE})
    void testThatCreateMethodCreateCorrectlyWithNotExistingID(long input) {
        Assertions.assertFalse(clientService.ifClientExist(input));
    }
    @ParameterizedTest
    @Order(4)
    @ValueSource(strings = {"TestName"})
    void testThatIfClientExistMethodCreateCorrectly(String input) {
        long id = clientService.create(input);
        Assertions.assertTrue(clientService.ifClientExist(id));
    }
    @ParameterizedTest
    @Order(5)
    @ValueSource(longs = {Long.MAX_VALUE-1L, Long.MAX_VALUE})
    void testThatGetByIdMethodThrowsNoSuchClientExceptionWhenInvalidArgumentsReceived(long input) {
        Assertions.assertThrows(NoSuchClientException.class, () -> {
            clientService.getById(input);
        });
    }
    @ParameterizedTest
    @Order(6)
    @ValueSource(longs = {Long.MAX_VALUE-1L, Long.MAX_VALUE})
    void testThatSetNameMethodThrowsNoSuchClientExceptionWhenInvalidArgumentsReceived(long input) {
        Assertions.assertThrows(NoSuchClientException.class, () -> {
            clientService.setName(input, "name");
        });
    }
    @ParameterizedTest
    @Order(7)
    @ValueSource(strings = {"TestName"})
    void testThatSetNameMethodSetNameCorrectly(String input){
        long id = clientService.create(input);
        String newName = "New Name";
        clientService.setName(id, newName);
        Assertions.assertEquals(newName, clientService.getById(id));
    }
    @ParameterizedTest
    @Order(8)
    @ValueSource(longs = {Long.MAX_VALUE-1L, Long.MAX_VALUE})
    void testThatDeleteByIdMethodDeleteClientThrowsNoSuchClientExceptionWhenInvalidArgumentsReceived(long input){
        Assertions.assertThrows(NoSuchClientException.class, () -> {
            clientService.deleteById(input);
        });
    }
    @ParameterizedTest
    @Order(9)
    @ValueSource(strings = {"TestName"})
    void testThatDeleteByIdMethodDeleteClientCorrectly(String input){
        long id = clientService.create(input);
        clientService.deleteById(id);
        Assertions.assertThrows(NoSuchClientException.class, () -> {
            clientService.getById(id);
        });
    }
    @Test
    @Order(10)
    void testThatListAllMethodReturnAllClients(){
        List<Client> clientsList = List.of(Client.builder()
                        .id(1L)
                        .name("Philip J. Fry")
                        .build(),
                Client.builder()
                        .id(2L)
                        .name("Turanga Leela")
                        .build(),
                Client.builder()
                        .id(3L)
                        .name("Bender Bending Rodriguez")
                        .build(),
                Client.builder()
                        .id(4L)
                        .name("Professor Hubert J. Farnsworth")
                        .build(),
                Client.builder()
                        .id(5L)
                        .name("Amy Wong")
                        .build()
        );
        Assertions.assertEquals(clientsList, clientService.listAll());
    }
}