package com.appbank.dao;

import com.appbank.connection.SingleConnection;
import com.appbank.models.ModelRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoRegister {
    private Connection connection;

    public DaoRegister() {
        connection = SingleConnection.getConnection();
    }

    public void createAccount(ModelRegister modelRegister) {
        try {
            String sql = "INSERT INTO account (email, password, account_number, agency) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, modelRegister.getEmail());
            statement.setString(2, modelRegister.getPassword());
            statement.setInt(3, modelRegister.getAccountNumber());
            statement.setInt(4, modelRegister.getAgencyNumber());
            statement.execute();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer changePassword(String oldPassword, String newPassword, Long Id) {
        try {
            String sql = "UPDATE account SET password=? WHERE password = ? AND id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, oldPassword);
            statement.setLong(3, Id);
            Integer retorno = statement.executeUpdate();
            connection.commit();
            return retorno;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
}
