package com.appbank.dao;

import com.appbank.connection.SingleConnection;
import com.appbank.models.ModelClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoClient {
    private Connection connection;

    public DaoClient() {
        connection = SingleConnection.getConnection();
    }

    public void insert(ModelClient modelClient) {
        try {
            String sql = "INSERT INTO client (id_register, first_name, full_name, birth_date, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, modelClient.getId_register());
            statement.setString(2, modelClient.getFirstName());
            statement.setString(3, modelClient.getFullName());
            statement.setDate(4, modelClient.getBirthDate());
            statement.setString(5, modelClient.getPhone());
            statement.execute();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ModelClient modelClient) {
        try {
            String sql = "UPDATE client SET first_name=?, full_name=?, birth_date=?, phone=? WHERE id_register = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelClient.getFirstName());
            statement.setString(2, modelClient.getFullName());
            statement.setDate(3, modelClient.getBirthDate());
            statement.setString(4, modelClient.getPhone());
            statement.setLong(5, modelClient.getId_register());
            statement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userExistsById(Long Id) {
        try {
            String sql = "SELECT * FROM client WHERE id_register = " + Id;
            PreparedStatement statement = connection.prepareStatement(sql);
            return statement.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ModelClient queryUserById(Long Id) {
        try {
            ModelClient user = new ModelClient();
            String sql = "SELECT * FROM client WHERE id_register = " + Id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet query = statement.executeQuery();
            while (query.next()) {
                user.setFirstName(query.getString("first_name"));
                user.setFullName(query.getString("full_name"));
                user.setBirthDate(query.getDate("birth_date"));
                user.setPhone(query.getString("phone"));
                user.setId_register(query.getLong("id_register"));
                user.setId(query.getLong("id"));
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
