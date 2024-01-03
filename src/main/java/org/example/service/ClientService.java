package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.constance.Constance;
import org.example.entity.Client;
import org.example.exeption.IllegalNameException;
import org.example.exeption.NoSuchClientException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClientService {
    private final Connection connection;
    private PreparedStatement preparedStatement;


    public long create(String name) {
        if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalNameException(
                    "Name should be greater then two characters and less then one thousand characters"
            );
        }
        try {
            preparedStatement = connection.prepareStatement(Constance.CREATE_CLIENT);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (ResultSet resultSet = connection.prepareStatement(Constance.GET_CLIENT_WITH_MAX_ID).executeQuery()) {
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id) {
        String answer = null;
        try {
            if (!ifClientExist(id)) {
                throw new NoSuchClientException("No such client with id: " + id);
            }
            preparedStatement = connection.prepareStatement(Constance.GET_CLIENT_NAME_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answer = resultSet.getString("name");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (ResultSet resultSet = connection.prepareStatement(Constance.GET_ALL_CLIENT).executeQuery()) {
            while (resultSet.next()) {
                clients.add(Client.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    public void setName(long id, String name) {
        try {
            if (!ifClientExist(id)) {
                throw new NoSuchClientException("No such client with id: " + id);
            }
            preparedStatement = connection.prepareStatement(Constance.UPDATE_CLIENT_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(long id){
        try {
            if (!ifClientExist(id)) {
                throw new NoSuchClientException("No such client with id: " + id);
            }
            preparedStatement = connection.prepareStatement(Constance.DELETE_CLIENT_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean ifClientExist(long id) {
        long answer = -1;
        try {
            preparedStatement = connection.prepareStatement(Constance.GET_CLIENT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answer = resultSet.getLong(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer > 1;
    }
}
